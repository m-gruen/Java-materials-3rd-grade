package htl.at;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Analysis {
    public static void main(String[] args) {
        List<Sale> sales = SaleMocks.getSaleMocks();
        List<Sale> sales2 = FileReader.readSalesFormFile(Path.of("files/sales.csv"));

        // a) Wie viele Verkäufe wurden im Jänner 2024 getätigt und was war der Gesamtumsatz?
        System.out.println("=== a) Wie viele Verkäufe wurden im Jänner 2024?");
        taskA_AnzahlVerkaeufe(sales);
        System.out.println();
        taskA_AnzahlVerkaeufe(sales2);

        // b) Welche Verkäufe von Artikel der Kategorie "Lights" wurden im Jänner 2024 nach Italien getätigt, sortiert nach Umsatz
        System.out.println("=== b) Welche Verkäufe von Artikel der Kategorie \"Lights\" wurden im Jänner 2024 nach Italien getätigt, sortiert nach Umsatz?");
        taskB_Lights_Italien(sales);
        System.out.println();
        taskB_Lights_Italien(sales2);

        // c) Welche 3 Verkäufe haben den höchsten Umsatz?
        System.out.println("=== c) Welche 3 Verkäufe haben den höchsten Umsatz?");
        taskC_Top3Umsatz(sales);
        System.out.println();
        taskC_Top3Umsatz(sales2);

        // d) Gab es im Jänner 2024 Verkäufe mit der Zahlungsart "Advance Payment" nach Ungarn?
        System.out.println("=== d) Gab es im Jänner 2024 Verkäufe mit der Zahlungsart \"Advance Payment\" nach Ungarn?");
        taskD_AdvancePayment_Ungarn(sales);
        System.out.println();
        taskD_AdvancePayment_Ungarn(sales2);

        // e) Wie hoch war der Umsatz im Jänner 2024, gruppiert nach Ländern?
        System.out.println("=== e) Wie hoch war der Umsatz im Jänner 2024, gruppiert nach Ländern?");
        taskE_Umsatz_NachLaender(sales);
        System.out.println();
        taskE_Umsatz_NachLaender(sales2);

        // f) Welche Kreditkarten wurden im Jänner 2024 in Deutschland verwendet?
        System.out.println("=== f) Welche Kreditkarten wurden im Jänner 2024 in Deutschland verwendet?");
        taskF_Kreditkarten_Deutschland(sales);
        System.out.println();
        taskF_Kreditkarten_Deutschland(sales2);

        // g) Wie hoch ist der durchschnittliche Umsatz pro Verkauf im Jänner 2024?
        System.out.println("=== g) Wie hoch ist der durchschnittliche Umsatz pro Verkauf im Jänner 2024?");
        taskG_DurchschnittlicherUmsatz(sales);
        System.out.println();
        taskG_DurchschnittlicherUmsatz(sales2);

        // h) Geben Sie 10 zufällige Verkäufe aus Österreich im Jänner 2024 aus, sortiert nach Umsatz!
        System.out.println("=== h) Geben Sie 10 zufällige Verkäufe aus Österreich im Jänner 2024 aus, sortiert nach Umsatz!");
        taskH_10ZufaelligeVerkaeufe_Oesterreich(sales);
        System.out.println();
        taskH_10ZufaelligeVerkaeufe_Oesterreich(sales2);
    }

    private static void taskA_AnzahlVerkaeufe(List<Sale> sales) {
        // a) Wie viele Verkäufe wurden im Jänner 2024 getätigt und was war der Gesamtumsatz?

        long countJan = sales.stream()
                .filter(sale -> sale.date().getMonthValue() == 1)
                .count();

        double saleJan = sales.stream()
                .filter(sale -> sale.date().getMonthValue() == 1)
                .mapToDouble(Sale::amount)
                .sum();

        System.out.println("Count: " + countJan);
        System.out.println("Sales: " + saleJan);
    }

    private static void taskB_Lights_Italien(List<Sale> sales) {
        // b) Welche Verkäufe von Artikel der Kategorie "Lights" wurden im Jänner 2024 nach Italien getätigt, sortiert nach Umsatz
        sales.stream()
                .filter(sale -> sale.date().getMonthValue() == 1)
                .filter(sale -> sale.category().equals("Lights"))
                .filter(sale -> sale.country().equals("Italy"))
                .sorted(Comparator.comparingDouble(Sale::amount).reversed())
                .forEach(System.out::println);
    }

    private static void taskC_Top3Umsatz(List<Sale> sales) {
        // c) Welche 3 Verkäufe haben den höchsten Umsatz?
        sales.stream()
                .sorted(Comparator.comparing(Sale::amount, Comparator.reverseOrder()))
                .limit(3)
                .forEach(System.out::println);
    }

    private static void taskD_AdvancePayment_Ungarn(List<Sale> sales) {
        // d) Gab es im Jänner 2024 Verkäufe mit der Zahlungsart "Advance Payment" nach Ungarn?
        boolean pays = sales.stream()
                .filter(sale -> sale.date().getMonthValue() == 1)
                .filter(sale -> sale.payment().equals("Advance Payment"))
                .anyMatch(sale -> sale.country().equals("Hungary"));

        System.out.println(pays ? "Ja" : "Nein");
    }

    private static void taskE_Umsatz_NachLaender(List<Sale> sales) {
        // e) Wie hoch war der Umsatz im Jänner 2024, gruppiert nach Ländern?
        sales.stream()
                .filter(sale -> sale.date().getMonthValue() == 1)
                .collect(Collectors.groupingBy(Sale::country, Collectors.summingDouble(Sale::amount)))
                .forEach((country, amount) -> System.out.println(country + ": " + amount));
    }

    private static void taskF_Kreditkarten_Deutschland(List<Sale> sales) {
        // f) Welche Kreditkarten wurden im Jänner 2024 in Deutschland verwendet?
        sales.stream()
                .filter(sale -> sale.date().getMonthValue() == 1)
                .filter(sale -> sale.country().equals("Germany"))
                .map(Sale::payment)
                .distinct()
                .forEach(System.out::println);
    }

    private static void taskG_DurchschnittlicherUmsatz(List<Sale> sales) {
        // g) Wie hoch ist der durchschnittliche Umsatz pro Verkauf im Jänner 2024?
        double avgSalesJan = sales.stream()
                .filter(sale -> sale.date().getMonthValue() == 1)
                .mapToDouble(Sale::amount)
                .average()
                .orElse(0);

        System.out.println("Average: " + Math.round(avgSalesJan * 100) / 100.0);
    }

    private static void taskH_10ZufaelligeVerkaeufe_Oesterreich(List<Sale> sales) {
        // h) Geben Sie 10 zufällige Verkäufe aus Österreich im Jänner 2024 aus, sortiert nach Umsatz!
        Random random = new Random();
        Stream.generate(() -> sales.get(random.nextInt(sales.size())))
                .filter(sale -> sale.date().getMonthValue() == 1)
                .filter(sale -> sale.country().equals("Austria"))
                .distinct()
                .limit(10)
                .sorted(Comparator.comparingDouble(Sale::amount).reversed())
                .forEach(System.out::println);
    }

}
