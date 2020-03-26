import javax.persistence.*;

@Entity
@Table(name = "dim_hospital")
public class dimHOSPITAL {

    //Clave primaria (PK)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    //Atributos
    private String nombre;
    private short cpostal;
    private String autopista;
    private String gestor;

    //Constructor necesario para Spring
    private dimHOSPITAL() {

    }

    //Constructor de clase
    public dimHOSPITAL(String nombre, short cpostal, String autopista, String gestor){
        this.nombre = nombre;
        this.cpostal = cpostal;
        this.autopista = autopista;
        this.gestor = gestor;
    }

    //Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public short getCpostal() {
        return cpostal;
    }

    public void setCpostal(short cpostal) {
        this.cpostal = cpostal;
    }

    public String getAutopista() {
        return autopista;
    }

    public void setAutopista(String autopista) {
        this.autopista = autopista;
    }

    public String getGestor() {
        return gestor;
    }

    public void setGestor(String gestor) {
        this.gestor = gestor;
    }

    //toString() para imprimir la clase
    @Override
    public String toString() {
        return "dimHOSPITAL{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", cpostal=" + cpostal +
                ", autopista='" + autopista + '\'' +
                ", gestor='" + gestor + '\'' +
                '}';
    }
}
