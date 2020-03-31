package prac3.entidades;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "dim_tiempo")
public class DimTiempo {

    // Clave primaria (PK)
    @Id
    private String idTiempo;

    //Atributos
    @Column
    private Date fecha;
    @Column
    private int dia;
    @Column
    private int mes;
    @Column
    private int anio;
    @Column
    private int cuatrimestre;
    @Column
    private String diaSemana;
    @Column
    private byte esFinde;

    //Constructor necesario para Spring
    public DimTiempo() {
    }

    //Constructor de clase
    public DimTiempo(Date fecha, int dia, int mes, int anio, int cuatrimestre, String diaSemana, byte esFinde) {
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

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
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

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public byte isEsFinde() {
        return esFinde;
    }

    public void setEsFinde(byte esFinde) {
        this.esFinde = esFinde;
    }

    //toString() para imprimir la clase
    @Override
    public String toString() {
        return "prac3.entidades.dimTiempo{" +
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
