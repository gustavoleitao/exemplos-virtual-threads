import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class VirtualThreds {

    private AtomicInteger counter = new AtomicInteger(0);

    public Runnable createTask(){
        return () -> {
            try {
                int id = counter.incrementAndGet();
                long timeout = (long) ((Math.random() * 10000) + 100);
                System.out.println(String.format("Task %d executing for %s", id, timeout));
                Thread.sleep(timeout);
                System.out.println(String.format("Task %d done!", id));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }

    public void executeTask(int qnt, ExecutorService executor) throws InterruptedException {
        try (ExecutorService e = executor) {
            for (int i=0;i<qnt;i++){
                e.execute(createTask());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        VirtualThreds virtualThreds = new VirtualThreds();
        ExecutorService executor = Executors.newFixedThreadPool(400);
//        ExecutorService executorVirtual = Executors.newVirtualThreadPerTaskExecutor();
        Instant start = Instant.now();
        virtualThreds.executeTask(1000, executor);
        Instant end = Instant.now();
        long seconds = ChronoUnit.SECONDS.between(start, end);
        System.out.println(String.format("Done in %d seconds", seconds));

        // Done in 57 seconds with 100 threds
        // Done in 31 seconds with 200 threads
        // Done in 18 seconds with 400 threads
        // Done in 12 seconds with 800 threads

        //Virtuais
        // Done in 10 seconds

    }

}
