import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class SubtaskThree {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("Hello", "World", "Java", "Programming", "Language");
        System.out.println(strings);

        // • Geben Sie die Strings in Großbuchstaben aus!
        List<String> upperCaseStrings = transformStrings(strings, x -> x.toUpperCase());
        System.out.println(upperCaseStrings);

        // • Geben Sie die Strings in Kleinbuchstaben aus!
        List<String> lowerCaseStrings = transformStrings(strings, x -> x.toLowerCase());
        System.out.println(lowerCaseStrings);

        // • Hängen Sie an jeden String „!“ an und geben Sie die Ergebnisse aus!
        List<String> exclamationMarkStrings = transformStrings(strings, x -> String.format("%s!", x));
        System.out.println(exclamationMarkStrings);

        // • Geben Sie die umgekehrten Strings aus!
        List<String> reversedStrings = transformStrings(strings, x -> reverseString(x));
        System.out.println(reversedStrings);

        // • Geben Sie eine weitere Anwendung von transformStrings() an!
        List<String> firstThreeCharacters = transformStrings(strings, x -> x.substring(0, 3));
        System.out.println(firstThreeCharacters);

        List<String> lastThreeCharacters = transformStrings(strings, x -> x.substring(x.length() - 3));
        System.out.println(lastThreeCharacters);

    }

    public static List<String> transformStrings(List<String> strings, Function<String, String> transformer) {
        List<String> transformedStrings = new ArrayList<>();
        for (String string : strings) {
            transformedStrings.add(transformer.apply(string));
        }
        return transformedStrings;
    }

    public static String reverseString(String string) {
        return new StringBuilder(string).reverse().toString();
    }
}
