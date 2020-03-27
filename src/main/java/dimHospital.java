import javax.persistence.*;

@Entity
@Table(name = "dim_hospital")
public class dimHospital {

    //Clave primaria (PK)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String idHospital;

    //Atributos
    private String nombre;
    private short cpostal;
    private String autopista;
    private String gestor;

    //Constructor necesario para Spring
    private dimHospital() {
    }

    //Constructor de clase
    public dimHospital(String nombre, short cpostal, String autopista, String gestor){
        this.nombre = nombre;
        this.cpostal = cpostal;
        this.autopista = autopista;
        this.gestor = gestor;
    }

    //Getters y Setters
    public String getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(String idHospital) {
        this.idHospital = idHospital;
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
        return "dimHospital{" +
                "id='" + idHospital + '\'' +
                ", nombre='" + nombre + '\'' +
                ", cpostal=" + cpostal +
                ", autopista='" + autopista + '\'' +
                ", gestor='" + gestor + '\'' +
                '}';
    }
}
