package prac3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prac3.entidades.*;
import prac3.servicios.*;

import java.lang.String;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class ProcessData {
    @Autowired
    private ServicioHechos servicioHechos;
    @Autowired
    private ServicioHospital servicioHospital;
    @Autowired
    private ServicioPaciente servicioPaciente;
    @Autowired
    private ServicioTiempo servicioTiempo;

    @PostConstruct
    public void loadData(){
        System.out.println("PROCEDIENDO A LA CARGA DE DATOS");
        processDimLugar();
        processDimTiempo();

        for (int i=1; i<5; i++) {
            processDimPaciente("P"+i);
        }

        for (int i=1; i<5; i++) {
            processTablaHechos("H"+i);
        }

    }

    public void processDimLugar() {

        System.out.println("CARGANDO DATOS DE dimLUGAR.csv...");

        try {
            FileReader fr = new FileReader("src/main/resources/data/dimLUGAR.csv");
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            //Leer siguiente linea ignorando la primera del bucle (cabecera de la tabla)
            line = br.readLine();
            while ((line != null)) {
                String resultado = line;
                String[] cadena = resultado.split(";");

                //Cargar datos de dimLUGAR.csv
                String id = cadena[0];
                String nombre = cadena[1];
                int cp = Integer.parseInt(cadena[2]);
                String autopista = cadena[3];
                String gestor = cadena[4];

                //Guardar datos de dimLUGAR.csv
                DimHospital hospital = new DimHospital(nombre, cp, autopista, gestor);
                hospital.setIdHospital(id);

                //Comprueba si existe el dato de hospital y obtiene su id
                String h = servicioHospital.comprobarHospital(hospital);
                hospital.setIdHospital(h);

                servicioHospital.guardarHospital(hospital);

                //Lee la siguiente linea
                line = br.readLine();
            }
        } catch (IOException e) {
            System.err.println("Hubo un problema con el fichero");
        }

        System.out.println("DATOS DE dimLUGAR.csv CARGADOS CON EXITO.");

    }

    public void processDimTiempo() {

        System.out.println("CARGANDO DATOS DE dimTIEMPO.csv...");

        try {
            FileReader fr = new FileReader("src/main/resources/data/dimTIEMPO.csv");
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            //Leer siguiente linea ignorando la primera del bucle (cabecera de la tabla)
            line = br.readLine();
            while ((line != null)) {
                String resultado = line;
                String[] cadena = resultado.split(";");

                //Cargar datos de dimTIEMPO.csv
                String id = cadena[0];
                String fecha = cadena[1];
                Date date = guardarFecha(fecha);
                short dia = Short.parseShort(cadena[2]);
                short mes = Short.parseShort(cadena[3]);
                short anio = Short.parseShort(cadena[4]);
                short cuatrimestre = Short.parseShort(cadena[5]);;
                String diaSemana = cadena[6];
                byte esFinde = Byte.parseByte(cadena[7]);

                //Guardar datos de dimTIEMPO.csv
                DimTiempo tiempo = new DimTiempo(date, dia, mes, anio, cuatrimestre, diaSemana, esFinde);
                tiempo.setIdTiempo(id);

                //Comprueba si existe el dato de tiempo y obtiene su id
                String t = servicioTiempo.comprobarTiempo(tiempo);
                tiempo.setIdTiempo(t);

                servicioTiempo.guardarTiempo(tiempo);

                //Lee la siguiente linea
                line = br.readLine();
            }
        } catch (IOException | ParseException e) {
            System.err.println("Hubo un problema con el fichero");
        }

        System.out.println("DATOS DE dimTIEMPO.csv CARGADOS CON EXITO.");

    }

    public void processDimPaciente(String file){
        System.out.println("CARGANDO DATOS DE "+file+".csv");

        //Carga de datos de Pi.csv
        try {
            FileReader fr = new FileReader("src/main/resources/data/"+file+".csv");
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            //Leer siguiente linea ignorando la primera del bucle (cabecera de la tabla)
            line = br.readLine();

            while (line != null) {
                String resultado = line;
                String[] cadena = resultado.split(";");

                //Cargar datos de Pi.csv
                String id = cadena[0];
                Short edad = Short.parseShort(cadena[1]);
                char sexo = (cadena[2].equals("0"))?'H':'M';
                float IMC = Float.parseFloat(cadena[3]);
                short formaFisica = Short.parseShort(cadena[4]);
                boolean tabaquismo = (cadena[5].equals("0"))?false:true;
                boolean alcoholismo = (cadena[6].equals("0"))?false:true;
                boolean colesterol = (cadena[7].equals("0"))?false:true;
                boolean hipertension = (cadena[8].equals("0"))?false:true;
                boolean cardiopatia = (cadena[9].equals("0"))?false:true;
                boolean reuma = (cadena[10].equals("0"))?false:true;
                boolean EPOC = (cadena[11].equals("0"))?false:true;
                boolean cancer = (cadena[12].equals("0"))?false:true;

                //Guardar datos de Pi.csv
                DimPaciente paciente = new DimPaciente(edad, sexo, IMC, formaFisica, tabaquismo, alcoholismo,
                                                        colesterol, hipertension, cardiopatia, reuma, EPOC, cancer);
                paciente.setIdPaciente(id);

                //Comprueba si existe el dato de paciente y obtiene su id
                String p = servicioPaciente.comprobarPaciente(paciente);
                paciente.setIdPaciente(p);

                servicioPaciente.guardarPaciente(paciente);

                //Lee la siguiente linea
                line = br.readLine();

            }
        } catch (IOException e) {
            System.err.println("Hubo un error con el fichero "+file+".csv");
        }

        System.out.println("DATOS DE "+file+".csv CARGADOS CON EXITO.");
    }

    public void processTablaHechos(String file) {
        System.out.println("CARGANDO DATOS DE "+file+".csv");

        //Carga de datos de Hi.csv
        try {
            FileReader fr = new FileReader("src/main/resources/data/"+file+".csv");
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            //Leer siguiente linea ignorando la primera del bucle (cabecera de la tabla)
            line = br.readLine();

            while (line != null) {
                String resultado = line;
                String[] cadena = resultado.split(";");

                //Cargar datos de Hi.csv
                String id = cadena[0];
                DimPaciente paciente_id = servicioPaciente.getPacienteById(cadena[1]);
                DimHospital hospital_id = servicioHospital.getHospitalById(file);
                DimTiempo fechaIngreso_id = servicioTiempo.getTiempoByFecha(guardarFecha(cadena[2]));
                int duracion = Integer.parseInt(cadena[3]);
                boolean UCI = (cadena[4].equals("No"))?false:true;
                boolean fallecido = (cadena[5].equals("No"))?false:true;
                short tratamiento = Short.parseShort(cadena[6]);

                //Guardar datos de Hi.csv
                TablaHechos hecho = new TablaHechos(paciente_id, hospital_id, fechaIngreso_id, duracion, UCI, fallecido, tratamiento);
                hecho.setId(id);

                //Comprueba si existe el dato de hecho y obtiene su id
                String th = servicioHechos.comprobarHecho(hecho);
                hecho.setId(th);

                servicioHechos.guardarHecho(hecho);

                //Lee la siguiente linea
                line = br.readLine();
            }

        } catch (IOException | ParseException e) {
            System.err.println("Hubo un error con el fichero "+file+".csv");
        }

        System.out.println("DATOS DE "+file+".csv CARGADOS CON EXITO.");
    }

    public static Date guardarFecha(String fecha) throws ParseException {
        String patron = "dd/MM/yyyy";
        SimpleDateFormat formatoFecha = new SimpleDateFormat(patron);
        java.util.Date utilDate = new java.util.Date();
        Date date = new Date(utilDate.getTime());
        return date;
    }
}
