import java.util.Queue;

public class Checkout extends Thread {
    private Queue<Customer> queue;
    private double mySalesSum = 0;

    public Checkout(Queue<Customer> queue, String name) {
        super(name);
        this.queue = queue;
    }

    @Override
    public void run() {
        Customer c;

        try {
            while (!isInterrupted()) {
                synchronized (queue) {
                    if (queue.isEmpty()) {
                        queue.wait(); // Wait for customers
                    } else {
                        c = queue.poll();
                        mySalesSum += c.getValue();
                    }
                }
            
                // Cast from double to long
                Thread.sleep((long) (40 + Math.random() * 500)); // Random sleep between 40 - 540 ms
            }
        } catch (InterruptedException ignore) {
        }

        try {
            while (!queue.isEmpty()) {
                synchronized (queue) {
                    if (queue.isEmpty()) {
                        break;
                    } else {
                        c = queue.poll();
                        mySalesSum += c.getValue();
                    }
                }
            
                // Cast from double to long
                Thread.sleep((long) (40 + Math.random() * 500)); // Random sleep between 40 - 540 ms
            }
        } catch (InterruptedException ignore) {
        }

        System.out.println("%s\tSales: EUR %7.2f".formatted(getName(), mySalesSum));
    }
}
