import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(numbers);

        // Increment all numbers by 1

        // Solution with local class
        class AddOne implements MyFunction {
            @Override
            public Integer apply(Integer i) {
                return i + 1;
            }
        }

        MyFunction addOne = new AddOne();
        update(numbers, addOne);
        System.out.println(numbers);

        // Solution with anonymous class
        MyFunction addOne2 = new MyFunction() {
            @Override
            public Integer apply(Integer i) {
                return i + 1;
            }
        };
        update(numbers, addOne2);
        System.out.println(numbers);

        update(numbers, new MyFunction() {
            @Override
            public Integer apply(Integer i) {
                return i + 1;
            }
        });
        System.out.println(numbers);

        // Solution with lambda
        update(numbers, i -> i + 1);
        System.out.println(numbers);

        // Multiply all numbers by 2
        update(numbers, i -> i * 2);
        System.out.println(numbers);

        // Subtract 2 from all numbers
        update(numbers, i -> i - 2);
        System.out.println(numbers);

        // Square all numbers
        update(numbers, i -> i * i);
        System.out.println(numbers);

    }

    public static void update(List<Integer> numbers, MyFunction function) {
        for (int i = 0; i < numbers.size(); i++) {
            Integer oldValue = numbers.get(i);
            Integer newValue = function.apply(oldValue);
            numbers.set(i, newValue);
        }
    }
}

interface MyFunction {
    Integer apply(Integer i);
}
