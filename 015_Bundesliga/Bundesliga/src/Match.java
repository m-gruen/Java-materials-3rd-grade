import java.time.LocalDate;
import java.time.LocalTime;

public record Match(
        Group group,
        int playDay,
        LocalDate date,
        LocalTime time,
        String homeTeam,
        String awayTeam,
        int homeGoals,
        int awayGoals,
        int homeGoalsHalfTime,
        int awayGoalsHalfTime) {

    @Override
    public String toString() {
        return String.format(
                "Gruppe: %s, Spieltag: %d, Datum: %s, Uhrzeit: %s, Heimmannschaft: %s, Gastmannschaft: %s, Tore Heim: %d, Tore Gast: %d, Tore Heim HZ: %d, Tore Gast HZ: %d",
                group,
                playDay,
                date,
                time,
                homeTeam,
                awayTeam,
                homeGoals,
                awayGoals,
                homeGoalsHalfTime,
                awayGoalsHalfTime);
    }

    public String getWinner() {
        if (homeGoals > awayGoals) {
            return homeTeam;
        } else if (awayGoals > homeGoals) {
            return awayTeam;
        } else {
            return "unentschieden";
        }
    }
}
