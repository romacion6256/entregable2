package entregable2;

public class Main {
    public static void main(String[] args) {
        OrderQueue orderQueue = new OrderQueue();
        ThreadPoolManager threadPoolManager = ThreadPoolManager.getInstance(1);

        // Agregar pedidos a la cola
        for (int i = 1; i <= 100; i++) {
            boolean urgente = (i % 10 == 0); // Cada décimo pedido es urgente
            Pedido pedido = new Pedido(i, urgente);
            orderQueue.agregarPedido(pedido);
        }

        // Procesar los pedidos usando múltiples hilos
        for (int i = 0; i < 10; i++) {  //preguntar esto al chat
            threadPoolManager.getThreadPool().submit(new PedidoProcessor(orderQueue));
        }

        // Cerrar el sistema después de que todos los pedidos sean procesados
        threadPoolManager.shutdown();
    }
}
