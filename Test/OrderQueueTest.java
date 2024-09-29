package Test;


import entregable2.OrderQueue;
import entregable2.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderQueueTest {
    private OrderQueue orderQueue;

    @BeforeEach
    public void setUp() {
        orderQueue = new OrderQueue();
    }

    @Test
    public void testAgregarYObtenerPedido() {
        OrderQueue orderQueue = new OrderQueue();

        // Crear pedidos urgente y no urgente
        Pedido pedidoUrgente = new Pedido(1, true);
        Pedido pedidoNoUrgente = new Pedido(2, false);
        Pedido pedidoNoUrgente2 = new Pedido(3, false);

        // Agregar los pedidos a la cola
        orderQueue.agregarPedido(pedidoNoUrgente);
        orderQueue.agregarPedido(pedidoUrgente);
        orderQueue.agregarPedido(pedidoNoUrgente2);

        // El primer pedido recuperado debe ser el urgente
        Pedido primerPedido = orderQueue.obtenerPedido();
        assertTrue(primerPedido.isUrgente(), "El primer pedido debe ser el urgente");

        // El segundo pedido recuperado debe ser el no urgente
        Pedido segundoPedido = orderQueue.obtenerPedido();
        assertFalse(segundoPedido.isUrgente(), "El segundo pedido debe ser el no urgente");

        // El tercer pedido recuperado debe ser el no urgente
        Pedido tercerPedido = orderQueue.obtenerPedido();
        assertFalse(tercerPedido.isUrgente(), "El tercer pedido debe ser el no urgente");
    }

    @Test
    public void testTienePedidos() {
        assertFalse(orderQueue.tienePedidos(), "La cola debe estar vacía inicialmente");

        Pedido pedido = new Pedido(1, false);
        orderQueue.agregarPedido(pedido);

        assertTrue(orderQueue.tienePedidos(), "La cola debe tener pedidos después de agregar uno");

        orderQueue.obtenerPedido(); // Consumimos el pedido
        assertFalse(orderQueue.tienePedidos(), "La cola debe estar vacía después de consumir el pedido");
    }

    @Test
    public void testObtenerPedidoSinPedidos() {
        // Intentamos obtener un pedido de una cola vacía
        Pedido resultado = orderQueue.obtenerPedido();
        assertNull(resultado, "Debería retornar null al intentar obtener de una cola vacía");
    }
}
