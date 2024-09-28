package entregable2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PedidoProcessor implements Runnable {
    private final OrderQueue orderQueue;
    private final ReentrantReadWriteLock bloqueo = new ReentrantReadWriteLock();
    private final List<Pedido> pedidosBatch = new ArrayList<>();

    public PedidoProcessor(OrderQueue orderQueue) {
        this.orderQueue = orderQueue;
    }

    @Override
    public void run() {
        while (orderQueue.tienePedidos()) {
            bloqueo.writeLock().lock();
            try {
                Pedido pedido = orderQueue.obtenerPedido();
                if (pedido != null) {
                    // 1. Procesar el pago del pedido
                    procesarPago(pedido);
                    pedidosBatch.add(pedido);  // Añadir pedido al batch para empaquetar

                    // 2. Si hay suficiente cantidad de pedidos o no quedan más pedidos en la cola
                    if (pedidosBatch.size() >= 3 || !orderQueue.tienePedidos()) {
                        // 3. Empaquetar los pedidos del lote en paralelo
                        for (Pedido p : pedidosBatch) {
                            System.out.println("Pedido en batch: " + p.getId());
                        }

                        empaquetarPedidosEnParalelo(pedidosBatch);

                        // 4. Enviar los pedidos uno por uno (después de empaquetar)
                        pedidosBatch.forEach(this::enviarPedido);

                        // 5. Limpiar el batch después de empaquetar y enviar
                        pedidosBatch.clear();
                    }
                }
            } finally {
                bloqueo.writeLock().unlock();
            }
        }
    }

    private void procesarPago(Pedido pedido) {
        System.out.println("Procesando pago para pedido " + pedido.getId());
        // Simulación del procesamiento de pago
    }

    // Aquí es donde empaquetamos en paralelo usando parallelStream
    private void empaquetarPedidosEnParalelo(List<Pedido> pedidos) {
        System.out.println("Empaquetando " + pedidos.size() + " pedidos en paralelo...");
        // Usamos parallelStream para empaquetar cada pedido en paralelo
        pedidos.parallelStream().forEach(pedido -> {
            System.out.println("Empaquetando pedido " + pedido.getId());
            // Simulación del empaquetado
        });
    }

    private void enviarPedido(Pedido pedido) {
        System.out.println("Enviando pedido " + pedido.getId());
        // Simulación del envío del pedido
    }
}
