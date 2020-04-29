package prac3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import java.io.*;
import java.util.*;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Discretize;
import weka.filters.unsupervised.attribute.StringToNominal;
import weka.clusterers.SimpleKMeans;

@Configuration
@ComponentScan
@EnableJpaRepositories
@Import(RepositoryRestMvcConfiguration.class)
@EnableAutoConfiguration
@SpringBootApplication

public class App {
    private static int TOTAL_NUMBER_OF_PACIENTS;
    private static int TOTAL_NUMBER_OF_MEDS;
    private static int[] NUMBER_OF_PACIENTS_BY_HOSPITAL = new int[4];

    public static void main(String[] args) throws Exception{
        SpringApplication.run(App.class, args);

        /*
        * Apartado 5 de la práctica
        */

        String inputDataFile = "src/main/resources/data/negativos.csv";

        //- Crear el cargador de CSV
        File inFile = new File(inputDataFile);
        CSVLoader loader = new CSVLoader();

        //- Especificar las caracteristicas del CSV
        loader.setFieldSeparator(",");
        loader.setNoHeaderRowPresent(false);

        // get instances object
        loader.setSource(inFile);
        Instances data = loader.getDataSet();

        //- String to Nominal
        StringToNominal filter1 = new StringToNominal();
        filter1.setAttributeRange("first-last");
        filter1.setInputFormat(data);
        data  = Filter.useFilter(data, filter1);

        //- Discretizar en 2 cubos
        Discretize  filter2 = new Discretize();
        filter2.setBins(2);
        filter2.setInputFormat(data);
        data = Filter.useFilter(data, filter2);

        // the Apriori alg.
        Apriori model = new Apriori();
        model.setOptions(new String[]{"-N", "10", "-T", "1", "-C", "1.3"});

        // build model
        model.buildAssociations(data);

        /*
        * Apartado 4 de la práctica
        * */

        prepareTemporalCsvFile();
        fillEmptyMedsWithRecommendations();
        generateBestMedsByPacient();

        /*
         * Apartado 6 de la práctica
         **/

        clustersPacientes("pacientesFallecidos.csv");
        clustersPacientes("pacientesUCI.csv");
        clustersPacientes("pacientesResto.csv");

        System.out.println("Archivos creados y aplicación iniciada");
    }

