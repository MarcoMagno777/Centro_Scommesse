package demo;

import java.util.ArrayList;

public class Roulette {
    
    private ArrayList<Numeri> numeri;

    public Roulette() {
        numeri = new ArrayList<>();

        // Schema dei numeri rossi nella roulette europea
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

    // Per stampare i numeri della roulette
    public void stampaNumeri() {
        for (Numeri n : numeri) {
            System.out.println(n.getNumero() + ", " + n.getColore());
        }
    }
}