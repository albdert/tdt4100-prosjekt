package app.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import app.FileManager;
import app.SceneManager;
import app.common.Paths;

public class HighscoreController {
    class ScoreEntry {
        String game;
        String player;
        int score;

        public ScoreEntry(String g, String p, int s) {
            game = g;
            player = p; 
            score = s;
        }

        public String getGame() {return game;}
        public String getPlayer() {return player;}
        public int getScore() {return score;}
    }

    enum Sorted {GAME, PLAYER, SCORE}
    private boolean sortAscending = true;

    FileManager f = new FileManager();
    Map<String, Map<String, Integer>> scores = f.loadScores();

    List<ScoreEntry> scoreList;
    Sorted sorting = Sorted.GAME;

    @FXML private VBox scoresContainer;

    @FXML
    public void initialize() {
        initScoreList();
        handleSortByGame();
        populateScores(scoreList);
    }

    @FXML
    private void handleButtonClick(ActionEvent e) throws IOException {
        String id = ( (Button)e.getSource() ).getId();
        switch (id) {
            case "mainMenu"     -> {menu();}
            case "quit"         -> {Platform.exit();}
        }
    }

    public void handleSortByGame() {
        sortScores(scoreList, Comparator.comparing(ScoreEntry::getGame));
    }
    public void handleSortByPlayer() {
        sortScores(scoreList, Comparator.comparing(ScoreEntry::getPlayer));
    }
    public void handleSortByScore() {
        sortScores(scoreList, Comparator.comparingInt(ScoreEntry::getScore));
    }

    private void menu() throws IOException {
        SceneManager.getInstance().setScene(Paths.MENU);
    }

    private void initScoreList() {
        scoreList = new ArrayList<>();
        for (Map.Entry<String, Map<String, Integer>> games : scores.entrySet()) {
            Map<String, Integer> inner = games.getValue();
            for (Map.Entry<String, Integer> entry : inner.entrySet() ) {
                scoreList.add(
                    new ScoreEntry(games.getKey(), entry.getKey(), entry.getValue())
                );
            }
        }
    }

    private void sortScores(List<ScoreEntry> scores, Comparator<ScoreEntry> comparator) {
        scores.sort(sortAscending ? comparator : comparator.reversed());
        sortAscending = !sortAscending;
        populateScores(scores);
    }

    private void populateScores(List<ScoreEntry> scores) {
        scoresContainer.getChildren().clear();
        for (int i = 0; i < scores.size(); i++) {
            ScoreEntry entry = scores.get(i);
            HBox row = new HBox();
            row.setStyle(i % 2 == 0
                ? "-fx-background-color: #111122; -fx-padding: 10 16;"
                : "-fx-background-color: #0d0d0f; -fx-padding: 10 16;"
            );

            Label rank   = new Label(String.valueOf(i + 1));
            Label game   = new Label(entry.getGame());
            Label player = new Label(entry.getPlayer());
            Label score  = new Label(String.valueOf(entry.getScore()));

            String labelStyle = "-fx-text-fill: #ccccdd; -fx-font-size: 13;";
            rank.setStyle(labelStyle);   rank.setMinWidth(40);
            game.setStyle(labelStyle);   game.setMinWidth(200);
            player.setStyle(labelStyle); player.setMinWidth(200);
            score.setStyle("-fx-text-fill: #4ade80; -fx-font-size: 13; -fx-font-weight: bold;");

            row.getChildren().addAll(rank, game, player, score);
            scoresContainer.getChildren().add(row);
        }
    }
}
