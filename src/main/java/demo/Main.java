package demo;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int scelta;

        ArrayList<Persona> persone = new ArrayList<>();

        do{

            System.out.println("___Menu___");
            System.out.println("1) Inserisci giocatore");
            System.out.println("2) Corsa cavalli");
            System.out.println("3) Roulette");
            System.out.println("0) Esci");

            scelta = sc.nextInt();

            switch(scelta){
                case 0:
                    break;
                case 1: 

                    Persona p = new Persona();
                    System.out.println("Inserisci nome giocatore : ");  
                    p.setNome(sc.nextLine());
                    System.out.println("Inserisci il saldo : ");
                    p.setConto(sc.nextInt());
                    persone.add(p);
                    break;

                case 2:

                    CorsaCavalli cc = new CorsaCavalli(persone);  
                    break;

                case 3: 

                    Roulette r = new Roulette(persone);
                    break;
                    
                default :
                System.out.println("Scelta non valida");
            }


        }while(scelta != 0);

    }
}