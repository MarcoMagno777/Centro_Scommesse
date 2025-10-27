package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CorsaCavalli {

    private Scanner sc;
    private ArrayList<Cavallo> scuderia;
    private ArrayList<Persona> persone;

    private Cavallo Cavallo1 = new Cavallo("Fulmine", 0);
    private Cavallo Cavallo2 = new Cavallo("Tiberio", 1);
    private Cavallo Cavallo3 = new Cavallo("Rigolini", 2);
    private Cavallo Cavallo4 = new Cavallo("Birbissi", 3);
    private Cavallo Cavallo5 = new Cavallo("Tartufo", 4);
    private Cavallo Cavallo6 = new Cavallo("Napoleone", 5);

    CorsaCavalli(ArrayList<Persona> persone) {

        sc = new Scanner(System.in);
        scuderia = new ArrayList<>();
        this.persone = persone;

        clearScreen();
        aggiungiCavalli();
        generaQuote();
        stampaQuote();
        inserimentoScommesse();
        gara();
        pagaScommesse();
        stampaConti();
        eliminaGiocatore();
        resetScuderia();

    }

    private void aggiungiCavalli() {

        scuderia.add(Cavallo1);
        scuderia.add(Cavallo2);
        scuderia.add(Cavallo3);
        scuderia.add(Cavallo4);
        scuderia.add(Cavallo5);
        scuderia.add(Cavallo6);

    }

    private void generaQuote() {

        for (Cavallo cavallo : scuderia) {
            cavallo.generaQuote();
        }

    }

    private void stampaQuote() {

        System.out.println("________QUOTE________");
        for (int i = 0; i < scuderia.size(); i++) {
            Cavallo c = scuderia.get(i);
            System.out.println((i + 1) + ") " + c.getNome() +
                    " | Vincente: " + c.getQuotaVincente() +
                    " | Piazzato: " + c.getQuotaPiazzato());
        }

    }

    private void inserimentoScommesse() {

        for (Persona p : persone) {

            while (true) {
                System.out.println(p.getNome() + ", scegli tipo scommessa: [1] Vincente  [2] Piazzato 2 : ");
                int scelta = sc.nextInt();
                if (scelta == 1) {
                    p.setTipoScommessa("vincente");
                    break;
                } else if (scelta == 2) {
                    p.setTipoScommessa("piazzato");
                    break;
                } else {
                    System.out.println("Scelta non valida");
                }
            }

            while (true) {
                System.out.println(p.getNome() + ", scegli il cavallo :");
                int cavallo = sc.nextInt() - 1;
                if (cavallo >= 0 && cavallo < scuderia.size()) {
                    p.setScommessa(cavallo);
                    break;
                }
                System.out.println("Scelta non valida");
            }

            while (true) {
                System.out.println(p.getNome() + ", quanto vuoi scommettere : conto : " + p.getConto() + " :");
                double scommessa = sc.nextDouble();
                if (p.getConto() - scommessa >= 0) {
                    p.setSaldoScommesso(scommessa);
                    p.setConto(p.getConto() - p.getSaldoScommesso());
                    break;
                }
                System.out.println("Saldo insufficiente");
            }

        }
    }

    private void gara() {

        System.out.println("Premi INVIO per avviare la gara...");
        sc.nextLine(); 
        sc.nextLine();
        System.out.println("Inizio Gara!");

        for (Cavallo cavallo : scuderia) {
            cavallo.start();
        }

        try {
            for (Cavallo cavallo : scuderia) {
                cavallo.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Fine Gara!");

    }

    private void pagaScommesse() {

        for (Persona p : persone) {

            Cavallo cavalloScommesso = scuderia.get(p.getScommessa());
            int posizione = cavalloScommesso.getPosizioneFinale();
            double vincita = 0;

            if (p.getTipoScommessa().equals("vincente") && posizione == 1) {

                vincita = p.getSaldoScommesso() * cavalloScommesso.getQuotaVincente();
                p.setConto(p.getConto() + vincita);

            } else if ((p.getTipoScommessa().equals("piazzato") && posizione == 2) || (p.getTipoScommessa().equals("piazzato") && posizione == 1)) {

                vincita = p.getSaldoScommesso() * cavalloScommesso.getQuotaPiazzato();
                p.setConto(p.getConto() + vincita);

            } 
        }

    }

    private void stampaConti() {

        System.out.println("____Conti Finali____");
        for (Persona p : persone) {
            System.out.println(p.getNome() + " conto: " + p.getConto());
        }

    }

    private void eliminaGiocatore() {

        List<Persona> toRemove = new ArrayList<>();
        for (Persona p : persone) {
            if (p.getConto() == 0) {
                toRemove.add(p);
            }
        }
        persone.removeAll(toRemove);
    }

    private void clearScreen() {
        
    try {

        String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("windows")) {
                
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }

        } catch (Exception e) {

        e.printStackTrace();
        
        }
    }


    private void resetScuderia() {
        scuderia.clear();
    }
}
