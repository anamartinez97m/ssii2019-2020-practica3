package prac3.entidades;

import javax.persistence.*;

@Entity
@Table(name = "tabla_hechos")
public class TablaHechos {

    //Clave primaria (PK)
    @Id
    private String id;

    //Claves ajenas (FK)
    @ManyToOne
    @JoinColumn(name = "idPaciente")
    private DimPaciente paciente_id;

    @ManyToOne
    @JoinColumn(name = "idHospital")
    private DimHospital hospital_id;

    @ManyToOne
    @JoinColumn(name = "idTiempo")
    private DimTiempo fechaIngreso_id;

    //Atributos
    private int duracion;
    private boolean UCI;
    private boolean fallecido;
    private short tratamiento;

    //Constructor necesario para Spring
    public TablaHechos() {
    }

    //Constructor de clase
    public TablaHechos(DimPaciente paciente_id, DimHospital hospital_id, DimTiempo fechaIngreso_id, int duracion,
                       boolean UCI, boolean fallecido, short tratamiento) {
        this.paciente_id = paciente_id;
        this.hospital_id = hospital_id;
        this.fechaIngreso_id = fechaIngreso_id;
        this.duracion = duracion;
        this.UCI = UCI;
        this.fallecido = fallecido;
        this.tratamiento = tratamiento;
    }

    //Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DimPaciente getPaciente_id() {
        return paciente_id;
    }

    public void setPaciente_id(DimPaciente cliente_id) {
        this.paciente_id = cliente_id;
    }

    public DimHospital getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(DimHospital hospital_id) {
        this.hospital_id = hospital_id;
    }

    public DimTiempo getFechaIngreso_id() {
        return fechaIngreso_id;
    }

    public void setFechaIngreso_id(DimTiempo fechaIngreso_id) {
        this.fechaIngreso_id = fechaIngreso_id;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public boolean isUCI() {
        return UCI;
    }

    public void setUCI(boolean UCI) {
        this.UCI = UCI;
    }

    public boolean isFallecido() {
        return fallecido;
    }

    public void setFallecido(boolean fallecido) {
        this.fallecido = fallecido;
    }

    public short getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(short tratamiento) {
        this.tratamiento = tratamiento;
    }

    //toString() para imprimir la clase
    @Override
    public String toString() {
        return "prac3.entidades.tablaHechos{" +
                "id='" + id + '\'' +
                ", paciente_id=" + paciente_id +
                ", hospital_id=" + hospital_id +
                ", fechaIngreso_id=" + fechaIngreso_id +
                ", duracion=" + duracion +
                ", UCI=" + UCI +
                ", fallecido=" + fallecido +
                ", tratamiento='" + tratamiento + '\'' +
                '}';
    }
}
