import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class SubtaskFive {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(numbers);

        // • Differenzieren Sie die Zahlen nach gerade/ungerade.
        Map<Boolean, List<Integer>> evenOddNumbers = groupByCondition(numbers, x -> x % 2 == 0);
        System.out.println(evenOddNumbers);

        // • Differenzieren Sie die Zahlen nach Zahlen < 5 und Zahlen >= 5.
        Map<Boolean, List<Integer>> lessGreaterEqualFiveNumbers = groupByCondition(numbers, x -> x < 5);
        System.out.println(lessGreaterEqualFiveNumbers);

        // • Differenzieren Sie die Zahlen nach positiven und negativen Zahlen.
        List<Integer> negativeNumbers = Arrays.asList(-1, -2, -3, -4, -5, -6, -7, -8, -9, -10);
        Map<Boolean, List<Integer>> positiveNegativeNumbers = groupByCondition(negativeNumbers, x -> x > 0);
        System.out.println(positiveNegativeNumbers);

        // • Differenzieren Sie die Zahlen nach Primzahl/nicht Primzahl.
        Map<Boolean, List<Integer>> primeNumbers = groupByCondition(numbers, x -> isPrime(x));
        System.out.println(primeNumbers);

        // • Geben Sie eine weitere Anwendung von groupByCondition() an!
        List<Integer> numbersGreaterThanFive = Arrays.asList(6, 7, 8, 9, 10);
        Map<Boolean, List<Integer>> greaterThanFiveNumbers = groupByCondition(numbersGreaterThanFive, x -> x > 5);
        System.out.println(greaterThanFiveNumbers);
    }

    public static Map<Boolean, List<Integer>> groupByCondition(List<Integer> numbers, Predicate<Integer> condition) {
        Map<Boolean, List<Integer>> groupedNumbers = new HashMap<>();
        List<Integer> trueNumbers = new ArrayList<>();
        List<Integer> falseNumbers = new ArrayList<>();
        for (Integer number : numbers) {
            if (condition.test(number)) {
                trueNumbers.add(number);
            } else {
                falseNumbers.add(number);
            }
        }
        groupedNumbers.put(true, trueNumbers);
        groupedNumbers.put(false, falseNumbers);
        return groupedNumbers;
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
