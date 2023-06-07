package arnaldo;

/**
 * Classe che gestisce una partita del gioco
 */
public class Partita {
    private Mondo mondoScelto;
    private Giocatore giocatore;

    /**
     * Costruttore di Partita
     * @param mondoScelto
     * @param giocatore
     */
    public Partita(Mondo mondoScelto, Giocatore giocatore) {
        this.mondoScelto = mondoScelto;
        this.giocatore = giocatore;
    }

    public Mondo getMondoScelto() {
        return mondoScelto;
    }

    public Giocatore getGiocatore() {
        return giocatore;
    }

    /**
     * Permette di eseguire uno scontro tra il giocatore e un mostro
     * @param mostro
     */
    public void eseguiScontro(Mostro mostro){
        if (Battaglia.scontro(giocatore, mostro)) {
            giocatore.incrementaPuntiAcquisiti(mostro.getPuntiOttenibili());
        }
    }

}
