package com.psymk6.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreUtil {
    public static void appendScore(String name, int score) {
        // Add a new score to the scores file
        // If no name entered, use default
        if(name.matches("")){name = "anonymous";}
        try {
            // Write name and score to the file.
            Files.writeString(Paths.get("src/main/resources/assets/scores/scores.csv"), name + "," + score + System.lineSeparator(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getTopScores() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/assets/scores/scores.csv"));

            // Collect the top 5 scores in descending order
            List<String> topScores = lines.stream()
                    .map((String line) -> {
                        String[] parts = line.split(",");
                        String name = parts[0];
                        int score = Integer.parseInt(parts[1]);
                        return name + " - " + score;
                    })
                    .sorted(Comparator.comparingInt((String s) -> Integer.parseInt(s.split(" - ")[1])).reversed())
                    .limit(5)
                    .collect(Collectors.toList());

            return String.join(System.lineSeparator(), topScores);

        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception (log, show an error to the user, etc.)
            return "";
        }
    }

}
