package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Roulette {

    private Scanner sc;
    private ArrayList<Numeri> numeri;
    private ArrayList<Persona> persone;

    private int numeroVincente;
    private String coloreVincente;
    private boolean pariVincente;

    public Roulette(ArrayList<Persona> persone) {

        sc = new Scanner(System.in);
        numeri = new ArrayList<>();
        this.persone = persone;

        clearScreen();
        assegnaNumeri();
        stampaQuote();
        inserimentoScommesse();
        giraRuota();
        pagaScommesse();
        stampaConti();
        eliminaGiocatore();
        resetNumeri();
        
    }

    private void assegnaNumeri() {

        int[] numeriRossi = {
            1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36
        };

        for (int i = 0; i <= 36; i++) {
            boolean pari = (i % 2 == 0);
            String colore;

            if (i == 0) {
                colore = "verde";
            } else if (contains(numeriRossi, i)) {
                colore = "rosso";
            } else {
                colore = "nero";
            }

            numeri.add(new Numeri(i, pari, colore));
        }
    }

    private boolean contains(int[] array, int value) {
        for (int i : array) {
            if (i == value) return true;
        }
        return false;
    }

    private void stampaQuote() {
        System.out.println("________QUOTE ROULETTE________");
        System.out.println("1) Numero esatto = quota 35");
        System.out.println("2) Colore (rosso/nero) = quota 2");
        System.out.println("3) Pari o Dispari = quota 2");
        System.out.println();
    }

    private void inserimentoScommesse() {

        for (Persona p : persone) {

            System.out.println(p.getNome() + ", scegli il tipo di scommessa:");
            System.out.println("1) Numero");
            System.out.println("2) Colore");
            System.out.println("3) Pari/Dispari");
            int tipo = sc.nextInt();
            sc.nextLine();

            switch (tipo) {
                case 1 : 
                    System.out.println("Inserisci il numero (0-36): ");
                    int numero = sc.nextInt();
                    p.setScommessa(numero);  
                    sc.nextLine();
                    break;
                case 2 :
                    System.out.println("Inserisci 1 per Rosso o 2 per Nero:");
                    int colore = sc.nextInt();
                    p.setScommessa(colore == 1 ? 100 : 200);
                    sc.nextLine();
                    break;
                case 3 :
                    System.out.println("Inserisci 1 per Pari o 2 per Dispari:");
                    int pd = sc.nextInt();
                    p.setScommessa(pd == 1 ? 300 : 400);
                    sc.nextLine();
                    break;
                default:
                    System.out.println("Scelta non valida");
                    break;
            }

            while (true) {
                System.out.println(p.getNome() + " inserisci quanto vuoi scommettere, conto: " + p.getConto());
                double scommessa = sc.nextDouble();
                if (p.getConto() - scommessa >= 0) {
                    p.setSaldoScommesso(scommessa);
                    p.setConto(p.getConto() - scommessa);
                    break;
                }
                System.out.println("Saldo insufficiente.");
            }
        }
    }

    private void giraRuota() {
        System.out.println("\nPremi INVIO per girare la ruota...");
        sc.nextLine();
        System.out.print("La ruota sta girando");

        for (int i = 0; i < 7; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(".");
        }

        System.out.println();
        
        Random rnd = new Random();
        numeroVincente = rnd.nextInt(37);
        Numeri n = numeri.get(numeroVincente);
        coloreVincente = n.getColore();
        pariVincente = n.getPari();

        System.out.println("Numero vincente: " + numeroVincente + " (" + coloreVincente + ")");
    }

    private void pagaScommesse() {
    
        for (Persona p : persone) {
    
            double quota = 0;
    
            int s = p.getScommessa();
    
            if (s >= 0 && s <= 36) {
                if (s == numeroVincente) {
                    quota = 35;
                }
    
            
            } else if (s == 100 || s == 200) {
                String coloreScelto = (s == 100) ? "rosso" : "nero";
                if (coloreScelto.equalsIgnoreCase(coloreVincente)) {
                    quota = 2;
                }
    
            
            } else if (s == 300 || s == 400) {
                boolean pariScelto = (s == 300);
                if (numeroVincente != 0 && pariScelto == pariVincente) {
                    quota = 2;
                }
            }
    
            if (quota > 0) {
                p.setConto((p.getSaldoScommesso() * quota) + p.getConto());
            }
        }
    }
    

    private void stampaConti() {
        System.out.println("\n____Conti____");
        for (Persona p : persone) {
            System.out.println(p.getNome() + " conto: " + p.getConto() + "€");
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

    private void resetNumeri() {
        numeri.clear();
    }
}
