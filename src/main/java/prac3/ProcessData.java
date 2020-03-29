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

    @PostConstruct
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

                //Comprueba si existe el dato de hospital y obtiene su id
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

    public static Date guardarFecha(String fecha) throws ParseException {
        String patron = "dd/MM/yyyy";
        SimpleDateFormat formatoFecha = new SimpleDateFormat(patron);
        java.util.Date utilDate = new java.util.Date();
        Date date = new Date(utilDate.getTime());
        return date;
    }
}
