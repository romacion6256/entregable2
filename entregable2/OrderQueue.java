package entregable2;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class OrderQueue {
    // Cola de pedidos con prioridad
    private final BlockingQueue<Pedido> orderQueue = new PriorityBlockingQueue<>(100, (o1, o2) -> Boolean.compare(o2.isUrgente(), o1.isUrgente()));

    // Agregar un pedido a la cola
    public void agregarPedido(Pedido pedido) {
        try {
            orderQueue.put(pedido); // Añade pedidos a la cola
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Obtener un pedido de la cola
    public Pedido obtenerPedido() {
        return orderQueue.poll(); // Toma pedidos de la cola y devuelve null si está vacía
    }

    // Verificar si la cola tiene pedidos
    public boolean tienePedidos() {
        return !orderQueue.isEmpty();
    }
}