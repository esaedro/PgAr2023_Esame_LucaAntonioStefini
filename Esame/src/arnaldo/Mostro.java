package arnaldo;

import it.unibs.fp.mylib.EstrazioneCasuale;

/**
 * Classe che rappresenta un mostro
 */
public class Mostro implements Mortale, Posizionabile{
    public static final int PUNTI_MOSTRO_NOMRALE = 5;
    public static final int PUNTI_BOSS = 10;
    public static final int PUNTI_BOSS_FINALE = 15;
    public static final int VITA_BASE_MOSTRO_NOMRALE = 6;
    public static final int VITA_BASE_BOSS = 12;
    public static final int VITA_BASE_BOSS_FINALE = 18;
    public static final int ATTACCO_BASE_MOSTRO_NOMRALE = 1;
    public static final int ATTACCO_BASE_BOSS = 3;
    public static final int ATTACCO_BASE_BOSS_FINALE = 4;
    private int puntiVita;
    private int attacco;
    private int puntiOttenibili;

    /**
     * Costruttore del mostro
     * @param puntiVita
     * @param attacco
     * @param puntiOttenibili
     */
    public Mostro(int puntiVita, int attacco, int puntiOttenibili) {
        this.puntiVita = puntiVita;
        this.attacco = attacco;
        this.puntiOttenibili = puntiOttenibili;
    }

    public int getPuntiVita() {
        return puntiVita;
    }

    public int getAttacco() {
        return attacco;
    }

    public int getPuntiOttenibili() {
        return puntiOttenibili;
    }

    /**
     * Crea di un mostro normale
     * @return mostro normale
     */
    public static Mostro creaMostroNormale() {
        return new Mostro(VITA_BASE_MOSTRO_NOMRALE, ATTACCO_BASE_MOSTRO_NOMRALE, PUNTI_MOSTRO_NOMRALE);
    }
    
    /**
     * Crea un boss
     * @return boss
     */
    public static Mostro creaBoss() {
        int puntiVitaBoss = VITA_BASE_BOSS + EstrazioneCasuale.estraiIntero(-5, 5);
        int attaccoBoss = ATTACCO_BASE_BOSS + EstrazioneCasuale.estraiIntero(-2, 2);
        return new Mostro(puntiVitaBoss, attaccoBoss, PUNTI_BOSS);
    }

    /**
     * Crea un boss finale
     * @return boss finale
     */
    public static Mostro creaBossFinale() {
        int puntiVitaBossFinale = VITA_BASE_BOSS_FINALE + EstrazioneCasuale.estraiIntero(-5, 5);
        int attaccoBossFinale = ATTACCO_BASE_BOSS_FINALE + EstrazioneCasuale.estraiIntero(-2, 2);
        return new Mostro(puntiVitaBossFinale, attaccoBossFinale, PUNTI_BOSS_FINALE);
    }

    /**
     * Attacca un mortale
     */
    public void attacca(Mortale mortale) {
        mortale.subisciDanno(attacco);
    }

    /**
     * Applica il danno subito
     */
    public void subisciDanno(int danno) {
        puntiVita -= danno;
        if (!inVita()) {
            morte();
        }
    }

    /**
     * Controlla se il mostro è in vita
     */
    public boolean inVita() {
        return puntiVita > 0;
    }

    /**
     * Uccide il mostro (imposta i suoi punti ottenibili a 0)
     */
    public void morte() {
        puntiOttenibili = 0;
    }

    /**
     * Assegna la posizione al mostro nel nodo specificato
     */
    public void assegnaPosizione(Nodo nodo) {
        nodo.setElemento(this);
    }

    /**
     * Esegue lo scontro tra il mostro e il giocatore
     */
    public void eseguiAzione(Partita partita) {
        partita.eseguiScontro(this);
    }

    public String toString() {
        return String.format("Mostro\nPuntiVita: %d", puntiVita);
    }
}
