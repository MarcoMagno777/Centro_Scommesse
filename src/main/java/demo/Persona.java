package demo;
public class Persona {

    private String nome;
    private double conto;
    private double scommessa;
    private int cavallo;

    Persona(String nome, float conto){

        this.nome = nome;
        this.conto = conto;

    }

    public void setCavallo(int cavallo) {
        this.cavallo = cavallo;
    }

    public void setScommessa(double scommessa) {
        this.scommessa = scommessa;
    }

    public void setConto(double conto) {
        this.conto = conto;
    }

    public int getCavallo() {
        return cavallo;
    }

    public double getScommessa() {
        return scommessa;
    }

    public double getConto() {
        return conto;
    }

    public String getNome() {
        return nome;
    }
    
}
