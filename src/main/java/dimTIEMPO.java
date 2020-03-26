import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "dim_tiempo")
public class dimTIEMPO {

    // Clave primaria (PK)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    //Atributos
    private Date fecha;
    private int dia;
    private String mes;
    private int anio;
    private int cuatrimestre;
    private int diaSemana;
    private boolean esFinde;

    //Constructor necesario para Spring
    private dimTIEMPO() {

    }

    //Constructor de clase
    public dimTIEMPO(Date fecha, int dia, String mes, int anio, int cuatrimestre, int diaSemana, boolean esFinde) {
        this.fecha = fecha;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.cuatrimestre = cuatrimestre;
        this.diaSemana = diaSemana;
        this.esFinde = esFinde;
    }

    //Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "dimTIEMPO{" +
                "id='" + id + '\'' +
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
