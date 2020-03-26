import javax.persistence.*;

@Entity
@Table(name = "tabla_hechos")
public class tablaHECHOS {

    //Clave primaria (PK)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    //Claves ajenas (FK)
    @ManyToOne
    @JoinColumn(name = "id")
    private dimPACIENTE cliente_id;

    @ManyToOne
    @JoinColumn(name = "id")
    private dimHOSPITAL hospital_id;

    @ManyToOne
    @JoinColumn(name = "id")
    private dimTIEMPO fechaIngreso_id;

    //Atributos
    private int duracion;
    private boolean UCI;
    private boolean fallecido;
    private String tratamiento;

    //Constructor necesario para Spring
    private tablaHECHOS() {

    }

    //Constructor de clase
    public tablaHECHOS(dimPACIENTE cliente_id, dimHOSPITAL hospital_id, dimTIEMPO fechaIngreso_id, int duracion,
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

    public dimPACIENTE getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(dimPACIENTE cliente_id) {
        this.cliente_id = cliente_id;
    }

    public dimHOSPITAL getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(dimHOSPITAL hospital_id) {
        this.hospital_id = hospital_id;
    }

    public dimTIEMPO getFechaIngreso_id() {
        return fechaIngreso_id;
    }

    public void setFechaIngreso_id(dimTIEMPO fechaIngreso_id) {
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
        return "tablaHECHOS{" +
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
