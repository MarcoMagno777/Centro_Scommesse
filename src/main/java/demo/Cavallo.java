package demo;

public class Cavallo extends Thread{
    
    private String nome;
    private static int posizone;
    private int posizioneFinale;
    private double quota;


    Cavallo(String nome){
        this.nome = nome;
    }

    public void generaQuota(){
        quota = Math.round(Math.random()*6) + 1.2;
        posizone = 0;
    }

    public void run(){

        try {

            sleep((int)((Math.random()*quota*2000))+5000);

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        posizioneFinale = ++posizone;

        System.out.println(nome + " è arrivato in " + posizioneFinale + " posizione");

    }

    public String getNome(){
        return nome;
    }

    public double getQuota() {
        return quota;
    }

    public int getPosizioneFinale() {
        return posizioneFinale;
    }

}
