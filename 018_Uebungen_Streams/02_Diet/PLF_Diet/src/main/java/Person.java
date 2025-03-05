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
        return String.format("%s %d\t%-30s %s %s (%d) BMI %.1f-%-15s\t%-4d\t%5.1f\t|\t%5.1f (%s%.1f)",
                hasAborted() ? "*" : " ",
                id,
                lastname + " " + firstname,
                gender,
                dayOfBirth.toString(),
                getAge(),
                getBMI(),
                getBMIClass(),
                height,
                weight_T1,
                weight_T2,
                weight_T2 < weight_T1 ? "-" : "+",
                Math.abs(weight_T2 - weight_T1));
    }

    public boolean hasAborted() {
        // TODO
        return weight_T2 == 0;
    }

    public int getAge() {
        // TODO
        int age = LocalDate.now().getYear() - dayOfBirth.getYear();

        if (LocalDate.now().getDayOfYear() < dayOfBirth.getDayOfYear()) {
            age--;
        }

        return age;
    }

    public double getBMI() {
        // TODO
        return weight_T1 / ((double) height / 100d * (double) height / 100);
    }

    public String getBMIClass() {
        // TODO
        double bmi = getBMI();

        if (bmi < 18.5) {
            return "underweight";
        } else if (bmi < 25.0) {
            return "normal weight";
        } else if (bmi < 30.0) {
            return "overweight";
        } else {
            return "obese";
        }
    }

}
