package demo;

import java.util.ArrayList;
import java.util.Scanner;

public class CorsaCavalli {

    private Scanner sc;

    private ArrayList<Cavallo> scuderia;
    private ArrayList<Persona> persone;
    
    private Cavallo Cavallo1 = new Cavallo("Fulmine");
    private Cavallo Cavallo2 = new Cavallo("Tiberio");
    private Cavallo Cavallo3 = new Cavallo("Rigolini");
    private Cavallo Cavallo4 = new Cavallo("Birbissi");
    private Cavallo Cavallo5 = new Cavallo("Tartufo");
    private Cavallo Cavallo6 = new Cavallo("Napoleone");

    CorsaCavalli(ArrayList<Persona> persone){

        sc = new Scanner(System.in);

        scuderia = new ArrayList<>();
        this.persone = persone;
        
        aggingiCavalli();
        generaQuota();
        stampaQuote();
        inserimentoScommesse();
        gara();
        pagaScommesse();
        stampaConti();
        eliminaGiocatore();
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

        for (Cavallo cavallo : scuderia) {
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
                    p.setScommessa(cavallo);
                    break;
                }
                System.out.println("Scelta non valida");
            }
    
            while(true){
                System.out.println(p.getNome() + " inserisci quanto vuoi scommettere, conto : " + p.getConto());
                double scommessa = sc.nextInt();
                if(p.getConto() - scommessa >= 0){
                    p.setSaldoScommesso(scommessa);
                    p.setConto(p.getConto() - p.getSaldoScommesso());
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
    
        for (Cavallo cavallo : scuderia) {
            cavallo.start();
        }
    
        try {
    
            for (Cavallo cavallo : scuderia) {
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

            Cavallo cavalloScomesso = scuderia.get(p.getScommessa());
    
            if(cavalloScomesso.getPosizioneFinale() == 1){
                p.setConto((p.getSaldoScommesso()*cavalloScomesso.getQuota()) + p.getConto());
            }
    
        }

    }
    
    private void stampaConti(){

        System.out.println("____Conti____");
        for (Persona p : persone){
            System.out.println(p.getNome() + " conto : " + p.getConto());
        }

    }

    private void eliminaGiocatore(){
        for (Persona p : persone) {
            if(p.getConto() == 0){
                persone.remove(p);
            }
        }
    }
    
    private void resetScuderia(){
        scuderia.clear();
    }

}

