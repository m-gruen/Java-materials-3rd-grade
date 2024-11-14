public class App {
    public static void main(String[] args) throws Exception {
        var currentThread = Thread.currentThread();
        printThreadState(currentThread);
    }

    public static void printThreadState(Thread thread) {
        System.out.println("---------------------");
        System.out.println("Thread ID: " + thread.threadId());
        System.out.println("Thread Name: " + thread.getName());
        System.out.println("Thread Priority: " + thread.getPriority());
        System.out.println("Thread State: " + thread.getState());
        System.out.println("Thread Group: " + thread.getThreadGroup());
        System.out.println("Thread is Alive: " + thread.isAlive());
    }
}
