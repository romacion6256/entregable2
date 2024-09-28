package Test;

import entregable2.ThreadPoolManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;


import static org.junit.jupiter.api.Assertions.*;

public class ThreadPoolManagerTest {
    private static ThreadPoolManager threadPoolManager;

    @BeforeAll
    public static void setUp() {
        threadPoolManager = ThreadPoolManager.getInstance(5); // Crear una instancia con un tamaño de pool de 5
    }

    @Test
    public void testGetInstance() {
        ThreadPoolManager anotherInstance = ThreadPoolManager.getInstance(10);
        assertSame(threadPoolManager, anotherInstance, "Se debe devolver la misma instancia del ThreadPoolManager");
    }

    @Test
    public void testGetThreadPool() {
        ExecutorService executorService = threadPoolManager.getThreadPool();
        assertNotNull(executorService, "El ExecutorService no debe ser nulo");
    }

    @Test
    public void testShutdown() {
        threadPoolManager.shutdown();
        assertTrue(threadPoolManager.getThreadPool().isShutdown(), "El thread pool debería estar cerrado");
    }
}
