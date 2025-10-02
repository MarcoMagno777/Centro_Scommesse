package demo;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int scelta;

        ArrayList<MioThread> scuderia = new ArrayList<>();
        ArrayList<Persona> persone = new ArrayList<>();

        Persona p1 = new Persona("Bobo", 20);
        Persona p2 = new Persona("Marco", 20);
        Persona p3 = new Persona("Pippo", 20);

        persone.add(p1);
        //persone.add(p2);
        //persone.add(p3);

        do{

        while(true){

            System.out.println("___Menu___");
            System.out.println("1) Nuova gara");
            System.out.println("0) Esci");

            scelta = sc.nextInt();
            if(scelta == 0 || scelta == 1){
                break;
            }
            System.out.println("Scelta non valida");
        }

        if (scelta == 0) {
            break;
        }

        MioThread Cavallo1 = new MioThread("Fulmine");
        MioThread Cavallo2 = new MioThread("Tiberio");
        MioThread Cavallo3 = new MioThread("Rigolini");
        MioThread Cavallo4 = new MioThread("Birbissi");
        MioThread Cavallo5 = new MioThread("Tartufo");
        MioThread Cavallo6 = new MioThread("Napoleone");

        scuderia.add(Cavallo1);
        scuderia.add(Cavallo2);
        scuderia.add(Cavallo3);
        scuderia.add(Cavallo4);
        scuderia.add(Cavallo5);
        scuderia.add(Cavallo6);

        for (MioThread cavallo : scuderia) {
            cavallo.generaQuota();
        }

        System.out.println("________QUOTE________");
        for (int i = 0;i < scuderia.size(); i++) {
            System.out.println(i + ") " + scuderia.get(i).getNome() + " quota : " + scuderia.get(i).getQuota());
        }

        for (Persona p : persone) {
            while(true){

                System.out.println(p.getNome() + " inserisci il cavallo");
                int cavallo = sc.nextInt();
                if(cavallo >= 0 && cavallo < scuderia.size()){
                    p.setCavallo(cavallo);
                    break;
                }
                System.out.println("Scelta non valida");
            }

            while(true){
                System.out.println(p.getNome() + " inserisci quanto vuoi scommettere, conto : " + p.getConto());
                double scommessa = sc.nextInt();
                if(p.getConto() - scommessa > 0){
                    p.setScommessa(scommessa);
                    p.setConto(p.getConto() - p.getScommessa());
                    break;
                }
                System.out.println("Saldo insufficente");

            }

        }

        System.out.println("Inserisci S per avviare la gara");
        sc.next();

        System.out.println("Inizio Gara");

        for (MioThread cavallo : scuderia) {
            cavallo.start();
        }
    
        try {

            for (MioThread cavallo : scuderia) {
                cavallo.join();
            }

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("fine");

        for (Persona p : persone) {

            MioThread cavalloScomesso = scuderia.get(p.getCavallo());

            if(cavalloScomesso.getPosizioneFinale() == 1){
                p.setConto((p.getScommessa()*cavalloScomesso.getQuota()) + p.getConto());
            }

        }

        System.out.println("____Conti____");
        for (Persona p : persone){
            System.out.println(p.getNome() + " conto : " + p.getConto());
        }

        scuderia.clear();

        }while(scelta != 0);
        


    }
}