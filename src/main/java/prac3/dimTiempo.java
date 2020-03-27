package prac3;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "dim_tiempo")
public class dimTiempo {

    // Clave primaria (PK)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String idTiempo;

    //Atributos
    private Date fecha;
    private int dia;
    private String mes;
    private int anio;
    private int cuatrimestre;
    private int diaSemana;
    private boolean esFinde;

    //Constructor necesario para Spring
    private dimTiempo() {
    }

    //Constructor de clase
    public dimTiempo(Date fecha, int dia, String mes, int anio, int cuatrimestre, int diaSemana, boolean esFinde) {
        this.fecha = fecha;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.cuatrimestre = cuatrimestre;
        this.diaSemana = diaSemana;
        this.esFinde = esFinde;
    }

    //Getters y Setters
    public String getIdTiempo() {
        return idTiempo;
    }

    public void setIdTiempo(String idTiempo) {
        this.idTiempo = idTiempo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(int cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }

    public int getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(int diaSemana) {
        this.diaSemana = diaSemana;
    }

    public boolean isEsFinde() {
        return esFinde;
    }

    public void setEsFinde(boolean esFinde) {
        this.esFinde = esFinde;
    }

    //toString() para imprimir la clase
    @Override
    public String toString() {
        return "prac3.dimTiempo{" +
                "id='" + idTiempo + '\'' +
                ", fecha=" + fecha +
                ", dia=" + dia +
                ", mes='" + mes + '\'' +
                ", anio=" + anio +
                ", cuatrimestre=" + cuatrimestre +
                ", diaSemana=" + diaSemana +
                ", esFinde=" + esFinde +
                '}';
    }
}
