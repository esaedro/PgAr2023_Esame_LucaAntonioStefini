package arnaldo;

import java.util.Set;
import java.util.TreeSet;

/**
 * Classe che rappresenta un nodo della mappa
 */
public class Nodo implements Comparable<Nodo>{
    private int id;
    private Set<Nodo> collegamenti;
    private TipoDiNodo tipologia;
    private Posizionabile elemento;

    /**
     * Costruttore del nodo con il solo id
     * @param id
     */
    public Nodo(int id) {
        this.id = id;
        collegamenti = new TreeSet<Nodo>();
    }

    public Set<Nodo> getCollegamenti() {
        return collegamenti;
    }

    /**
     * Aggiunge un collegamento al nodo
     * @param nodo
     */
    public void aggiungiCollegamento(Nodo nodo) {
        collegamenti.add(nodo);
    }

    /**
     * Imposta tutti i collegamenti del nodo
     * @param collegamenti
     */
    public void setCollegamenti(Set<Nodo> collegamenti) {
        this.collegamenti = new TreeSet<Nodo>();
        this.collegamenti.addAll(collegamenti);
    }

    public TipoDiNodo getTipologia() {
        return tipologia;
    }

    public void setTipologia(TipoDiNodo tipologia){
        this.tipologia = tipologia; 
    }

    public Posizionabile getElemento() {
        return elemento;
    }

    public void setElemento(Posizionabile elemento) {
        this.elemento = elemento;
    }

    public int getId() {
        return id;
    }

    /**
     * Permette di confrontare due nodi in base all'id
     * @param nodo
     * @return 1 se l'id del nodo passato come parametro è maggiore, -1 altrimenti
     */
    public int compareTo(Nodo nodo) {
        return id < nodo.getId() ? 1 : -1;
    }

    /**
     * Rimuove un collegamento dal nodo
     * @param nodo
     */
    public void rimuoviCollegamento(Nodo nodo) {
        if (!collegamenti.isEmpty()){
            collegamenti.remove(nodo);
        }
    }

    public String toString() {
        String stringa = "Nodo " + id + "\n";
        stringa += "Tipologia: " + tipologia + "\n";
        stringa += "Collegamenti: ";

        for (Nodo nodo : collegamenti) {
            stringa += nodo.getId() + " ";
        }

        stringa += "\n";

        if (elemento != null) {
            stringa += "Elemento: " + elemento.toString() + "\n";
        }

        return stringa;
    }
}
