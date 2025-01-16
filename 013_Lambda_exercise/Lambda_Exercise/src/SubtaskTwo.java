import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class SubtaskTwo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(numbers);

        // • Geben Sie alle geraden Zahlen aus!
        List<Integer> evenNumbers = filterNumbers(numbers, x -> x % 2 == 0);
        System.out.println(evenNumbers);

        // • Geben Sie alle ungeraden Zahlen aus!
        List<Integer> oddNumbers = filterNumbers(numbers, x -> x % 2 != 0);
        System.out.println(oddNumbers);

        // • Geben Sie alle Vielfachen von 4 aus!
        List<Integer> multiplesOfFour = filterNumbers(numbers, x -> x % 4 == 0);
        System.out.println(multiplesOfFour);

        // • Geben Sie alle Primzahlen aus!
        List<Integer> primeNumbers = filterNumbers(numbers, x -> isPrime(x));
        System.out.println(primeNumbers);

        // • Geben Sie eine weitere Anwendung von filterNumber() an!
        List<Integer> numbersGreaterThanFive = filterNumbers(numbers, x -> x > 5);
        System.out.println(numbersGreaterThanFive);

        List<Integer> numbersLessThanFive = filterNumbers(numbers, x -> x < 5);
        System.out.println(numbersLessThanFive);

    }

    public static List<Integer> filterNumbers(List<Integer> numbers, Predicate<Integer> condition) {
        List<Integer> filteredNumbers = new ArrayList<>();
        for (int number : numbers) {
            if (condition.test(number)) {
                filteredNumbers.add(number);
            }
        }
        return filteredNumbers;
    }

    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
