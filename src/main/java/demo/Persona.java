package demo;
public class Persona {

    private String nome;
    private float conto;

    Persona(String nome, float conto){

        this.nome = nome;
        this.conto = conto;

    }

    public float getConto() {
        return conto;
    }

    public String getNome() {
        return nome;
    }
    
}
