package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileManager{
    private String filename = "scores.txt";

    public void saveScore(String username, int score) {

        Map<String, Integer> scores = new HashMap<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");
                String fileUsername = parts[0]; //"navn"
                int fileScore = Integer.parseInt(parts[1]); //"score"

                scores.put(fileUsername, fileScore);
            }
        }
        catch(IOException e) {
        }

        if (scores.containsKey(username)) {
            if (score > scores.get(username)) {
                scores.put(username, score);
            }
        }
        else {
            scores.put(username,score);
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, Integer> entry : scores.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        }
        catch(IOException e) {}
    }

    public Map<String,Integer> loadScores() {
        Map<String, Integer> scores = new HashMap<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String fileUsername = parts[0];
                int fileScore = Integer.parseInt(parts[1]);

                scores.put(fileUsername, fileScore);
            }
        }
        catch(IOException e) {}
        return scores;
    }
}