import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Match> file = null;
        try {
            file = FileReader.readMatchesFromFile(Path.of("files/bundesliga_23_24.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (file != null) {
            file.forEach(System.out::println);
        }

        // a) Drucken Sie alle Spiele des Lieblingsvereins!
        // b) Drucken Sie alle Spiele Ihres Vereins in der Gesamtgruppe!
        // c) Hat Ihr Verein in der Meistergruppe gespielt?
        // d) Drucken Sie alle in der Gesamtgruppe gewonnene Spiele Ihres Vereins!
        // e) Drucken Sie alle in der Gesamtgruppe unentschieden gespielte Spiele Ihres
        // Vereins!
        // f) Drucken Sie alle 0:0-Spiele Ihres Vereins!
        // g) Wie viele Spiele hat Ihre Mannschaft insgesamt gewonnen?
        // h) Wie viele Tore hat Ihre Mannschaft insgesamt erzielt?
        // i) Wie ist die gesamte Torbilanz Ihrer Mannschaft?
        // j) Wie viele Punkte hat Ihre Mannschaft in der Gesamtgruppe erreicht?
        // k) Welche Spiele wurden am 20. Spieltag ausgetragen?
        // l) Welche Spiele wurden im April ausgetragen?
        // m) Bei welchen Spielen war die Tordifferenz größer als 5?
        // n) Bei wie vielen Spielen in der Gesamtgruppe hat eine Mannschaft mehr als 5
        // Tore erzielt?
        // o) Bei welchen Spielen hat eine Mannschaft das Spiel nach der 1. Halbzeit
        // gedreht?
        // p) Listen Sie alle Mannschaften der Qualifikationsgruppe auf!
        // q) Listen Sie alle Mannschaften der Meistergruppe auf!
        // r) Welche Mannschaft hat in der Gesamtgruppe die meisten Spiele gewonnen?
    }
}
