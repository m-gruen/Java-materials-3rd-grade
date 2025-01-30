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
                "%s %d %s %s %s vs. %s %d:%d %d:%d",
                group.getDisplayName(),
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
