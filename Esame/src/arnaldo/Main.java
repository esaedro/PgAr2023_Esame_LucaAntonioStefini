package arnaldo;

public class Main {
    public static void main(String[] args) {
        Partita partita = new Partita(new Mappa(new Mondo("Base")), new Giocatore("G1"));
        partita.getMappa().generaMappaBase();
        partita.getMappa().prosegui();
    }
}
