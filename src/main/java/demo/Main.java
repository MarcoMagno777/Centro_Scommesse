package demo;

import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int scelta;

        ArrayList<MioThread> scuderia = new ArrayList<>();
        ArrayList<Persona> persone = new ArrayList<>();

        Persona p1 = new Persona("Bobo", 15);
        Persona p2 = new Persona("Marco", 15);
        Persona p3 = new Persona("Pippo", 10);
        Persona p4 = new Persona("Maio", 20);

        persone.add(p1);
        persone.add(p2);
        persone.add(p3);
        persone.add(p4);

        do{

            System.out.println("___Menu___");
            System.out.println("1) Corsa cavalli");
            System.out.println("2) Roulette");
            System.out.println("0) Esci");

            scelta = sc.nextInt();

            switch(scelta){
                case 0:
                    break;
                case 1: 

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
                            if(p.getConto() - scommessa >= 0){
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
                    break;

                case 2:

                    Roulette r = new Roulette();

                    System.out.println("_____Tabella_____");

                    r.stampaNumeri();

                    for (Persona p : persone) {
                        do {

                            System.out.println(p.getNome() + "Su cosa vuoi scommettere");
                            System.out.println("1) Numero");
                            System.out.println("2) Pari/Dispari");
                            System.out.println("3) Colore");

                            int tipoScommessa = sc.nextInt();
                            
                            switch(tipoScommessa){
                                case 1:
                                    System.out.println("Inserisci il numero");
                                    

                            }

                            int cavallo = sc.nextInt();
                            if(cavallo >= 0 && cavallo < scuderia.size()){
                                p.setCavallo(cavallo);
                                break;
                            }
                            System.out.println("Scelta non valida");
                            System.out.println("Vuoi continuare a scommettere");
                
                        } while (true);

                        while(true){
                            System.out.println(p.getNome() + " inserisci quanto vuoi scommettere, conto : " + p.getConto());
                            double scommessa = sc.nextInt();
                            if(p.getConto() - scommessa >= 0){
                                p.setScommessa(scommessa);
                                p.setConto(p.getConto() - p.getScommessa());
                                break;
                            }
                            System.out.println("Saldo insufficente");
            
                        }
            
                    }

                    break;
                default :
                System.out.println("Scelta non valida");
            }

        

        }while(scelta != 0);
        


    }
}