import java.time.Duration;
import java.time.Instant;

public class CounterExt extends Thread {
    public static volatile int count = 0;

    public int myCount = 0;
    public final int upperBound;

    public CounterExt(int upperBound, String name) {
        super(name);
        this.upperBound = upperBound;
    }

    @Override
    public void run() {
        Instant start, end;

        start = Instant.now();
        
        while (true) {
            synchronized (getClass()) {
                if (count < upperBound) {
                    count++;
                    myCount++;
                } else {
                    end = Instant.now();
                    break;
                }
            }
        }

        System.out.printf(
                "Thread: %s, My Iterations: %d, Total Value: %d, Time: %d ms\n",
                getName(),
                myCount,
                count,
                Duration.between(start, end).toMillis());
    }
}