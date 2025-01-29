import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class App {
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
    }
}
