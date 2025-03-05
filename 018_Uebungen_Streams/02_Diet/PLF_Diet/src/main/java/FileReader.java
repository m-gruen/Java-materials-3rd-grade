import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FileReader {
    public static List<Person> readPersonsFromFile(Path filePath) {
        List<Person> persons = new ArrayList<>();
        try {
            persons = Files.readAllLines(filePath).stream()
                    .skip(1)
                    .map(FileReader::processLine)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return persons;
    }

    private static Person processLine(String line) {
        try {
            String[] parts = line.split(";");
            int id = Integer.parseInt(parts[0]);
            String firstName = parts[1];
            String lastName = parts[2];
            char gender = parts[3].charAt(0);
            LocalDate dayOfBirth = LocalDate.parse(parts[4], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            int height = Integer.parseInt(parts[5]);
            double weight_T1 = Double.parseDouble(parts[6]);
            double weight_T2 = Double.parseDouble(parts[7]);

            return new Person(id, firstName, lastName, gender, dayOfBirth, height, weight_T1, weight_T2);
        } catch (Exception e) {
            System.out.println("Error while processing line: " + line);
        }

        return null;
    }

}
