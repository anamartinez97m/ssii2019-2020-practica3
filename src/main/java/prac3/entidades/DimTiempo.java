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
    private short dia;
    @Column
    private short mes;
    @Column
    private short anio;
    @Column
    private short cuatrimestre;
    @Column
    private String diaSemana;
    @Column
    private byte esFinde;

    //Constructor necesario para Spring
    public DimTiempo() {
    }

    //Constructor de clase
    public DimTiempo(Date fecha, short dia, short mes, short anio, short cuatrimestre, String diaSemana, byte esFinde) {
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

    public short getDia() {
        return dia;
    }

    public void setDia(short dia) {
        this.dia = dia;
    }

    public short getMes() {
        return mes;
    }

    public void setMes(short mes) {
        this.mes = mes;
    }

    public short getAnio() {
        return anio;
    }

    public void setAnio(short anio) {
        this.anio = anio;
    }

    public short getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(short cuatrimestre) {
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
