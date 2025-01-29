import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FileReader {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static List<Match> readMatchesFromFile(Path filePath) throws IOException {
        return Files.lines(filePath)
                .skip(1)
                .map(FileReader::processLine)
                .toList();
    }

    private static Match processLine(String line) {
        String[] fields = line.split(",");
        Group group = Group.fromDisplayName(fields[0]);
        int playDay = Integer.parseInt(fields[1]);
        LocalDate date = LocalDate.parse(fields[2], DATE_FORMATTER);
        LocalTime time = LocalTime.parse(fields[3]);
        String homeTeam = fields[4];
        String awayTeam = fields[5];
        int homeGoals = Integer.parseInt(fields[6]);
        int awayGoals = Integer.parseInt(fields[7]);
        int homeGoalsHalfTime = Integer.parseInt(fields[8]);
        int awayGoalsHalfTime = Integer.parseInt(fields[9]);

        return new Match(
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
}