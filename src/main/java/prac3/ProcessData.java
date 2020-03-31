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
import java.util.Iterator;
import java.util.List;

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
            System.err.println("Hubo un problema con el fichero dimLUGAR.csv");
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
                SimpleDateFormat formatoFecha = new SimpleDateFormat(formatoFecha(fecha));
                Date date = new Date(formatoFecha.parse(fecha).getTime());
                int dia = Integer.parseInt(cadena[2]);
                int mes = Integer.parseInt(cadena[3]);
                int anio = Integer.parseInt(cadena[4]);
                int cuatrimestre = Integer.parseInt(cadena[5]);;
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
        } catch (IOException e) {
            System.err.println("Hubo un problema con el fichero dimTIEMPO.csv");
        } catch (ParseException pe) {
            System.err.println("Hubo un problema con la conversion de la fecha:");
            System.err.println(pe);
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
                char sexo = convertirDatoChar(cadena[2]);
                float IMC = Float.parseFloat(cadena[3]);
                short formaFisica = Short.parseShort(cadena[4]);
                boolean tabaquismo = convertirDatoBooleano(cadena[5]);
                boolean alcoholismo = convertirDatoBooleano(cadena[6]);;
                boolean colesterol = convertirDatoBooleano(cadena[7]);;
                boolean hipertension = convertirDatoBooleano(cadena[8]);;
                boolean cardiopatia = convertirDatoBooleano(cadena[9]);;
                boolean reuma = convertirDatoBooleano(cadena[10]);;
                boolean EPOC = convertirDatoBooleano(cadena[11]);;
                boolean cancer = convertirDatoBooleano(cadena[12]);;

                //Guardar datos de Pi.csv
                DimPaciente paciente = new DimPaciente(edad, sexo, IMC, formaFisica, tabaquismo, alcoholismo,
                                                        colesterol, hipertension, cardiopatia, reuma, EPOC, cancer);
                paciente.setId(id);

                //Comprueba si existe el dato de paciente y obtiene su id
                String p = servicioPaciente.comprobarPaciente(paciente);
                paciente.setId(p);

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

        List<DimPaciente> pacienteList = servicioPaciente.getPaciente();
        Iterator<DimPaciente> it = pacienteList.iterator();

        //Carga de datos de Hi.csv
        try {
            FileReader fr = new FileReader("src/main/resources/data/"+file+".csv");
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            //Leer siguiente linea ignorando la primera del bucle (cabecera de la tabla)
            line = br.readLine();

            while (line != null && it.hasNext()) {
                String resultado = line;
                String[] cadena = resultado.split(";");

                //Cargar datos de Hi.csv
                String id = cadena[0];
                DimPaciente paciente_id = asignarIdPaciente(Integer.parseInt(cadena[1]), it.next());
                DimHospital hospital_id = servicioHospital.getHospitalById(file);
                DimTiempo fechaIngreso_id = servicioTiempo.getTiempoByDiaAndMesAndAnioAndCuatrimestre(cadena[2]);
                System.out.println(fechaIngreso_id);
                int duracion = Integer.parseInt(cadena[3]);
                boolean UCI = convertirDatoBooleano(cadena[4]);
                boolean fallecido = convertirDatoBooleano(cadena[5]);
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

        } catch (IOException e) {
            System.err.println("Hubo un error con el fichero "+file+".csv");
        }

        System.out.println("DATOS DE "+file+".csv CARGADOS CON EXITO.");
    }

    public DimPaciente asignarIdPaciente(int id, DimPaciente p) {
        p.setIdPaciente(id);
        servicioPaciente.guardarPaciente(p);
        return p;
    }

    public boolean convertirDatoBooleano(String cadena) {
        if (cadena.contains("N") || cadena.contains("S")) {
           return (cadena.contains("S"))?true:false;
        } else {
            return (Integer.parseInt(cadena) == 1)?true:false;
        }
    }

    public char convertirDatoChar(String cadena) {
        if (cadena.contains("V") || cadena.contains("M")) {
            return (cadena.equals("M"))?'M':'H';
        } else {
            return (Integer.parseInt(cadena) == 1)?'M':'H';
        }
    }

    public String formatoFecha(String fecha){
        if (fecha.length() >  8) {
            return "dd/MM/yyyy";
        } else {
            return "dd/MM/yy";
        }
    }
}
