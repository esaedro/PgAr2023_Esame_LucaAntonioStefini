package arnaldo;

import it.unibs.fp.mylib.EstrazioneCasuale;

public class Modificatore implements Posizionabile{
    private TipoDiModificatore tipologia;

    public Modificatore(TipoDiModificatore tipologia) {
        this.tipologia = tipologia;
    }
    
    public void eseguiAzione(Partita partita){
        switch (tipologia) {
            case ATTACCO -> partita.getGiocatore().modificaAttacco(EstrazioneCasuale.estraiIntero(-3, 3));
            case PUNTI_VITA -> partita.getGiocatore().modificaPuntiVita(EstrazioneCasuale.estraiIntero(-5, 10));
        }
    }

    public void assegnaPosizione(Nodo nodo) {
        nodo.setElemento(this);
    }
}
