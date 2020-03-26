import javax.persistence.*;

@Entity
@Table(name = "dim_paciente")
public class dimPACIENTE {

    //Clave primaria (PK)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    //Atributos
    private int edad;
    private char sexo;
    private double IMC;
    private String formaFisica;
    private boolean tabaquismo, alcoholismo, colesterol, hipertension, cardiopatía, reuma, EPOC, cancer;

    //Constructor necesario para Spring
    private dimPACIENTE() {

    }

    //Constructor de clase
    public dimPACIENTE(int edad, char sexo, double IMC, String formaFisica, boolean tabaquismo, boolean alcoholismo,
                       boolean colesterol, boolean hipertension, boolean cardiopatía, boolean reuma, boolean EPOC,
                       boolean cancer) {
        this.edad = edad;
        this.sexo = sexo;
        this.IMC = IMC;
        this.formaFisica = formaFisica;
        this.tabaquismo = tabaquismo;
        this.colesterol = colesterol;
        this.hipertension = hipertension;
        this.cardiopatía = cardiopatía;
        this.reuma = reuma;
        this.EPOC = EPOC;
        this.cancer = cancer;
    }

    //Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public double getIMC() {
        return IMC;
    }

    public void setIMC(double IMC) {
        this.IMC = IMC;
    }

    public String getFormaFisica() {
        return formaFisica;
    }

    public void setFormaFisica(String formaFisica) {
        this.formaFisica = formaFisica;
    }

    public boolean isTabaquismo() {
        return tabaquismo;
    }

    public void setTabaquismo(boolean tabaquismo) {
        this.tabaquismo = tabaquismo;
    }

    public boolean isAlcoholismo() {
        return alcoholismo;
    }

    public void setAlcoholismo(boolean alcoholismo) {
        this.alcoholismo = alcoholismo;
    }

    public boolean isColesterol() {
        return colesterol;
    }

    public void setColesterol(boolean colesterol) {
        this.colesterol = colesterol;
    }

    public boolean isHipertension() {
        return hipertension;
    }

    public void setHipertension(boolean hipertension) {
        this.hipertension = hipertension;
    }

    public boolean isCardiopatía() {
        return cardiopatía;
    }

    public void setCardiopatía(boolean cardiopatía) {
        this.cardiopatía = cardiopatía;
    }

    public boolean isReuma() {
        return reuma;
    }

    public void setReuma(boolean reuma) {
        this.reuma = reuma;
    }

    public boolean isEPOC() {
        return EPOC;
    }

    public void setEPOC(boolean EPOC) {
        this.EPOC = EPOC;
    }

    public boolean isCancer() {
        return cancer;
    }

    public void setCancer(boolean cancer) {
        this.cancer = cancer;
    }

    //toString() para imprimir la clase
    @Override
    public String toString() {
        return "dimPACIENTE{" +
                "id='" + id + '\'' +
                ", edad=" + edad +
                ", sexo=" + sexo +
                ", IMC=" + IMC +
                ", formaFisica='" + formaFisica + '\'' +
                ", tabaquismo=" + tabaquismo +
                ", alcoholismo=" + alcoholismo +
                ", colesterol=" + colesterol +
                ", hipertension=" + hipertension +
                ", cardiopatía=" + cardiopatía +
                ", reuma=" + reuma +
                ", EPOC=" + EPOC +
                ", cancer=" + cancer +
                '}';
    }
}
