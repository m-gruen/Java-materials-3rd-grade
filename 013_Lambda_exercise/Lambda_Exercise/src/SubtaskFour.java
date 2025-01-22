import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Function;

public class SubtaskFour {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(numbers);

        // • Berechnen Sie die Summe aller Zahlen!
        Integer sumOfNumbers = processNumbers(numbers, x -> true, x -> x);
        System.out.println(sumOfNumbers);

        // • Berechnen Sie die Summe der ungeraden Zahlen!
        Integer sumOfOddNumbers = processNumbers(numbers, x -> x % 2 != 0, x -> x);
        System.out.println(sumOfOddNumbers);

        // • Berechnen Sie die Summe des doppelten Werte aller ungeraden Zahlen!
        Integer sumOfDoubledOddNumbers = processNumbers(numbers, x -> x % 2 != 0, x -> x * 2);
        System.out.println(sumOfDoubledOddNumbers);

        // • Geben Sie eine weitere Anwendung von processNumbers() an!
        Integer sumOfNumbersGreaterThanFive = processNumbers(numbers, x -> x > 5, x -> x);
        System.out.println(sumOfNumbersGreaterThanFive);
    }

    public static Integer processNumbers(List<Integer> numbers, Predicate<Integer> filter, Function<Integer, Integer> transformer) {
        int result = 0;
        for (Integer number : numbers) {
            if (filter.test(number)) {
                result += transformer.apply(number);
            }
        }
        return result;
        
    }
}