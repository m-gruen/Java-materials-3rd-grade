package analyzer;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LotteryAnalyzer {

    private static final List<Integer> WINING_NUMBERS = List.of(3, 8, 15, 22, 34, 42);
    private static final int NUMBER_OF_THREADS = 16;
    private static final String FILE_EXTENSION = ".txt";
    private static final String DIRECTORY_PATH = "files";

    public static void main(String[] args) {
        Path directoryPath = Paths.get(DIRECTORY_PATH);
        // abort if the directory does not exist
        if (!Files.exists(directoryPath) || !Files.isDirectory(directoryPath)) {
            System.err.printf("Directory '%s' does not exist.%n", DIRECTORY_PATH);
            return;
        }

        System.out.println("Gewinnzahlen: " + WINING_NUMBERS);

        List<Path> files = new ArrayList<>();
        try {
            files = getAllFiles(directoryPath, FILE_EXTENSION);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        
        for (Path file : files) {
            executorService.submit(new LotteryAnalyzerTask(file, WINING_NUMBERS));
        }

        executorService.shutdown();
    }

    public static List<Path> getAllFiles(Path directory, String extension) throws IOException {
        List<Path> result = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory, "*" +
                extension)) {
            for (Path entry : stream) {
                if (Files.isRegularFile(entry)) { // Ensure it's a file, not a directory
                    result.add(entry);
                }
            }
        }
        return result;
    }
}
