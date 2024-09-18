package entregable2;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PedidoProcessor implements Runnable {
    private final OrderQueue orderQueue;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public PedidoProcessor(OrderQueue orderQueue) {
        this.orderQueue = orderQueue;
    }

    @Override
    public void run() {
        while (orderQueue.hasOrders()) {
            lock.writeLock().lock();
            try {
                Pedido pedido = orderQueue.getOrder();
                if (pedido != null) {
                    procesarPago(pedido);
                    empaquetarPedido(pedido);
                    enviarPedido(pedido);
                }
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    private void procesarPago(Pedido pedido) {
        System.out.println("Procesando pago para pedido " + pedido.getId());
        // Simula el procesamiento de pago
    }

    private void empaquetarPedido(Pedido pedido) {
        System.out.println("Empaquetando pedido " + pedido.getId());
        // Simula el empaquetado del pedido
    }

    private void enviarPedido(Pedido pedido) {
        System.out.println("Enviando pedido " + pedido.getId());
        // Simula el envío del pedido
    }
}