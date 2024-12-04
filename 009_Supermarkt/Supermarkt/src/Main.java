import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        

        Queue<Customer> queue = new LinkedList<>();

        // Start to generate customers
        CustomerGen customerGen = new CustomerGen(queue);
        customerGen.start();

        // Start 3 checkout threads
        Checkout checkout1 = new Checkout(queue, "Checkout 1");
        Checkout checkout2 = new Checkout(queue, "Checkout 2");
        Checkout checkout3 = new Checkout(queue, "Checkout 3");

        checkout1.start();
        checkout2.start();
        checkout3.start();

        try {
            // Wait for 5 seconds
            Thread.sleep(5000);
        } catch (InterruptedException ignore) {
        }

        // Interrupt all threads
        customerGen.interrupt();
        checkout1.interrupt();
        checkout2.interrupt();
        checkout3.interrupt();
    }
}
