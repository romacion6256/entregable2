package entregable2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.lang.Thread.sleep;

public class PedidoProcessor implements Runnable {
    private final OrderQueue orderQueue;
    private final ReentrantReadWriteLock bloqueo = new ReentrantReadWriteLock();
    private final List<Pedido> pedidosEnProceso = new ArrayList<>();

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
                    pedidosEnProceso.add(pedido);  // Añadir pedido al batch para empaquetar

                    // 2. Si hay suficiente cantidad de pedidos o no quedan más pedidos en la cola
                    if (pedidosEnProceso.size() >= 10 || !orderQueue.tienePedidos()) {
                        for (Pedido p : pedidosEnProceso) {
                            System.out.println("Pedido en proceso: " + p.getId());
                        }
                        // 3. Empaquetar los pedidos del lote en paralelo
                        empaquetarPedidosEnParalelo(pedidosEnProceso);

                        // 4. Enviar los pedidos uno por uno (después de empaquetar)
                        pedidosEnProceso.forEach(this::enviarPedido);

                        // 5. Limpiar el batch después de empaquetar y enviar
                        pedidosEnProceso.clear();
                    }
                }
            } finally {
                bloqueo.writeLock().unlock();
            }
        }
    }

    private void procesarPago(Pedido pedido) {
        System.out.println("Procesando pago para pedido " + pedido.getId());
        // simulamos el procesamiento del pago con sleep
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Aquí es donde empaquetamos en paralelo usando parallelStream
    private void empaquetarPedidosEnParalelo(List<Pedido> pedidos) {
        // Usamos parallelStream para empaquetar cada pedido en paralelo
        pedidos.parallelStream().forEach(pedido -> {
            System.out.println("Empaquetando pedido " + pedido.getId());
            // simulación del empaquetado
        });
    }

    private void enviarPedido(Pedido pedido) {
        System.out.println("Enviando pedido " + pedido.getId());
        // simulamos el envio del pedido con sleep
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
