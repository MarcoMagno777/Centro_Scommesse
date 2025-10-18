package demo;

import java.util.ArrayList;
import java.util.Scanner;

public class CorsaCavalli {

    Scanner sc = new Scanner(System.in);

    ArrayList<MioThread> scuderia = new ArrayList<>();
    ArrayList<Persona> persone = new ArrayList<>();
    
    MioThread Cavallo1 = new MioThread("Fulmine");
    MioThread Cavallo2 = new MioThread("Tiberio");
    MioThread Cavallo3 = new MioThread("Rigolini");
    MioThread Cavallo4 = new MioThread("Birbissi");
    MioThread Cavallo5 = new MioThread("Tartufo");
    MioThread Cavallo6 = new MioThread("Napoleone");

    CorsaCavalli(ArrayList<Persona> persone){

        this.persone = persone;
        
        aggingiCavalli();
        generaQuota();
        stampaQuote();
        inserimentoScommesse();
        gara();
        pagaScommesse();
        stampaConti();
        resetScuderia();

    }

    private void aggingiCavalli(){

        scuderia.add(Cavallo1);
        scuderia.add(Cavallo2);
        scuderia.add(Cavallo3);
        scuderia.add(Cavallo4);
        scuderia.add(Cavallo5);
        scuderia.add(Cavallo6);

    }

    private void generaQuota(){

        for (MioThread cavallo : scuderia) {
            cavallo.generaQuota();
        }

    }

    private void stampaQuote(){

        System.out.println("________QUOTE________");

        for (int i = 0;i < scuderia.size(); i++) {
            System.out.println(i + ") " + scuderia.get(i).getNome() + " quota : " + scuderia.get(i).getQuota());
        }

    }

    private void inserimentoScommesse(){

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

    }
    
    private void gara(){

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

    }

    private void pagaScommesse(){
        
        for (Persona p : persone) {

            MioThread cavalloScomesso = scuderia.get(p.getCavallo());
    
            if(cavalloScomesso.getPosizioneFinale() == 1){
                p.setConto((p.getScommessa()*cavalloScomesso.getQuota()) + p.getConto());
            }
    
        }

    }
    
    private void stampaConti(){

        System.out.println("____Conti____");
        for (Persona p : persone){
            System.out.println(p.getNome() + " conto : " + p.getConto());
        }

    }
    
    private void resetScuderia(){
        scuderia.clear();
    }

}

