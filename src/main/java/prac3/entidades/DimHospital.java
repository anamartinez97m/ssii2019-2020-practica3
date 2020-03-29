package prac3.entidades;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "dim_hospital")
public class DimHospital {

    //Clave primaria (PK)
    @Id
    private String idHospital;

    //Atributos
    @Column
    private String nombre;
    @Column
    private int cpostal;
    @Column
    private String autopista;
    @Column
    private String gestor;

    //Constructor necesario para Spring
    public DimHospital() {
    }

    //Constructor de clase
    public DimHospital(String nombre, int cpostal, String autopista, String gestor){
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

    public int getCpostal() {
        return cpostal;
    }

    public void setCpostal(int cpostal) {
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
        return "prac3.entidades.dimHospital{" +
                "id='" + idHospital + '\'' +
                ", nombre='" + nombre + '\'' +
                ", cpostal=" + cpostal +
                ", autopista='" + autopista + '\'' +
                ", gestor='" + gestor + '\'' +
                '}';
    }
}
