public class App {
    public static void main(String[] args) {
        int upperBound = 10_000;
        Thread t1 = new CounterExt(upperBound, "Tick");
        Thread t2 = new CounterExt(upperBound, "Trick");
        Thread t3 = new CounterExt(upperBound, "Track");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main is done!");
    }
}
