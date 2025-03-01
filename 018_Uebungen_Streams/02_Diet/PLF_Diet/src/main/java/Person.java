import java.time.LocalDate;
import java.util.List;

public record Person(
        int id,
        String lastname,
        String firstname,
        char gender,
        LocalDate dayOfBirth,
        int height,
        double weight_T1,
        double weight_T2) {


    public String toString() {
        // TODO
        return "";
    }

    public boolean hasAborted() {
        // TODO
        return false;
    }

    public int getAge() {
        // TODO
        return -1;
    }

    public double getBMI() {
        // TODO
        return -1;
    }

    public String getBMIClass() {
        // TODO
        return "";
    }

}
