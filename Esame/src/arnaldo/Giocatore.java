package arnaldo;

/**
 * Classe che rappresenta il giocatore
 */
public class Giocatore implements Mortale{
    public static final int VITE_INIZIALI = 10;
    public static final int PUNTI_VITA_INIZIALI = 20;
    public static final int ATTACCO_INIZIALE = 5;
    private String nome;
    private int vite;
    private int puntiVita;
    private int attacco;
    private int puntiAcquisiti;

    /**
     * Costruttore del giocatore
     * @param nome
     */
    public Giocatore(String nome) {
        this.vite = VITE_INIZIALI;
        this.nome = nome;
        this.puntiVita = PUNTI_VITA_INIZIALI;
        this.attacco = ATTACCO_INIZIALE;
    }

    public int getVite() {
        return vite;
    }

    public int getPuntiVita() {
        return puntiVita;
    }

    public int getAttacco() {
        return attacco;
    }

    public void perdiVita() {
        this.vite--;
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
     * Modifica l'attacco del giocatore
     * @param modifica
     */
    public void modificaAttacco(int modifica) {
        attacco += modifica;
    }

    /**
     * Modifica i punti vita del giocatore
     * @param modifica
     */
    public void modificaPuntiVita(int modifica) {
        puntiVita += modifica;
    }

    /**
     * Controlla se il giocatore è in vita
     */
    public boolean inVita() {
        return puntiVita > 0;
    }

    /**
     * Uccide il giocatore (gli toglie una vita)
     */
    public void morte() {
        this.vite--;
        if (this.vite == 0) {
            InterazioneUtente.dichiaraSconfitta(this);
        }
    }

    
    public String toString() {
        return String.format("%s\nPuntiVita: %d", nome, puntiVita);
    }

    /**
     * Incrementa i punti acquisiti del giocatore
     * @param puntiOttenuti
     */
    public void incrementaPuntiAcquisiti(int puntiOttenuti) {
        puntiAcquisiti += puntiOttenuti;
    }

    public int getPuntiAcquisiti() {
        return puntiAcquisiti;
    }
}
