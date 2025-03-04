import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // Erledigen Sie untenstehende Aufgaben. Nutzen Sie dabei Streams und verwenden
        // Sie generate, iterate bzw. of zum Erzeugung eines Streams!

        // 1. Drucken Sie alle geraden Zahlen von 2 bis 10, getrennt durch Leerzeichen.
        System.out.println("1. Drucken Sie alle geraden Zahlen von 2 bis 10, getrennt durch Leerzeichen.");
        IntStream.iterate(2, x -> x + 2)
                .limit(5)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();

        // 2. Drucken Sie alle Primzahlen von 1 – 100, getrennt durch Leerzeichen.
        // Tipp: Erstellen Sie sich eine Hilfsmethode zur Prüfung, ob eine Zahl Primzahl
        // ist oder nicht!
        System.out.println("2. Drucken Sie alle Primzahlen von 1 - 100, getrennt durch Leerzeichen.");
        IntStream.iterate(2, x -> x <= 100, x -> x + 1)
                .filter(Main::isPrime)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();

        // 3. Drucken Sie die ersten 100 Primzahlen, getrennt durch Leerzeichen.
        System.out.println("3. Drucken Sie die ersten 100 Primzahlen, getrennt durch Leerzeichen.");
        IntStream.iterate(2, x -> x + 1)
                .filter(Main::isPrime)
                .limit(100)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();

        // 4. Drucken Sie die Summe ersten 10 Primzahlen.
        System.out.println("4. Drucken Sie die Summe ersten 10 Primzahlen.");
        int sum = IntStream.iterate(2, x -> x + 1)
                .filter(Main::isPrime)
                .limit(10)
                .sum();
        System.out.println(sum);

        // 5. Nutzen Sie iterate, um ein int-Array mit den Primzahlen von 1 bis 59 zu
        // erzeugen. Drucken Sie diese.
        System.out.println("5. Nutzen Sie iterate, um ein int-Array mit den Primzahlen von 1 bis 59 zu erzeugen.");
        int[] primes = IntStream.iterate(2, x -> x <= 59, x -> x + 1)
                .filter(Main::isPrime)
                .toArray();
        for (int prime : primes) {
            System.out.print(prime + " ");
        }
        System.out.println();

        // 6. Nutzen Sie range, um ein int-Array mit den Primzahlen von 1 bis 59 zu
        // erzeugen. Drucken Sie diese.
        System.out.println("6. Nutzen Sie range, um ein int-Array mit den Primzahlen von 1 bis 59 zu erzeugen.");
        int[] primes2 = IntStream.range(2, 60)
                .filter(Main::isPrime)
                .toArray();
        for (int prime : primes2) {
            System.out.print(prime + " ");
        }
        System.out.println();

        // 7. Nutzen Sie rangeClosed, um ein int-Array mit den Primzahlen von 1 bis 59
        // zu erzeugen. Drucken Sie diese.
        System.out.println("7. Nutzen Sie rangeClosed, um ein int-Array mit den Primzahlen von 1 bis 59 zu erzeugen.");
        int[] primes3 = IntStream.rangeClosed(2, 59)
                .filter(Main::isPrime)
                .toArray();
        for (int prime : primes3) {
            System.out.print(prime + " ");
        }
        System.out.println();

        // 8. Drucken Sie 10 Zufallszahlen von 1 bis 100 (Duplikate sind erlaubt).
        // Nutzen Sie Math.random().
        System.out.println("8. Drucken Sie 10 Zufallszahlen von 1 bis 100 (Duplikate sind erlaubt).");
        IntStream.generate(() -> (int) (Math.random() * 100) + 1)
                .limit(10)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();

        // 9. Drucken Sie 10 Zufallszahlen von 1 bis 100 (Duplikate sind erlaubt).
        // Nutzen Sie die Random-Klasse.
        System.out.println("9. Drucken Sie 10 Zufallszahlen von 1 bis 100 (Duplikate sind erlaubt).");
        Random random = new Random();
        IntStream.generate(() -> random.nextInt(100) + 1)
                .limit(10)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();

        // 10. Drucken Sie 10 Zufallszahlen von 1 bis 100 (ohne Duplikate).
        System.out.println("10. Drucken Sie 10 Zufallszahlen von 1 bis 100 (ohne Duplikate).");
        IntStream.generate(() -> random.nextInt(100) + 1)
                .distinct()
                .limit(10)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();

        // 11. Drucken Sie 10 Zufallszahlen von 1 bis 100 (ohne Duplikate, in
        // aufsteigender Reihenfolge sortiert).
        System.out.println(
                "11. Drucken Sie 10 Zufallszahlen von 1 bis 100 (ohne Duplikate, in aufsteigender Reihenfolge sortiert).");
        IntStream.generate(() -> random.nextInt(100 + 1))
                .distinct()
                .limit(10)
                .sorted()
                .forEach(x -> System.out.print(x + " "));
        System.out.println();

        // 12. Erzeugen Sie 10 Zufallszahlen von 1 bis 100 (ohne Duplikate) und drucken
        // Sie nur jene, welche unter 50 sind in absteigender Reihenfolge.
        System.out.println(
                "12. Erzeugen Sie 10 Zufallszahlen von 1 bis 100 (ohne Duplikate) und drucken Sie nur jene, welche unter 50 sind in absteigender Reihenfolge.");
        IntStream.generate(() -> random.nextInt(100 + 1))
                .distinct()
                .limit(10)
                .filter(x -> x < 50)
                .boxed()
                .sorted((n1, n2) -> n2 - n1)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();

        // 13. Erzeugen Sie 1000 Zufallszahlen von 1 bis 100 und geben Sie aus, wie
        // häufig jede Zahl vorkommt.
        System.out.println(
                "13. Erzeugen Sie 1000 Zufallszahlen von 1 bis 100 und geben Sie aus, wie häufig jede Zahl vorkommt.");
        IntStream.generate(() -> random.nextInt(100) + 1)
                .limit(1000)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println();

        // 14. Erzeugen Sie 1000 Zufallszahlen von 1 bis 100 und drucken Sie die 3 am
        // häufigsten vorkommenden Zahlen.
        System.out.println(
                "14. Erzeugen Sie 1000 Zufallszahlen von 1 bis 100 und drucken Sie die 3 am häufigsten vorkommenden Zahlen.");

        // 15. Erzeugen Sie einen String, der alle Großbuchstaben (A bis Z) enthält.
        // Drucken Sie diesen.
        System.out.println("15. Erzeugen Sie einen String, der alle Großbuchstaben (A bis Z) enthält.");

        // 16. Erzeugen Sie einen String, der alle Groß- und Kleinbuchstaben enthält.
        // Drucken Sie diesen.
        // Tipp: Nutzen Sie die Methode Character.isAlphabetic().
        System.out.println("16. Erzeugen Sie einen String, der alle Groß- und Kleinbuchstaben enthält.");

        // 17. Erzeugen Sie einen 10-stelligen String aus zufälligen Groß- und
        // Kleinbuchstaben.
        System.out.println("17. Erzeugen Sie einen 10-stelligen String aus zufälligen Groß- und Kleinbuchstaben.");

        // 18. Erzeugen Sie einen String all jener Zeichen, deren ASCII-Code eine
        // Primzahl ist.
        System.out.println("18. Erzeugen Sie einen String all jener Zeichen, deren ASCII-Code eine Primzahl ist.");

        // 19. Erzeugen Sie einen 20-stelliges, zufälliges Passwort, das aus Ziffern,
        // Groß- und Kleinbuchstaben besteht.
        System.out.println(
                "19. Erzeugen Sie einen 20-stelliges, zufälliges Passwort, das aus Ziffern, Groß- und Kleinbuchstaben besteht.");

        // 20. Erzeugen Sie einen zufälligen TAN-Code, der aus einer Sequenz von 2
        // Großbuchstaben, 2 Ziffern und 2 Kleinbuchstaben besteht.
        System.out.println(
                "20. Erzeugen Sie einen zufälligen TAN-Code, der aus einer Sequenz von 2 Großbuchstaben, 2 Ziffern und 2 Kleinbuchstaben besteht.");

        // Erstellen Sie einen String-Array mit den Elementen "apple", "banana",
        // "cherry", "date", "elderberry", "fig".

        // 21. Erzeugen Sie einen String, der alle Früchte enthält, durch Komma
        // separiert.
        System.out.println("21. Erzeugen Sie einen String, der alle Früchte enthält, durch Komma separiert.");

        // 22. Erzeugen Sie einen String, der alle Früchte, umgewandelt in
        // Großbuchstaben, enthält, durch Komma separiert.
        System.out.println(
                "22. Erzeugen Sie einen String, der alle Früchte, umgewandelt in Großbuchstaben, enthält, durch Komma separiert.");

        // 23. Erzeugen Sie einen Fruchtsalat, in dem die Früchte zufällig angeordnet
        // sind, durch Komma und Leerzeichen getrennt.
        System.out.println(
                "23. Erzeugen Sie einen Fruchtsalat, in dem die Früchte zufällig angeordnet sind, durch Komma und Leerzeichen getrennt.");

        // 24. Erzeugen Sie einen String, bei dem die Anfangsbuchstaben der Früchte in
        // Großbuchstaben angeführt sind.
        System.out.println(
                "24. Erzeugen Sie einen String, bei dem die Anfangsbuchstaben der Früchte in Großbuchstaben angeführt sind.");

        // 25. Erzeugen Sie einen String, der ein Alphabet der Früchte angibt: „A for
        // apple, B for banana, …“.
        System.out.println(
                "25. Erzeugen Sie einen String, der ein Alphabet der Früchte angibt: „A for apple, B for banana, …“.");

        // 26. Erzeugen Sie einen String aller Früchte, wobei jede Frucht rückwärts
        // ausgegeben wird: apple -> elppa, …
        System.out.println(
                "26. Erzeugen Sie einen String aller Früchte, wobei jede Frucht rückwärts ausgegeben wird: apple -> elppa, …");

        // 27. Erzeugen Sie neue Früchte, indem Sie zunächst alle Früchte
        // aneinanderreihen und dann an zufälligen
        // Stellen Komma + Leerzeichen einfügen, z. B. „app, leban, anacher, ry …“.
        // Tipp: Nutzen Sie flatMap, um den String-Stream in einen Character-Stream
        // umzuwandeln.
        System.out.println(
                "27. Erzeugen Sie neue Früchte, indem Sie zunächst alle Früchte aneinanderreihen und dann an zufälligen Stellen Komma + Leerzeichen einfügen, z. B. „app, leban, anacher, ry …“.");

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
