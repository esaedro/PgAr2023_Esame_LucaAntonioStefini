package arnaldo;

import java.util.Set;

public class Nodo implements Comparable<Nodo>{
    private int id;
    private Set<Nodo> collegamenti;
    private TipoDiNodo tipologia;
    private Posizionabile elemento;
    private boolean visitato;

    public Nodo(int id) {
        this.id = id;
    }

    public Nodo(int id, Set<Nodo> collegamenti, TipoDiNodo tipologia) {
        this.id = id;
        this.collegamenti = collegamenti;
        this.tipologia = tipologia;
        this.visitato = false;
    }

    public Set<Nodo> getCollegamenti() {
        return collegamenti;
    }

    public void setCollegamenti(Set<Nodo> collegamenti) {
        this.collegamenti = collegamenti;
    }

    public TipoDiNodo getTipologia() {
        return tipologia;
    }

    public Posizionabile getElemento() {
        return elemento;
    }

    public void setElemento(Posizionabile elemento) {
        this.elemento = elemento;
    }

    public void eseguiAzioneElemento(Partita partita) {
        elemento.eseguiAzione(partita);
    }

    public boolean getVisitato() {
        return visitato;
    }

    public void cambiaVisitato() {
        visitato = true;
    }

    public int getId() {
        return id;
    }

    public int compareTo(Nodo nodo) {
        return id < nodo.getId() ? 1 : -1;
    }
}
