package arnaldo;

import it.unibs.fp.mylib.EstrazioneCasuale;

/**
 * Classe che rappresenta un mondo di gioco.
 * Ha un nome, una mappa e un punteggio ottenibile se completato interamente.
 *
 */
public class Mondo {
    private String nome;
    private int puntiOttenibili;
    private Mappa mappa;

    /**
     * Costruttore di Mondo
     * @param nome
     */
    public Mondo(String nome) {
        this.nome = nome;
        Mappa mappa = new Mappa();
        if (EstrazioneCasuale.estraiIntero(0, 1) == 1) {
            mappa.generaMappaBase();
        }
        else {
            mappa.generaMappaCasuale();
        }
        this.mappa = mappa;
    }

    public String getNome() {
        return nome;
    }

    public int getPuntiOttenibili() {
        return puntiOttenibili;
    }

    /**
     * Permette di entrare nel mondo
     * @param partita
     */
    public void entra(Partita partita) {
        mappa.prosegui(partita);
    }

    public Mappa getMappa() {
        return mappa;
    }
}
