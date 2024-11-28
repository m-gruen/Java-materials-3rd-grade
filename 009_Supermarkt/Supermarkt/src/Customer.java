public class Customer {
    private int id;
    private double value;

    private static int nextId = 1;

    private Customer() {
        this.id = nextId++;
        this.value = Math.random() * 100; // Random value between 0 - 100
    }

    public static Customer create() {
        Customer customer = new Customer();
        System.out.println("Buying: " + customer.toString());
        return customer;
    }

    @Override
    public String toString() {
        return "Customer %d: EUR %7.2f".formatted(this.id, this.value);
    }

    public double getValue() {
        return value;
    }

    public int getId() {
        return id;
    }
}
