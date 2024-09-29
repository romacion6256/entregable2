package entregable2;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class OrderQueue {
    private final BlockingQueue<Pedido> orderQueue = new PriorityBlockingQueue<>(100, (o1, o2) -> Boolean.compare(o2.isUrgente(), o1.isUrgente()));

    public void agregarPedido(Pedido pedido) {
        try {
            orderQueue.put(pedido); // Añade pedidos a la cola
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Pedido obtenerPedido() {
        return orderQueue.poll(); // Toma pedidos de la cola y devuelve null si está vacía
    }

    public boolean tienePedidos() {
        return !orderQueue.isEmpty();
    }
}