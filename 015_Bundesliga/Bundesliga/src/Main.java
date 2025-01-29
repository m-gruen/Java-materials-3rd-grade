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

        // a)
        System.out.println("a) Drucken Sie alle Spiele des Lieblingsvereins!");

        // b) 
        System.out.println("b) Drucken Sie alle Spiele Ihres Vereins in der Gesamtgruppe!");

        // c)
        System.out.println("c) Hat Ihr Verein in der Meistergruppe gespielt?");

        // d) 
        System.out.println("d) Drucken Sie alle in der Gesamtgruppe gewonnene Spiele Ihres Vereins!");

        // e)
        System.out.println("e) Drucken Sie alle in der Gesamtgruppe unentschieden gespielte Spiele Ihres Vereins!");

        // f)
        System.out.println("f) Drucken Sie alle 0:0-Spiele Ihres Vereins!");

        // g)
        System.out.println("g) Wie viele Spiele hat Ihre Mannschaft insgesamt gewonnen?");

        // h)
        System.out.println("h) Wie viele Tore hat Ihre Mannschaft insgesamt erzielt?");

        // i)
        System.out.println("i) Wie ist die gesamte Torbilanz Ihrer Mannschaft?");

        // j)
        System.out.println("j) Wie viele Punkte hat Ihre Mannschaft in der Gesamtgruppe erreicht?");

        // k)
        System.out.println("k) Welche Spiele wurden am 20. Spieltag ausgetragen?");

        // l)
        System.out.println("l) Welche Spiele wurden im April ausgetragen?");

        // m)
        System.out.println("m) Bei welchen Spielen war die Tordifferenz größer als 5?");

        // n)
        System.out.println("n) Bei wie vielen Spielen in der Gesamtgruppe hat eine Mannschaft mehr als 5 Tore erzielt?");

        // o)
        System.out.println("o) Bei welchen Spielen hat eine Mannschaft das Spiel nach der 1. Halbzeit gedreht?");

        // p)
        System.out.println("p) Listen Sie alle Mannschaften der Qualifikationsgruppe auf!");

        // q)
        System.out.println("q) Listen Sie alle Mannschaften der Meistergruppe auf!");

        // r)
        System.out.println("r) Welche Mannschaft hat in der Gesamtgruppe die meisten Spiele gewonnen?");
    }
}
