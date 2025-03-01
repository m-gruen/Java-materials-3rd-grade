package htl.at;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Analysis {
    public static void main(String[] args) {
        List<Sale> sales = SaleMocks.getSaleMocks();

        // a) Wie viele Verkäufe wurden im Jänner 2024 getätigt und was war der Gesamtumsatz?
        System.out.println("=== a) Wie viele Verkäufe wurden im Jänner 2024?");
        taskA_AnzahlVerkaeufe(sales);

        // b) Welche Verkäufe von Artikel der Kategorie "Lights" wurden im Jänner 2024 nach Italien getätigt, sortiert nach Umsatz
        System.out.println("=== b) Welche Verkäufe von Artikel der Kategorie \"Lights\" wurden im Jänner 2024 nach Italien getätigt, sortiert nach Umsatz?");
        taskB_Lights_Italien(sales);

        // c) Welche 3 Verkäufe haben den höchsten Umsatz?
        System.out.println("=== c) Welche 3 Verkäufe haben im Jänner 2024 den höchsten Umsatz?");
        taskC_Top3Umsatz(sales);

        // d) Gab es im Jänner 2024 Verkäufe mit der Zahlungsart "Advance Payment" nach Ungarn?
        System.out.println("=== d) Gab es im Jänner 2024 Verkäufe mit der Zahlungsart \"Advance Payment\" nach Ungarn?");
        taskD_AdvancePayment_Ungarn(sales);

        // e) Wie hoch war der Umsatz im Jänner 2024, gruppiert nach Ländern?
        System.out.println("=== e) Wie hoch war der Umsatz im Jänner 2024, gruppiert nach Ländern?");
        taskE_Umsatz_NachLaender(sales);

        // f) Welche Kreditkarten wurden im Jänner 2024 in Deutschland verwendet?
        System.out.println("=== f) Welche Kreditkarten wurden im Jänner 2024 in Deutschland verwendet?");
        taskF_Kreditkarten_Deutschland(sales);

        // g) Wie hoch ist der durchschnittliche Umsatz pro Verkauf im Jänner 2024?
        System.out.println("=== g) Wie hoch ist der durchschnittliche Umsatz pro Verkauf im Jänner 2024?");
        taskG_DurchschnittlicherUmsatz(sales);

        // h) Geben Sie 10 zufällige Verkäufe aus Österreich im Jänner 2024 aus, sortiert nach Umsatz!
        System.out.println("=== h) Geben Sie 10 zufällige Verkäufe aus Österreich im Jänner 2024 aus, sortiert nach Umsatz!");
        taskH_10ZufaelligeVerkaeufe_Oesterreich(sales);

    }

    private static void taskA_AnzahlVerkaeufe(List<Sale> sales) {
        // TODO
    }

    private static void taskB_Lights_Italien(List<Sale> sales) {
        // TODO
    }

    private static void taskC_Top3Umsatz(List<Sale> sales) {
        // TODO
    }

    private static void taskD_AdvancePayment_Ungarn(List<Sale> sales) {
        // TODO
    }

    private static void taskE_Umsatz_NachLaender(List<Sale> sales) {
        // TODO
    }

    private static void taskF_Kreditkarten_Deutschland(List<Sale> sales) {
        // TODO
    }

    private static void taskG_DurchschnittlicherUmsatz(List<Sale> sales) {
        // TODO
    }

    private static void taskH_10ZufaelligeVerkaeufe_Oesterreich(List<Sale> sales) {
        // TODO
    }


}