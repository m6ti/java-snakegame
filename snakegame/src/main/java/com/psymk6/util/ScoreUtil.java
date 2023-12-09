package com.psymk6.util;

import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The ScoreUtil class provides utility methods for managing and retrieving game scores.
 * It interacts with a CSV file to store and retrieve player scores.
 *
 * @author Mateusz Klocek
 * @version 1.0
 */
public class ScoreUtil {
    /**
     * A string which stores the scores file path.
     */
    private static final String SCORES_FILE_PATH = "/assets/scores/scores.csv";

    /**
     * Appends a new score to the scores file.
     *
     * @param name The name of the player.
     * @param score The player's score.
     */
    public static void appendScore(String name, int score) {
        // Add a new score to the scores file
        // If no name entered, use default
        if (name.matches("")) {
            name = "anonymous";
        }
        try {
            // Get the path to the scores file
            Path scoresPath = getScoresFilePath();
            // Create the scores file if it doesn't exist
            if (!Files.exists(scoresPath)) {
                Files.createFile(scoresPath);
            }
            // Write name and score to the file.
            Files.writeString(scoresPath, name + "," + score + System.lineSeparator(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the top scores from the scores file.
     *
     * @return A formatted string containing the top scores.
     */
    public static String getTopScores() {
        try {
            // Get the path to the scores file
            Path scoresPath = getScoresFilePath();
            // Read existing content
            List<String> lines = Files.readAllLines(scoresPath);
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

    /**
     * Gets the path to the scores file.
     *
     * @return The Path object representing the scores file path.
     */
    private static Path getScoresFilePath() {
        // Get the path to the scores file
        String userDir = System.getProperty("user.dir");
        return Paths.get(userDir, "src", "main", "resources", SCORES_FILE_PATH);
    }
}
