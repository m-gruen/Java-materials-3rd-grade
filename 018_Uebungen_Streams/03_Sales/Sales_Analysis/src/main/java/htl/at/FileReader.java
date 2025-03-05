package htl.at;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public static List<Sale> readSalesFormFile(Path filePath) {
        List<Sale> sales = new ArrayList<>();
        try {
            sales = Files.readAllLines(filePath)
                    .stream()
                    .skip(1)
                    .map(FileReader::processLine)
                    .toList();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sales;
    }

    private static Sale processLine(String line) {
        try {
            String[] parts = line.split(";");
            int id = Integer.parseInt(parts[0]);
            String priority = parts[1];
            LocalDate orderDate = LocalDate.parse(parts[2], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            int customerID = Integer.parseInt(parts[3]);
            String country = parts[4];
            int productID = Integer.parseInt(parts[5]);
            String category = parts[6];
            int quantity = Integer.parseInt(parts[7]);
            double pricePerUnit = Double.parseDouble(parts[8]);
            String payment = parts[9];

            return new Sale(id, priority, orderDate, customerID, country, productID, category, quantity, pricePerUnit, payment);

        } catch (Exception e) {
            System.err.println("Invalid format: " + line);
        }
        return null;
    }
}
