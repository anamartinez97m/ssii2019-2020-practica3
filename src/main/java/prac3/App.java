package prac3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import java.io.File;
//import required classes
import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.converters.CSVLoader;
import weka.core.pmml.jaxbbindings.LiftData;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Discretize;
import weka.filters.unsupervised.attribute.StringToNominal;

@Configuration
@ComponentScan
@EnableJpaRepositories
@Import(RepositoryRestMvcConfiguration.class)
@EnableAutoConfiguration
@SpringBootApplication

public class App {
    public static void main(String[] args) throws Exception{
        SpringApplication.run(App.class, args);

        /*
        * Apartado 5 de la práctica
        */

        /*
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

        //- Mostrar en pantalla los atributos de los datos
        System.out.println(loader.getStructure()+" ...\n\n");

        // the Apriori alg.
        Apriori model = new Apriori();
        //model.setOptions(new String[]{"-N 10", "-T 1", "-C 1.3"});
        //model.setMetricType(1);
        //System.out.println(model.getMetricType());

        //model.setNumRules(10);
        //model.setMinMetric(1.3);

        // build model
        model.buildAssociations(data);
        System.out.println(model);
        */
    }
}