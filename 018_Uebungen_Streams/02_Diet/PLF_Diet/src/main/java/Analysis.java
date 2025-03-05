import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        long canceled = persons.stream()
                .filter(Person::hasAborted)
                .count();

        System.out.printf("Canceled: %d (%.2f%%)%n", canceled, (double) canceled / (double) persons.size() * 100.0);
    }

    private static void taskB_weiblich_60Plus_2kgMinus_OrderedByAge(List<Person> persons) {
        // b) Welche weiblichen Untersuchungspersonen mit einem Alter von mindestens 60 Jahren haben mehr als 2 kg
        //    Gewicht verloren, sortiert nach dem Alter?

        persons.stream()
                .filter(person -> !person.hasAborted())
                .filter(person -> person.gender() == 'W')
                .filter(person -> person.getAge() >= 60)
                .filter(person -> person.getWeightDifference() <= -2.0)
                .forEach(System.out::println);
    }

    private static void taskC_Top3Gewichtsverlust(List<Person> persons) {
        // c) Welche 3 Personen haben das meiste Gewicht verloren?

        persons.stream()
                .filter(person -> !person.hasAborted())
                .sorted(Comparator.comparingDouble(Person::getWeightDifference))
                .limit(3)
                .forEach(System.out::println);
    }

    private static void taskD_Maenner_Groesser180_Kleiner70kg(List<Person> persons) {
        // d) Haben an der Untersuchung auch männliche Personen mit einer Größe von zumindest 1,8 m und einem
        //    Gewicht von weniger als 70 kg (Zeitpunkt T1) teilgenommen?

        boolean haveParticipated = persons.stream()
                .anyMatch(person -> person.gender() == 'M' && person.height() > 180 && person.weight_T1() < 70);

        System.out.println(haveParticipated ? "Ja" : "Nein");
    }

    private static void taskE_AnzahlPersonen_5KgMinus_NachGeschlecht(List<Person> persons) {
        // e) Wie viele Personen haben mehr als 5 kg verloren, gruppiert nach dem Geschlecht?

        persons.stream()
                .filter(person -> !person.hasAborted())
                .filter(person -> person.getWeightDifference() < -5.0)
                .collect(Collectors.groupingBy(Person::gender, Collectors.counting()))
                .forEach((gender, count) -> System.out.println(gender + ": " + count));
    }

    private static void taskF_Weiblich_VornameBeginnMitN(List<Person> persons) {
        // f) Welche weiblichen Vornamen, die mit „N“ beginnen, sind in der Untersuchungsdaten vorhanden,
        //    ohne Duplikate?

        persons.stream()
                .filter(person -> person.gender() == 'W')
                .map(Person::firstname)
                .filter(firstname -> firstname.charAt(0) == 'N')
                .distinct()
                .forEach(System.out::println);
    }

    private static void taskG_Maenner_Groesser180_DurschnittlicherGewichtsverlust(List<Person> persons) {
        // g) Wie hoch ist die durchschnittliche Gewichtsveränderung aller männliche Untersuchungspersonen mit einer
        //    Größe von zumindest 1,8 m?

        double avgMale = persons.stream()
                .filter(person -> !person.hasAborted())
                .filter(person -> person.gender() == 'M')
                .filter(person -> person.height() >= 180)
                .mapToDouble(Person::getWeightDifference)
                .average()
                .orElse(0);

        System.out.printf("%.3f kg%n", avgMale);
    }

    private static void taskH_10ZufaelligePersonen_Gewichtsverlust_OrderedByAge(List<Person> persons) {
        // h) Geben Sie 10 zufällige Untersuchungspersonen aus, welche Gewicht verloren haben. Sortieren Sie die
        //    Liste nach dem BMI.

        Random rand = new Random();
        Stream.generate(() -> persons.get(rand.nextInt(persons.size())))
                .filter(person -> !person.hasAborted())
                .filter(person -> person.getWeightDifference() < 0)
                .distinct()
                .limit(10)
                .sorted(Comparator.comparingDouble(Person::getBMI))
                .forEach(System.out::println);
    }

}
