import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class SubtaskOne {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(numbers);

        // • Erhöhen Sie alle Werte um 1!
        update(numbers, x -> x + 1);
        System.out.println(numbers);

        // • Verdoppeln Sie alle Werte!
        update(numbers, x -> x * 2);
        System.out.println(numbers);

        // • Vermindern Sie alle Werte um 2!
        update(numbers, x -> x - 2);
        System.out.println(numbers);

        // • Ersetzen Sie die Werte durch den Abstand vom Mittelwert aller Werte!
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        final int avg = sum / numbers.size();

        update(numbers, x -> Math.abs(x - avg));
        System.out.println(numbers);

        // • Geben Sie eine weitere Anwendung von update() an!
        update(numbers, x -> x * x);
        System.out.println(numbers);

        update(numbers, x -> x * x * x * x * x * x * x);
        System.out.println(numbers);
    }

    public static void update(List<Integer> numbers, Function<Integer, Integer> updater) {
        for (int i = 0; i < numbers.size(); i++) {
            numbers.set(i, updater.apply(numbers.get(i)));
        }
    }
}
