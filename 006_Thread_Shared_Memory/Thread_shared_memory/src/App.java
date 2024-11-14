public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}

class StopWatch {
    public StopWatch() {

    }

    public void countDown(int startCount) {

        String threadName = Thread.currentThread().getName();
        ThreadColor threadColor = ThreadColor.ANSI_GREEN;
        String color = threadColor.color();
        

        for (int i = startCount; i > 0; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("%s%s: %d%n", color, threadName, i);
        }
    }
}
