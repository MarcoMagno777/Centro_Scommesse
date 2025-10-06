package demo;

public class Numeri {
    
    private int numero;
    private boolean pari;
    private String colore;

    Numeri(int numero, boolean pari, String colore){
        this.numero = numero;
        this.pari = pari;
        this.colore = colore;
    }

    public String getColore() {
        return colore;
    }

    public int getNumero() {
        return numero;
    }

    public boolean getPari(){
        return pari;
    }
    
}
