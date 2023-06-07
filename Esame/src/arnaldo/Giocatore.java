package arnaldo;

public class Giocatore implements Mortale{
    public static final int VITE_INIZIALI = 10;
    public static final int PUNTI_VITA_INIZIALI = 20;
    public static final int ATTACCO_INIZIALE = 5;
    private String nome;
    private int vite;
    private int puntiVita;
    private int attacco;
    private int puntiAcquisiti;
    private Nodo posizione;

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

    public void attacca(Mortale mortale) {
        mortale.subisciDanno(attacco);
    }

    public void subisciDanno(int danno) {
        puntiVita -= danno;
        if (!inVita()) {
            morte();
        }
    }

    public void modificaAttacco(int modifica) {
        attacco += modifica;
    }

    public void modificaPuntiVita(int modifica) {
        puntiVita += modifica;
    }

    public boolean inVita() {
        return puntiVita > 0;
    }

    public void morte() {
        this.vite--;
        if (this.vite == 0) {
            InterazioneUtente.dichiaraSconfitta();
        }
    }
}
