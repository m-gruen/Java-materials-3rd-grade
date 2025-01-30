import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {

        List<Match> file = null;
        try {
            file = FileReader.readMatchesFromFile(Path.of("files/bundesliga_23_24.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String myTeam = "Sturm Graz";

        Predicate<Match> isMyTeam = match -> match.homeTeam().equals(myTeam) || match.awayTeam().equals(myTeam);
        Predicate<Match> isTotalGroup = match -> match.group().equals(Group.GESAMT);
        Predicate<Match> isQualificationGroup = match -> match.group().equals(Group.QUALIFIKATION);
        Predicate<Match> isMasterGroup = match -> match.group().equals(Group.MEISTER);
        Predicate<Match> wonGamesMyTeam = match -> match.getWinner().equals(myTeam);
        Predicate<Match> drawGamesMyTeam = match -> match.getWinner().equals("unentschieden");
        Predicate<Match> zeroZeroGames = match -> match.homeGoals() == 0 && match.awayGoals() == 0;

        System.err.println("Fragen zur Bundesliga-Saison 2023/24:");
        System.err.println();

        // a)
        System.out.println("a) Drucken Sie alle Spiele des Lieblingsvereins!");
        file.stream()
                .filter(isMyTeam)
                .forEach(System.out::println);
        System.out.println();

        // b)
        System.out.println("b) Drucken Sie alle Spiele Ihres Vereins in der Gesamtgruppe!");
        file.stream()
                .filter(isMyTeam.and(isTotalGroup))
                .forEach(System.out::println);
        System.out.println();

        // c)
        System.out.println("c) Hat Ihr Verein in der Meistergruppe gespielt?");
        boolean playedInMasterGroup = file.stream()
                .anyMatch(isMyTeam.and(isMasterGroup));
        System.out.println(playedInMasterGroup ? "Ja" : "Nein");
        System.out.println();

        // d)
        System.out.println("d) Drucken Sie alle in der Gesamtgruppe gewonnene Spiele Ihres Vereins!");
        file.stream()
                .filter(isMyTeam.and(isTotalGroup).and(wonGamesMyTeam))
                .forEach(System.out::println);
        System.out.println();

        // e)
        System.out.println("e) Drucken Sie alle in der Gesamtgruppe unentschieden gespielte Spiele Ihres Vereins!");
        file.stream()
                .filter(isMyTeam.and(isTotalGroup).and(drawGamesMyTeam))
                .forEach(System.out::println);
        System.out.println();

        // f)
        System.out.println("f) Drucken Sie alle 0:0-Spiele Ihres Vereins!");
        file.stream()
                .filter(isMyTeam.and(zeroZeroGames))
                .forEach(System.out::println);
        System.out.println();

        // g)
        System.out.println("g) Wie viele Spiele hat Ihre Mannschaft insgesamt gewonnen?");
        long wonGames = file.stream()
                .filter(wonGamesMyTeam)
                .count();
        System.out.println(wonGames);
        System.out.println();

        // h)
        System.out.println("h) Wie viele Tore hat Ihre Mannschaft insgesamt erzielt?");
        int totalGoals = file.stream()
                .filter(isMyTeam)
                .mapToInt(c -> {
                    if (c.homeTeam().equals(myTeam)) {
                        return c.homeGoals();
                    } else {
                        return c.awayGoals();
                    }
                })
                .sum();
        System.out.println(totalGoals);
        System.out.println();

        // i)
        System.out.println("i) Wie ist die gesamte Torbilanz Ihrer Mannschaft?");
        int totalGoalsDifference = file.stream()
                .filter(isMyTeam)
                .mapToInt(c -> {
                    if (c.homeTeam().equals(myTeam)) {
                        return c.homeGoals() - c.awayGoals();
                    } else {
                        return c.awayGoals() - c.homeGoals();
                    }
                })
                .sum();
        System.out.println(totalGoalsDifference);
        System.out.println();

        // j)
        System.out.println("j) Wie viele Punkte hat Ihre Mannschaft in der Gesamtgruppe erreicht?");
        int totalPoints = file.stream()
                .filter(isMyTeam.and(isTotalGroup))
                .mapToInt(c -> {
                    if (c.getWinner().equals(myTeam)) {
                        return 3;
                    } else if (c.getWinner().equals("unentschieden")) {
                        return 1;
                    } else {
                        return 0;
                    }
                })
                .sum();
        System.out.println(totalPoints);
        System.out.println();

        // k)
        System.out.println("k) Welche Spiele wurden am 20. Spieltag ausgetragen?");
        file.stream()
                .filter(c -> c.playDay() == 20)
                .forEach(System.out::println);
        System.out.println();

        // l)
        System.out.println("l) Welche Spiele wurden im April ausgetragen?");
        file.stream()
                .filter(c -> c.date().getMonthValue() == 4)
                .forEach(System.out::println);
        System.out.println();

        // m)
        System.out.println("m) Bei welchen Spielen war die Tordifferenz größer als 5?");
        file.stream()
                .filter(c -> Math.abs(c.homeGoals() - c.awayGoals()) > 5)
                .forEach(System.out::println);
        System.out.println();

        // n)
        System.out
                .println("n) Bei wie vielen Spielen in der Gesamtgruppe hat eine Mannschaft mehr als 5 Tore erzielt?");
        long gamesWithMoreThanFiveGoals = file.stream()
                .filter(isTotalGroup)
                .filter(c -> c.homeGoals() > 5 || c.awayGoals() > 5)
                .count();
        System.out.println(gamesWithMoreThanFiveGoals);
        System.out.println();

        // o)
        System.out.println("o) Bei welchen Spielen hat eine Mannschaft das Spiel nach der 1. Halbzeit gedreht?");
        file.stream()
                .filter(c -> c.homeGoalsHalfTime() < c.awayGoalsHalfTime() && c.homeGoals() > c.awayGoals()
                        || c.homeGoalsHalfTime() > c.awayGoalsHalfTime() && c.homeGoals() < c.awayGoals())
                .forEach(System.out::println);
        System.out.println();

        // p)
        System.out.println("p) Listen Sie alle Mannschaften der Qualifikationsgruppe auf!");
        file.stream()
                .filter(isQualificationGroup)
                .flatMap(match -> List.of(match.homeTeam(), match.awayTeam()).stream())
                .distinct()
                .forEach(System.out::println);
        System.out.println();

        // q)
        System.out.println("q) Listen Sie alle Mannschaften der Meistergruppe auf!");
        file.stream()
                .filter(isMasterGroup)
                .map(Match::homeTeam)
                .distinct()
                .forEach(System.out::println);
        System.out.println();

        // r)
        System.out.println("r) Welche Mannschaft hat in der Gesamtgruppe die meisten Spiele gewonnen?");
        Map<String, Long> teamWins = new HashMap<>();

        file.stream()
                .filter(isTotalGroup)
                .map(Match::getWinner)
                .filter(winner -> !winner.equals("unentschieden"))
                .forEach(winner -> teamWins.put(winner, teamWins.getOrDefault(winner, 0L) + 1));

        String teamWithMostWins = "Keine Spiele gewonnen";
        long maxWins = 0;

        for (String team : teamWins.keySet()) {
            long wins = teamWins.get(team);
            if (wins > maxWins) {
                maxWins = wins;
                teamWithMostWins = team;
            }
        }

        System.out.println(teamWithMostWins);
        System.out.println();

        // Stellen Sie weitere 10 Fragen!
        System.out.println("Weitere 10 Fragen:");
        System.out.println();

        // 1)
        System.out.println("1) Wie viele Spiele hat Ihre Mannschaft in der Qualifikationsgruppe verloren?");
        long lostGames = file.stream()
                .filter(isMyTeam.and(isQualificationGroup).and(wonGamesMyTeam.negate()).and(drawGamesMyTeam.negate()))
                .count();
        System.out.println(lostGames);
        System.out.println();

        // 2)
        System.out.println("2) Wie viele Tore hat Ihre Mannschaft in der Meistergruppe erzielt?");
        int totalGoalsMasterGroup = file.stream()
                .filter(isMyTeam.and(isMasterGroup))
                .mapToInt(c -> {
                    if (c.homeTeam().equals(myTeam)) {
                        return c.homeGoals();
                    } else {
                        return c.awayGoals();
                    }
                })
                .sum();
        System.out.println(totalGoalsMasterGroup);
        System.out.println();

        // 3)
        System.out.println("3) Wie viele Tore hat Ihre Mannschaft in der Qualifikationsgruppe kassiert?");
        int totalGoalsConcededQualificationGroup = file.stream()
                .filter(isMyTeam.and(isQualificationGroup))
                .mapToInt(c -> {
                    if (c.homeTeam().equals(myTeam)) {
                        return c.awayGoals();
                    } else {
                        return c.homeGoals();
                    }
                })
                .sum();
        System.out.println(totalGoalsConcededQualificationGroup);
        System.out.println();

        // 4)
        System.out.println("4) Wie viele Spiele hat Ihre Mannschaft in der Gesamtgruppe unentschieden gespielt?");
        long drawGames = file.stream()
                .filter(isMyTeam.and(isTotalGroup).and(drawGamesMyTeam))
                .count();
        System.out.println(drawGames);
        System.out.println();

        // 5)
        System.out.println("5) Wie viele Tore hat Ihre Mannschaft in der Gesamtgruppe erzielt?");
        int totalGoalsTotalGroup = file.stream()
                .filter(isMyTeam.and(isTotalGroup))
                .mapToInt(c -> {
                    if (c.homeTeam().equals(myTeam)) {
                        return c.homeGoals();
                    } else {
                        return c.awayGoals();
                    }
                })
                .sum();
        System.out.println(totalGoalsTotalGroup);
        System.out.println();

        // 6)
        System.out.println("6) Wie viele Tore hat Ihre Mannschaft in der Meistergruppe kassiert?");
        int totalGoalsConcededMasterGroup = file.stream()
                .filter(isMyTeam.and(isMasterGroup))
                .mapToInt(c -> {
                    if (c.homeTeam().equals(myTeam)) {
                        return c.awayGoals();
                    } else {
                        return c.homeGoals();
                    }
                })
                .sum();
        System.out.println(totalGoalsConcededMasterGroup);
        System.out.println();

        // 7)
        System.out.println("7) Wie viele Punkte hat Ihre Mannschaft in der Qualifikationsgruppe erreicht?");
        int totalPointsQualificationGroup = file.stream()
                .filter(isMyTeam.and(isQualificationGroup))
                .mapToInt(c -> {
                    if (c.getWinner().equals(myTeam)) {
                        return 3;
                    } else if (c.getWinner().equals("unentschieden")) {
                        return 1;
                    } else {
                        return 0;
                    }
                })
                .sum();
        System.out.println(totalPointsQualificationGroup);
        System.out.println();

        // 8)
        System.out.println("8) Wie viele Punkte hat Ihre Mannschaft in der Meistergruppe erreicht?");
        int totalPointsMasterGroup = file.stream()
                .filter(isMyTeam.and(isMasterGroup))
                .mapToInt(c -> {
                    if (c.getWinner().equals(myTeam)) {
                        return 3;
                    } else if (c.getWinner().equals("unentschieden")) {
                        return 1;
                    } else {
                        return 0;
                    }
                })
                .sum();
        System.out.println(totalPointsMasterGroup);
        System.out.println();

        // 9)
        System.out.println("9) Wie viele Tore hat Ihre Mannschaft in der Qualifikationsgruppe erzielt?");
        int totalGoalsQualificationGroup = file.stream()
                .filter(isMyTeam.and(isQualificationGroup))
                .mapToInt(c -> {
                    if (c.homeTeam().equals(myTeam)) {
                        return c.homeGoals();
                    } else {
                        return c.awayGoals();
                    }
                })
                .sum();
        System.out.println(totalGoalsQualificationGroup);
        System.out.println();

        // 10)
        System.out.println("10) Wie viele Tore hat Ihre Mannschaft in der Gesamtgruppe kassiert?");
        int totalGoalsConcededTotalGroup = file.stream()
                .filter(isMyTeam.and(isTotalGroup))
                .mapToInt(c -> {
                    if (c.homeTeam().equals(myTeam)) {
                        return c.awayGoals();
                    } else {
                        return c.homeGoals();
                    }
                })
                .sum();
        System.out.println(totalGoalsConcededTotalGroup);
    }
}
