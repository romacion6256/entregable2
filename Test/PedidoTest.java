package Test;

import entregable2.Pedido;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PedidoTest {

    @Test
    public void testCrearPedido() {
        Pedido pedido = new Pedido(1, true);

        assertEquals(1, pedido.getId(), "El ID del pedido debe ser 1");
        assertTrue(pedido.isUrgente(), "El pedido debe ser urgente");
    }

    @Test
    public void testPedidoNoUrgente() {
        Pedido pedido = new Pedido(2, false);

        assertEquals(2, pedido.getId(), "El ID del pedido debe ser 2");
        assertFalse(pedido.isUrgente(), "El pedido no debe ser urgente");
    }
}
