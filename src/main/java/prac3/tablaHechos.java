package prac3;

import prac3.dimHospital;
import prac3.dimPaciente;
import prac3.dimTiempo;

import javax.persistence.*;

@Entity
@Table(name = "tabla_hechos")
public class tablaHechos {

    //Clave primaria (PK)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    //Claves ajenas (FK)
    @ManyToOne
    @JoinColumn(name = "idPaciente")
    private dimPaciente cliente_id;

    @ManyToOne
    @JoinColumn(name = "idHospital")
    private dimHospital hospital_id;

    @ManyToOne
    @JoinColumn(name = "idTiempo")
    private dimTiempo fechaIngreso_id;

    //Atributos
    private int duracion;
    private boolean UCI;
    private boolean fallecido;
    private String tratamiento;

    //Constructor necesario para Spring
    private tablaHechos() {
    }

    //Constructor de clase
    public tablaHechos(dimPaciente cliente_id, dimHospital hospital_id, dimTiempo fechaIngreso_id, int duracion,
                       boolean UCI, boolean fallecido, String tratamiento) {
        this.cliente_id = cliente_id;
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

    public dimPaciente getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(dimPaciente cliente_id) {
        this.cliente_id = cliente_id;
    }

    public dimHospital getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(dimHospital hospital_id) {
        this.hospital_id = hospital_id;
    }

    public dimTiempo getFechaIngreso_id() {
        return fechaIngreso_id;
    }

    public void setFechaIngreso_id(dimTiempo fechaIngreso_id) {
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

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    //toString() para imprimir la clase
    @Override
    public String toString() {
        return "prac3.tablaHechos{" +
                "id='" + id + '\'' +
                ", cliente_id=" + cliente_id +
                ", hospital_id=" + hospital_id +
                ", fechaIngreso_id=" + fechaIngreso_id +
                ", duracion=" + duracion +
                ", UCI=" + UCI +
                ", fallecido=" + fallecido +
                ", tratamiento='" + tratamiento + '\'' +
                '}';
    }
}
