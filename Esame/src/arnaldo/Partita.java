package arnaldo;

public class Partita {
    private Mondo[] mondiDisponibili;
    private Mappa mappa;
    private Giocatore giocatore;

    public Partita(Mappa mappa, Giocatore giocatore) {
        this.mappa = mappa;
        this.giocatore = giocatore;
    }

    public Mappa getMappa() {
        return mappa;
    }

    public Giocatore getGiocatore() {
        return giocatore;
    }

    public void eseguiScontro(Mortale mostro){
        Battaglia.scontro(giocatore, mostro);
    }

}
