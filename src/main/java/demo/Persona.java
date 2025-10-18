package demo;
public class Persona {

    private String nome;
    private double conto;
    private double saldoScommesso;
    private int scommessa;

    Persona(){

    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setScommessa(int scommessa) {
        this.scommessa = scommessa;
    }

    public void setSaldoScommesso(double saldoScommesso) {
        this.saldoScommesso = saldoScommesso;
    }

    public void setConto(double conto) {
        this.conto = conto;
    }

    public int getScommessa() {
        return scommessa;
    }

    public double getSaldoScommesso() {
        return saldoScommesso;
    }

    public double getConto() {
        return conto;
    }

    public String getNome() {
        return nome;
    }
    
}
