package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileManager{
    //private final static String filename = "highscores/highscores.txt";
    private final static String filename = "highscores.txt";

    public void saveScores(String game, String username, int score) {
        Map<String, Map<String, Integer>> scores = loadScores();

        if(!scores.containsKey(game)) {
            scores.put(game, new HashMap <String, Integer>());
        }

        if (!scores.get(game).containsKey(username) ||
           (score > scores.get(game).get(username))) {
            scores.get(game).put(username,score);
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, Map<String, Integer>> games : scores.entrySet()) {
                writer.write("#"+games.getKey()+ "\n");

                Map<String, Integer> inner = games.getValue();
                for (Map.Entry<String, Integer> entry : inner.entrySet() ) {
                    writer.write(entry.getKey() + "," + entry.getValue()+"\n");
                }
            }
        }
        catch(IOException e) {}
    }

    public Map<String, Map<String,Integer>> loadScores() {

        Map<String, Map<String, Integer>> scores = new HashMap<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            int i = -1;
            String[] game = new String[3];
            String line;
            while ((line = reader.readLine()) != null) {

                if (line.charAt(0)=='#'){
                    i++;
                    game[i] = line.split("#")[1];
                    scores.put(game[i], new HashMap<String, Integer>());
                    continue;
                }

                String[] parts = line.split(",");
                String fileUsername = parts[0]; //"navn"
                int fileScore = Integer.parseInt(parts[1]); //"score"

                scores.get(game[i]).put(fileUsername, fileScore);
            }
        }
        catch(IOException e) {}
        return scores;
    }
}