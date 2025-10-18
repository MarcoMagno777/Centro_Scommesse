package demo;

import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int scelta;

        ArrayList<Persona> persone = new ArrayList<>();

        Persona p1 = new Persona("Bobo", 15);
        Persona p2 = new Persona("Marco", 15);
        Persona p3 = new Persona("Pippo", 10);
        Persona p4 = new Persona("Maio", 20);

        persone.add(p1);
        //persone.add(p2);
        //persone.add(p3);
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

                    CorsaCavalli cc = new CorsaCavalli(persone);
                    
                    break;

                case 2:

                    Roulette r = new Roulette();

                    break;
                default :
                System.out.println("Scelta non valida");
            }

        

        }while(scelta != 0);
        


    }
}