package demo;

public class Cavallo extends Thread {

    private String nome;
    private static int posizione;
    private int posizioneFinale;
    private double quotaVincente;
    private double quotaPiazzato;
    private int corsia;
    private static int lunghezzaPista;
    private static int numeroCorsie;
    private static String[][] pista;

    Cavallo(String nome, int corsia) {

        this.nome = nome;
        this.corsia = corsia;

        if (pista == null || lunghezzaPista == 0 || numeroCorsie == 0) {
            lunghezzaPista = 25;
            numeroCorsie = 6;
            pista = new String[numeroCorsie][lunghezzaPista];
        }

        inizializzaPista();
    }

    public void generaQuote() {

        quotaVincente = Math.round((3 + Math.random() * 9) * 10) / 10.0;
        quotaPiazzato = Math.round((quotaVincente / 2 + Math.random()) * 10) / 10.0; 
        posizione = 0;

    }

    private void inizializzaPista() {

        for (int i = 0; i < lunghezzaPista; i++) {
            if (i == 0) {
                pista[corsia][i] = (corsia + 1) + ")";
            } else if (i == (lunghezzaPista - 2)) {
                pista[corsia][i] = "|Fine|" + nome;
            } else if (i == (lunghezzaPista - 1)) {
                pista[corsia][i] = "";
            } else {
                pista[corsia][i] = "_";
            }
        }

    }

    public void run() {

        for (int i = 1; i < (lunghezzaPista - 2); i++) {

            stampaPista();

            try {

                sleep((int) (Math.random() * quotaVincente * 300) + 1000);

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

            pista[corsia][i] = ".";
        }

        posizioneFinale = ++posizione;
        pista[corsia][lunghezzaPista - 1] = " è arrivato " + posizioneFinale;
    }

    private void stampaPista() {

        clearScreen();

        for (int i = 0; i < numeroCorsie; i++) {
            for (int j = 0; j < lunghezzaPista; j++) {
                System.out.print(pista[i][j]);
            }
            System.out.println();
        }

    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public String getNome() {
        return nome;
    }

    public double getQuotaVincente() {
        return quotaVincente;
    }

    public double getQuotaPiazzato() {
        return quotaPiazzato;
    }

    public int getPosizioneFinale() {
        return posizioneFinale;
    }

    public int getCorsia() {
        return corsia;
    }
}
