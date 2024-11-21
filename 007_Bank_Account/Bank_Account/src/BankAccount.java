import java.util.concurrent.TimeUnit;

public class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public synchronized void deposit(double amount) {
        try {
            System.out.println("Einzahlung: Warten auf System...");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        double originalBalance = balance;

        balance += amount;
        System.out.printf(
                "Alter Kontostand: %.2f, Einzahlung: %.2f, Neuer Kontostand: %.2f\n",
                originalBalance,
                amount,
                balance);
    }

    public synchronized void withdraw(double amount) {
        try {
            System.out.println("Auszahlung: Warten auf System...");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        double originalBalance = balance;

        if (amount <= balance) {
            balance -= amount;
            System.out.printf(
                    "Alter Kontostand: %.2f, Auszahlung: %.2f, Neuer Kontostand: %.2f\n",
                    originalBalance,
                    amount,
                    balance);
        } else {
            System.out.printf(
                    "Alter Kontostand: %.2f, Einzahlung: %.2f, Guthaben nicht ausreichend\n",
                    originalBalance,
                    amount);
        }

    }
}
