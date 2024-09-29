package entregable2;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolManager {
    private static ThreadPoolManager instancia; // Singleton
    private final ExecutorService threadPool;

    private ThreadPoolManager(int poolSize) {
        threadPool = Executors.newFixedThreadPool(poolSize);
    }

    public static synchronized ThreadPoolManager getInstance(int poolSize) {
        if (instancia == null) {
            instancia = new ThreadPoolManager(poolSize);
        }
        return instancia;
    }

    // Getter para el ThreadPool
    public ExecutorService getThreadPool() {
        return threadPool;
    }

    // Método para cerrar el ThreadPool
    public void shutdown() {
        threadPool.shutdown();
    }
}