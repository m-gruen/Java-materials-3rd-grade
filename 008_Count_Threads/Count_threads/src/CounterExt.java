import java.time.Duration;
import java.time.Instant;

public class CounterExt extends Thread {
    public static volatile int count = 0;

    public int myCount = 0;
    public final int upperBound;
    public String name;

    public Instant start = null;
    public Instant end = null;

    public CounterExt(int upperBound, String name) {
        this.upperBound = upperBound;
        this.name = name;
    }

    @Override
    public void run() {
        start = Instant.now();
        while (true) {
            synchronized (this) {
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
                name,
                myCount,
                count,
                Duration.between(start, end).toMillis());
    }
}