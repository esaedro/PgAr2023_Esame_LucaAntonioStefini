package arnaldo;

import it.unibs.fp.mylib.EstrazioneCasuale;

/**
 * Classe che rappresenta un modificatore di statistiche
 */
public class Modificatore implements Posizionabile{
    private TipoDiModificatore tipologia;

    /**
     * Costruttore del modificatore
     * @param tipologia
     */
    public Modificatore(TipoDiModificatore tipologia) {
        this.tipologia = tipologia;
    }
    
    /**
     * Esegue l'azione del modificatore in base alla sua tipologia
     */
    public void eseguiAzione(Partita partita){
        int incremento;
        switch (tipologia) {
            case ATTACCO:
                incremento = EstrazioneCasuale.estraiIntero(-3, 3);
                partita.getGiocatore().modificaAttacco(incremento);
                InterazioneUtente.mostraIncrementoAttacco(incremento, partita.getGiocatore());
                break;

            case PUNTI_VITA:
                incremento = EstrazioneCasuale.estraiIntero(-5, 10);
                partita.getGiocatore().modificaPuntiVita(incremento);
                InterazioneUtente.mostraIncrementoPuntiVita(incremento, partita.getGiocatore());
                break;

            default:
        }
    }

    /**
     * Assegna la posizione al modificatore
     */
    public void assegnaPosizione(Nodo nodo) {
        nodo.setElemento(this);
    }

    public String toString() {
        return "Modificatore: " + tipologia + "\n";
    }
}
