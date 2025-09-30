package demo;

public class Main {
    public static void main(String[] args) {

        Persona p1 = new Persona(null, 0);
        Persona p2 = new Persona(null, 0);
        Persona p3 = new Persona(null, 0);

        do{
        MioThread Cavallo1 = new MioThread("Fulmine");
        MioThread Cavallo2 = new MioThread("Tiberio");
        MioThread Cavallo3 = new MioThread("Rigolini");
        MioThread Cavallo4 = new MioThread("Birbissi");

        System.out.println("Inizio Gara");

        Cavallo1.start();
        Cavallo2.start();
        Cavallo3.start();
        Cavallo4.start();

        try {
            Cavallo1.join();
            Cavallo2.join();
            Cavallo3.join();
            Cavallo4.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("fine");

        }while(p1.getConto() != 0 && p2.getConto() != 0 && p3.getConto() != 0);
        


    }
}