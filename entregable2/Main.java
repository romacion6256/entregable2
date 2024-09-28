package entregable2;

import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {
        int poolSize = 3;
        OrderQueue orderQueue = new OrderQueue();
        ThreadPoolManager threadPoolManager = ThreadPoolManager.getInstance(poolSize);

        // Agregar pedidos a la cola
        for (int i = 1; i <= 100; i++) {
            boolean urgente = (i % 10 == 0); // Cada décimo pedido es urgente
            Pedido pedido = new Pedido(i, urgente);
            orderQueue.agregarPedido(pedido);
        }

        // Contador para esperar a que se procesen todos los pedidos
        CountDownLatch latch = new CountDownLatch(poolSize);

        // Medir el tiempo de inicio
        long startTime = System.currentTimeMillis();

        // Procesar los pedidos usando múltiples hilos
        for (int i = 0; i < poolSize; i++) {
            threadPoolManager.getThreadPool().submit(() -> {
                try {
                    new PedidoProcessor(orderQueue).run(); // Ejecutar el procesador de pedidos
                } finally {
                    latch.countDown(); // Disminuir el contador al finalizar
                }
            });
        }

        // Esperar a que todos los hilos terminen
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Medir el tiempo de fin
        long endTime = System.currentTimeMillis();

        // Calcular la duración total en milisegundos
        long duration = endTime - startTime;
        System.out.println("Tiempo total de procesamiento de pedidos: " + duration + " ms");

        // Cerrar el sistema
        threadPoolManager.shutdown();
    }
}
