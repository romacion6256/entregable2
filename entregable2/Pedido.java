package entregable2;

public class Pedido {
    private final int id;
    private final boolean urgente;

    public Pedido(int id, boolean urgente) {
        this.id = id;
        this.urgente = urgente;
    }

    public int getId() {
        return id;
    }

    public boolean isUrgente() {
        return urgente;
    }
}