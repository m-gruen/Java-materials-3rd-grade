public class ThreadCreation {
    public static void main(String[] args) {
        CustomThread customThread = new CustomThread();
        customThread.start();

        Runnable myRunnable = () -> {
            for (int i = 1; i <= 8; i++) {
                System.out.print(" 2 ");
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread myThread = new Thread(myRunnable);
        myThread.start();
    }
}
