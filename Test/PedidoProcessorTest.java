package Test;

import entregable2.OrderQueue;
import entregable2.Pedido;
import entregable2.PedidoProcessor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoProcessorTest {

    @Test
    void testProcesarPedidos() throws InterruptedException {
        // Creamos una cola de pedidos y agregamos algunos pedidos
        OrderQueue orderQueue = new OrderQueue();
        Pedido pedido1 = new Pedido(1, false);
        Pedido pedido2 = new Pedido(2, true);
        Pedido pedido3 = new Pedido(3, false);

        orderQueue.agregarPedido(pedido1);
        orderQueue.agregarPedido(pedido2);
        orderQueue.agregarPedido(pedido3);

        // Creamos el PedidoProcessor
        PedidoProcessor processor = new PedidoProcessor(orderQueue);

        // Creamos un hilo para procesar los pedidos
        Thread processorThread = new Thread(processor);
        processorThread.start();

        // Esperamos un tiempo para que los pedidos se procesen
        processorThread.join();

        // Verificamos que la cola esté vacía después del procesamiento
        assertFalse(orderQueue.tienePedidos(), "La cola debería estar vacía después del procesamiento.");
    }

    @Test
    void testProcesarPedidoUnico() throws InterruptedException {
        // Creamos una cola de pedidos con un único pedido
        OrderQueue orderQueue = new OrderQueue();
        Pedido pedido = new Pedido(1, true);
        orderQueue.agregarPedido(pedido);

        // Creamos el PedidoProcessor
        PedidoProcessor processor = new PedidoProcessor(orderQueue);

        // Creamos un hilo para procesar el pedido
        Thread processorThread = new Thread(processor);
        processorThread.start();

        // Esperamos a que termine el procesamiento
        processorThread.join();

        // Verificamos que la cola esté vacía
        assertFalse(orderQueue.tienePedidos(), "La cola debería estar vacía después de procesar un único pedido.");
    }

    @Test
    void testPedidosProcesadosEnBatch() throws InterruptedException {
        // Creamos una cola con 10 pedidos
        OrderQueue orderQueue = new OrderQueue();
        for (int i = 1; i <= 10; i++) {
            orderQueue.agregarPedido(new Pedido(i, false));
        }
        // Creamos el PedidoProcessor
        PedidoProcessor processor = new PedidoProcessor(orderQueue);

        // Ejecutamos el procesador en un hilo
        Thread processorThread = new Thread(processor);
        processorThread.start();

        // Esperamos a que termine el procesamiento
        processorThread.join();

        // Verificamos que la cola esté vacía
        assertFalse(orderQueue.tienePedidos(), "La cola debería estar vacía después de procesar el batch de 10 pedidos.");
    }
}
