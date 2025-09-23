package demo;

public class MioThread extends Thread{
    
    private String nome;
    private static int posizone = 0;

    MioThread(String nome){
        this.nome = nome;
    }

    public void run(){

        try {
            sleep((int)(Math.random()*20000));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        posizone ++;

        System.out.println(nome + " è arrivato in " + posizone + " posizione");

    }

    public String getNome(){
        return nome;
    }

}
