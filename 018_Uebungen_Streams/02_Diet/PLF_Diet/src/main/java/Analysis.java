import java.nio.file.Path;
import java.util.List;

public class Analysis {
    public static void main(String[] args) {
        // TODO: Read persons from file
        List<Person> persons = PersonMocks.getPersonMocks();
        List<Person> persons1 = FileReader.readPersonsFromFile(Path.of("files/data.csv"));

        // a) Wie viele Personen (absolut und in %) haben die Untersuchung abgebrochen (weight_T2 == 0)?
        System.out.println("===================================================================================");
        System.out.println("=== a) Wie viele Personen (absolut und in %) haben die Untersuchung abgebrochen " +
                "(weight_T2 == 0)?");
        taskA_PersonenAbgebrochen_AnzahlUndProzent(persons);

        // b) Welche weiblichen Untersuchungspersonen mit einem Alter von mindestens 60 Jahren haben mehr als 2 kg
        //    Gewicht verloren, sortiert nach dem Alter?
        System.out.println("===================================================================================");
        System.out.println("=== b) Welche weiblichen Untersuchungspersonen mit einem Alter von mindestens " +
                "60 Jahren haben mehr als 2 kg Gewicht verloren, sortiert nach dem Alter?");
        taskB_weiblich_60Plus_2kgMinus_OrderedByAge(persons);

        // c) Welche 3 Personen haben das meiste Gewicht verloren?
        System.out.println("===================================================================================");
        System.out.println("=== c) Welche 3 Personen haben das meiste Gewicht verloren?");
        taskC_Top3Gewichtsverlust(persons);

        // d) Haben an der Untersuchung auch männliche Personen mit einer Größe von zumindest 1,8 m und einem
        //    Gewicht von weniger als 70 kg (Zeitpunkt T1) teilgenommen?
        System.out.println("===================================================================================");
        System.out.println("=== d) Haben an der Untersuchung auch männliche Personen mit einer Größe von zumindest " +
                                "1,8 m und einem Gewicht von weniger als 70 kg (Zeitpunkt T1) teilgenommen?");
        taskD_Maenner_Groesser180_Kleiner70kg(persons);

        // e) Wie viele Personen haben mehr als 5 kg verloren, gruppiert nach dem Geschlecht?
        System.out.println("===================================================================================");
        System.out.println("=== e) Wie viele Personen haben mehr als 5 kg verloren, gruppiert nach dem Geschlecht?");
        taskE_AnzahlPersonen_5KgMinus_NachGeschlecht(persons);

        // f) Welche weiblichen Vornamen, die mit „N“ beginnen, sind in der Untersuchungsdaten vorhanden,
        //    ohne Duplikate?
        System.out.println("===================================================================================");
        System.out.println("=== f) Welche weiblichen Vornamen, die mit „N“ beginnen, sind in der Untersuchungsdaten " +
                                "vorhanden, ohne Duplikate?");
        taskF_Weiblich_VornameBeginnMitN(persons);

        // g) Wie hoch ist die durchschnittliche Gewichtsveränderung aller männliche Untersuchungspersonen mit einer
        //    Größe von zumindest 1,8 m?
        System.out.println("===================================================================================");
        System.out.println("=== g) Wie hoch ist die durchschnittliche Gewichtsveränderung aller männliche " +
                                "Untersuchungspersonen mit einer Größe von zumindest 1,8 m?");
        taskG_Maenner_Groesser180_DurschnittlicherGewichtsverlust(persons);

        // h) Geben Sie 10 zufällige Untersuchungspersonen aus, welche Gewicht verloren haben. Sortieren Sie die
        //    Liste nach dem BMI.
        System.out.println("===================================================================================");
        System.out.println("=== h) Geben Sie 10 zufällige Untersuchungspersonen aus, welche Gewicht verloren haben. " +
                                "Sortieren Sie die Liste nach dem BMI.");
        taskH_10ZufaelligePersonen_Gewichtsverlust_OrderedByAge(persons);
    }

    private static void taskA_PersonenAbgebrochen_AnzahlUndProzent(List<Person> persons) {
        // a) Wie viele Personen (absolut und in %) haben die Untersuchung abgebrochen (weight_T2 == 0)?

        // TODO
        System.out.println("TODO");
    }

    private static void taskB_weiblich_60Plus_2kgMinus_OrderedByAge(List<Person> persons) {
        // b) Welche weiblichen Untersuchungspersonen mit einem Alter von mindestens 60 Jahren haben mehr als 2 kg
        //    Gewicht verloren, sortiert nach dem Alter?

        // TODO
        System.out.println("TODO");
    }

    private static void taskC_Top3Gewichtsverlust(List<Person> persons) {
        // c) Welche 3 Personen haben das meiste Gewicht verloren?

        // TODO
        System.out.println("TODO");
    }

    private static void taskD_Maenner_Groesser180_Kleiner70kg(List<Person> persons) {
        // d) Haben an der Untersuchung auch männliche Personen mit einer Größe von zumindest 1,8 m und einem
        //    Gewicht von weniger als 70 kg (Zeitpunkt T1) teilgenommen?

        // TODO
        System.out.println("TODO");
    }

    private static void taskE_AnzahlPersonen_5KgMinus_NachGeschlecht(List<Person> persons) {
        // e) Wie viele Personen haben mehr als 5 kg verloren, gruppiert nach dem Geschlecht?

        // TODO
        System.out.println("TODO");
    }

    private static void taskF_Weiblich_VornameBeginnMitN(List<Person> persons) {
        // f) Welche weiblichen Vornamen, die mit „N“ beginnen, sind in der Untersuchungsdaten vorhanden,
        //    ohne Duplikate?

        // TODO
        System.out.println("TODO");
    }

    private static void taskG_Maenner_Groesser180_DurschnittlicherGewichtsverlust(List<Person> persons) {
        // g) Wie hoch ist die durchschnittliche Gewichtsveränderung aller männliche Untersuchungspersonen mit einer
        //    Größe von zumindest 1,8 m?

        // TODO
        System.out.println("TODO");
    }

    private static void taskH_10ZufaelligePersonen_Gewichtsverlust_OrderedByAge(List<Person> persons) {
        // h) Geben Sie 10 zufällige Untersuchungspersonen aus, welche Gewicht verloren haben. Sortieren Sie die
        //    Liste nach dem BMI.

        // TODO
        System.out.println("TODO");
    }

}
