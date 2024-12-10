package runnableSushi;

public class RunnableSushi {

    public static void main(String[] args) {
        System.out.println("Runnable Sushi opens ...");

        // Create and start belt
        var belt = new Belt(12);
        belt.start();

        // Create and start cooks
        var cook1 = new Producer("S", FoodType.SUSHI, belt, 1);
        var cook2 = new Producer("A", FoodType.APPETIZER, belt, 2);

        cook1.start();
        cook2.start();

        // Create and start guests
        var guest1 = new Consumer(ConsumerType.GUEST, "Ann", belt, 3);
        var guest2 = new Consumer(ConsumerType.GUEST, "Bob", belt, 5);
        var guest3 = new Consumer(ConsumerType.GUEST, "Joe", belt, 7);

        guest1.start();
        guest2.start();
        guest3.start();

        // Wait until the Runnable Sushi has to close
        try {
            Thread.sleep(10000); // The Runnable Sushi is open for 3 seconds
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Stop the cooks
        cook1.interrupt();
        cook2.interrupt();

        // Stop the guests
        guest1.interrupt();
        guest2.interrupt();
        guest3.interrupt();

        // Clean the belt
        var cleaner = new Consumer(ConsumerType.CLEANER, "Cleaner", belt, 0);
        cleaner.start();

        try {
            cook1.join();
            cook2.join();
            guest1.join();
            guest2.join();
            guest3.join();
            cleaner.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Stop the belt
        belt.interrupt();

        try {
            belt.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Close Runnable Sushi
        System.out.println("Runnable Sushi closes ...");

    }

}
