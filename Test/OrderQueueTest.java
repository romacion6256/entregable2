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
        Pedido pedido1 = new Pedido(1, false);
        Pedido pedido2 = new Pedido(2, true); // Urgente
        Pedido pedido3 = new Pedido(3, false);

        orderQueue.agregarPedido(pedido1);
        orderQueue.agregarPedido(pedido2);
        orderQueue.agregarPedido(pedido3);

        assertEquals(pedido2, orderQueue.obtenerPedido(), "El primer pedido debe ser el urgente");
        assertEquals(pedido1, orderQueue.obtenerPedido(), "El segundo pedido debe ser el no urgente");
        assertEquals(pedido3, orderQueue.obtenerPedido(), "El tercer pedido debe ser el otro no urgente");
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
