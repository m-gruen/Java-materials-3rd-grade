package htl.at;

import java.time.LocalDate;
import java.util.Objects;

public record Sale(
        int orderID,
        String priority,
        LocalDate date,
        int customerID,
        String country,
        int productID,
        String category,
        int quantity,
        double pricePerUnit,
        String payment) {

    @Override
    public String toString() {
        return "%s %7d %-9s %s %7d %-20s %7d %-12s %4d %8.2f %10.2f %-10s".formatted(
                Objects.equals(priority, "Next Day") || Objects.equals(priority, "Express") ? "*" : " ",
                orderID,
                priority,
                date,
                customerID,
                country,
                productID,
                category,
                quantity,
                pricePerUnit,
                amount(),
                payment);
    }

    public double amount() {
        return quantity * pricePerUnit;
    }


}



