import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        

        Queue<Customer> queue = new LinkedList<>();

        // Start to generate customers
        CustomerGen customerGen = new CustomerGen(queue);
        customerGen.start();

        // 5 hours later ...
        customerGen.interrupt();
    }
}
