package entregable2;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolManager {
    private static ThreadPoolManager instancia;
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

    public ExecutorService getThreadPool() {
        return threadPool;
    }

    public void shutdown() {
        threadPool.shutdown();
    }
}