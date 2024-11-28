import java.util.Queue;

public class CustomerGen extends Thread {

    private Queue<Customer> queue;

    public CustomerGen(Queue<Customer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Customer c;

        try {

            while (!isInterrupted()) {
                c = Customer.create();

                synchronized (queue) {
                    queue.offer(c);
                    queue.notifyAll(); // Notify all waiting threads
                }

                // Cast from double in long
                Thread.sleep((long) (Math.random() * 100)); // Random sleep between 0 - 100 ms
            }

        } catch (InterruptedException ignore) {
        }

        System.out.println("CustomerGen interrupted");
    }

}
