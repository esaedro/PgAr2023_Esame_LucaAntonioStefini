package arnaldo;

import java.util.Set;

public class Nodo {
    Set<Nodo> collegamenti;
    Identificativo identificativo;
    TipoDiNodo tipologia;

    public Nodo(Set<Nodo> collegamenti, Identificativo identificativo, TipoDiNodo tipologia) {
        this.collegamenti = collegamenti;
        this.identificativo = identificativo;
        this.tipologia = tipologia;
    }

}