    private static void clustersPacientes(String name) throws Exception {
        // Numero de clusters a hacer
        int K = 3;

        // Numero maximo de iteraciones
        int maxIteraciones = 100;

        String nameTXT = "";

        switch (name) {
            case "pacientesFallecidos.csv":
                nameTXT = "protoFallecido.txt";
                break;
            case "pacientesUCI.csv":
                nameTXT = "protoUCI.txt";
                break;
            case "pacientesResto.csv":
                nameTXT = "protoResto.txt";
                break;
        }

        // Ruta fichero CSV
        String inputDataFile = "src/main/resources/data/"+name;

        // Crear el cargador de CSV
        File inFile = new File(inputDataFile);
        CSVLoader loader = new CSVLoader();

        // Especificar las caracteristicas del CSV
        loader.setFieldSeparator(";");
        loader.setNoHeaderRowPresent(false); //Indica que la primera fila es la cabecera

        // Cargar los datos
        loader.setSource(inFile);
        Instances data = loader.getDataSet();

        // Crear un objeto "K-Means", que es el metodo a utilizar
        SimpleKMeans kMeans = new SimpleKMeans();

        // Especificar las caracteristicas del metodo
        kMeans.setNumClusters(K);
        kMeans.setMaxIterations(maxIteraciones);
        kMeans.setPreserveInstancesOrder(true);

        // Ejecutar el agrupamiento sobre los datos
        try {
            kMeans.buildClusterer(data);
        } catch (Exception ex) {
            System.err.println("Error al construir los Clusterer: " + ex.getMessage());
            ex.printStackTrace();
        }

        // Escribir los prototipos de cada grupo (centroides)
        Instances centroids = kMeans.getClusterCentroids();

        File txt = new File("src/main/resources/data/"+nameTXT);
        FileWriter fw = new FileWriter(txt);
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            // Escribir los atributos de los datos
            bw.append("Atributo de los datos:\n------------------");
            bw.append(""+loader.getStructure()+"\n\n");

            bw.append("-----------RESULTADO------------\n");
            for(int i = 0; i < K; i++) {
                bw.append("Cluster "+i+1+" tamaño: "+kMeans.getClusterSizes()[i]+"\n");
                bw.append("Prueba: " +centroids.instance(i)+"\n");
            }

            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.err.println("Hubo un error con la creación de: "+nameTXT);
        }

    }

    private static void prepareTemporalCsvFile() throws Exception {
        FileWriter csvWriter = new FileWriter("src/main/resources/data/temporal.csv");
        int numPacient = 1;
        for(int i = 1; i <= 4; i++) {
            BufferedReader csvReader = new BufferedReader(new FileReader("src/main/resources/data/datos_filtrado_colaborativo_" + i + ".csv"));
            // Skip first line
            csvReader.readLine();
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                TOTAL_NUMBER_OF_MEDS = data.length - 1;
                String pacient;
                String med;
                String rating;
                for(int j = 1; j < data.length; j++) {
                    pacient = String.valueOf(numPacient);
                    med = String.valueOf(j);
                    rating = (data[j].equals("0")) ? "" : String.valueOf(data[j]);
                    csvWriter.append(String.join(",", Arrays.asList(pacient, med, rating)));
                    csvWriter.append("\n");
                }
                numPacient++;
            }
            NUMBER_OF_PACIENTS_BY_HOSPITAL[i - 1] = (numPacient - 1) - TOTAL_NUMBER_OF_PACIENTS;
            TOTAL_NUMBER_OF_PACIENTS = numPacient - 1;
            csvReader.close();
        }
        csvWriter.flush();
        csvWriter.close();
    }

    private static HashMap<Long, Float> calculatePacientRecommendationsForEmptyMeds(int numPacient) throws Exception {
        DataModel model = new FileDataModel(new File("src/main/resources/data/temporal.csv"));
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
        UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
        UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
        List<RecommendedItem> recommendations = recommender.recommend(numPacient, 20);
        HashMap<Long, Float> recommendationsMap = new HashMap<>();
        for (RecommendedItem recommendation : recommendations) {
            recommendationsMap.put(recommendation.getItemID(), recommendation.getValue());
        }
        return recommendationsMap;
    }

    private static void fillEmptyMedsWithRecommendations() throws Exception {
        FileWriter csvWriter = new FileWriter("src/main/resources/data/temporalFilledWithRecommendations.csv");
        BufferedReader csvReader = new BufferedReader(new FileReader("src/main/resources/data/temporal.csv"));
        String row;
        ArrayList<String[]> dataFilled = new ArrayList<>();
        int numPacient = 1;
        int rowNumber = 1;
        HashMap<Long, Float> mapMissingMedsRecommendations;
        mapMissingMedsRecommendations = calculatePacientRecommendationsForEmptyMeds(numPacient);
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            String ratingValue;
            if(data.length < 3) {
                String med = data[1];
                String missingMedRating = String.valueOf(mapMissingMedsRecommendations.get(Long.parseLong(med)));
                ratingValue = missingMedRating;
            } else {
                ratingValue = data[2];
            }
            dataFilled.add(new String[]{data[0], data[1], ratingValue});
            if(rowNumber % TOTAL_NUMBER_OF_MEDS == 0 && numPacient < TOTAL_NUMBER_OF_PACIENTS) {
                numPacient++;
                mapMissingMedsRecommendations = calculatePacientRecommendationsForEmptyMeds(numPacient);
            }
            rowNumber++;
        }

        // Write the file with the recommended meds that were missing
        for(String[] rowData : dataFilled) {
            csvWriter.append(String.join(",", Arrays.asList(rowData[0], rowData[1], rowData[2])));
            csvWriter.append("\n");
        }
        csvReader.close();
        csvWriter.flush();
        csvWriter.close();
    }

    private static void generateBestMedsByPacient() throws Exception {
        BufferedReader csvReader = new BufferedReader(new FileReader("src/main/resources/data/temporalFilledWithRecommendations.csv"));
        FileWriter writer1 = new FileWriter("src/main/resources/charts/bestMedsHospital1.txt");
        FileWriter writer2 = new FileWriter("src/main/resources/charts/bestMedsHospital2.txt");
        FileWriter writer3 = new FileWriter("src/main/resources/charts/bestMedsHospital3.txt");
        FileWriter writer4 = new FileWriter("src/main/resources/charts/bestMedsHospital4.txt");
        HashMap<Integer, ArrayList<Float>> mapPacientsMeds = new HashMap<>();
        int rowNumber = 1;
        int numPacient = 1;
        ArrayList<Float> medsWithRatings = new ArrayList<>();
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            medsWithRatings.add(Float.parseFloat(data[2]));
            mapPacientsMeds.put(numPacient, medsWithRatings);
            if(rowNumber % TOTAL_NUMBER_OF_MEDS == 0 && numPacient < TOTAL_NUMBER_OF_PACIENTS) {
                numPacient++;
                medsWithRatings = new ArrayList<>();
            }
            rowNumber++;
        }
        csvReader.close();
        Iterator it = mapPacientsMeds.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            int[] bestMedsIndexes = getBestMeds((ArrayList<Float>) pair.getValue());
            // To adjust the meds and avoid to start the indexes from 0, because the meds start from 1
            for(int i = 0; i < bestMedsIndexes.length; i++) {
                bestMedsIndexes[i] = bestMedsIndexes[i] + 1;
            }
            if((int) pair.getKey() <= NUMBER_OF_PACIENTS_BY_HOSPITAL[0]) {
                String numPacientInHospital = String.valueOf((int) pair.getKey());
                writer1.append(String.join(",", Arrays.asList(
                        numPacientInHospital,
                        String.valueOf(bestMedsIndexes[0]),
                        String.valueOf(bestMedsIndexes[1]),
                        String.valueOf(bestMedsIndexes[2])
                )));
                writer1.append("\n");
            } else if((int) pair.getKey() - NUMBER_OF_PACIENTS_BY_HOSPITAL[0] <= NUMBER_OF_PACIENTS_BY_HOSPITAL[1]) {
                String numPacientInHospital = String.valueOf((int) pair.getKey() - NUMBER_OF_PACIENTS_BY_HOSPITAL[0]);
                writer2.append(String.join(",", Arrays.asList(
                        numPacientInHospital,
                        String.valueOf(bestMedsIndexes[0]),
                        String.valueOf(bestMedsIndexes[1]),
                        String.valueOf(bestMedsIndexes[2])
                )));
                writer2.append("\n");
            } else if((int) pair.getKey() - NUMBER_OF_PACIENTS_BY_HOSPITAL[0]
                    - NUMBER_OF_PACIENTS_BY_HOSPITAL[1] <= NUMBER_OF_PACIENTS_BY_HOSPITAL[2]) {
                String numPacientInHospital = String.valueOf((int) pair.getKey() - NUMBER_OF_PACIENTS_BY_HOSPITAL[1] -
                        NUMBER_OF_PACIENTS_BY_HOSPITAL[0]);
                writer3.append(String.join(",", Arrays.asList(
                        numPacientInHospital,
                        String.valueOf(bestMedsIndexes[0]),
                        String.valueOf(bestMedsIndexes[1]),
                        String.valueOf(bestMedsIndexes[2])
                )));
                writer3.append("\n");
            } else {
                String numPacientInHospital = String.valueOf((int) pair.getKey() - NUMBER_OF_PACIENTS_BY_HOSPITAL[2] -
                        NUMBER_OF_PACIENTS_BY_HOSPITAL[1] - NUMBER_OF_PACIENTS_BY_HOSPITAL[0]);
                writer4.append(String.join(",", Arrays.asList(
                        numPacientInHospital,
                        String.valueOf(bestMedsIndexes[0]),
                        String.valueOf(bestMedsIndexes[1]),
                        String.valueOf(bestMedsIndexes[2])
                )));
                writer4.append("\n");
            }
        }
        writer1.flush();
        writer1.close();
        writer2.flush();
        writer2.close();
        writer3.flush();
        writer3.close();
        writer4.flush();
        writer4.close();
    }

    private static int[] getBestMeds(ArrayList<Float> values) {
        ArrayList<Float> valuesCopyOrdered =new ArrayList<>(values);
        ArrayList<Float> valuesCopyOriginal =new ArrayList<>(values);
        int[] bestIndexes = new int[3];
        Collections.sort(valuesCopyOrdered);
        Collections.reverse(valuesCopyOrdered);
        bestIndexes[0] = valuesCopyOriginal.indexOf(valuesCopyOrdered.get(0));
        valuesCopyOriginal.set(bestIndexes[0], (float) -1.0);
        bestIndexes[1] = valuesCopyOriginal.indexOf(valuesCopyOrdered.get(1));
        valuesCopyOriginal.set(bestIndexes[1], (float) -1.0);
        bestIndexes[2] = valuesCopyOriginal.indexOf(valuesCopyOrdered.get(2));
        valuesCopyOriginal.set(bestIndexes[2], (float) -1.0);
        return bestIndexes;
    }
}