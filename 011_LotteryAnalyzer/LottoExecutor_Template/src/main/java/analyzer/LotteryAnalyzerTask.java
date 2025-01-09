package analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LotteryAnalyzerTask implements Runnable {
    private Path file;
    private List<Integer> winingNumbers;

    public LotteryAnalyzerTask(Path file, List<Integer> winingNumbers) {
        this.file = file;
        this.winingNumbers = winingNumbers;
    }

    private List<LotteryTip> readLotteryTipsFromFile(Path filePath) throws IOException {
        List<LotteryTip> tips = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);
        for (String line : lines) {
            tips.add(processLine(line));
        }
        return tips;
    }

    private LotteryTip processLine(String line) {
        String[] parts = line.split(",");
        String id = parts[0];
        int[] numbers = new int[6];
        for (int i = 0; i < 6; i++) {
            numbers[i] = Integer.parseInt(parts[i + 1]);
        }
        return new LotteryTip(id, numbers);
    }

    private int getCorrectCount(LotteryTip tip) {
        int correctCount = 0;
        for (int number : tip.numbers()) {
            if (winingNumbers.contains(number)) {
                correctCount++;
            }
        }
        return correctCount;
    }

    private void printResult(LotteryTip tip, int correctCount) {
        StringBuilder result = new StringBuilder();
        result.append("Thread: ").append(Thread.currentThread().getName()).append(", ");
        result.append(tip.id()).append(": ");
        for (int number : tip.numbers()) {
            if (winingNumbers.contains(number)) {
                result.append(number).append("* ");
            } else {
                result.append(number).append(" ");
            }
        }
        result.append("- Corr ").append(correctCount);
        System.out.println(result.toString());
    }

    @Override
    public void run() {
        try {
            List<LotteryTip> tips = readLotteryTipsFromFile(file);
            for (LotteryTip tip : tips) {
                int correctCount = getCorrectCount(tip);

                if (correctCount >= 5) {
                    printResult(tip, correctCount);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
