package app.controllers;

import java.io.IOException;

import app.AppState;
import app.FileManager;
import app.SceneManager;
import app.common.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameOverController {
    @FXML private Label scoreLabel;
    @FXML private Label highScoreLabel;
    @FXML private Label levelLabel;

<<<<<<< HEAD
    private GameController gameController;
=======
    @FXML private Label gameOverSymbol;
    @FXML private Label gameOverTitle;
    @FXML private Label gameOverText;

    @FXML private Button savescore;
>>>>>>> f1a1026be5547377c92d9d758e567f24e483f8c3

    public Label getScoreLabel() { return scoreLabel; }
    public Label getLevelLabel() { return levelLabel; }

    public void setGameController(GameController gc) {
        this.gameController = gc;
    }

    @FXML
    private void handleButtonClick(ActionEvent e) throws IOException {
        String id = ( (Button)e.getSource() ).getId();
        switch (id) {
            case "mainmenu"  -> {menu();}
            case "restart"   -> {restart();}
            case "savescore" -> {savescore();}
        }
    }

    public void showWinScreen() {
        gameOverTitle.setText("YOU WIN");
        gameOverText.setText("WELL DONE");

        gameOverSymbol.setStyle("-fx-text-fill: #4ade80; -fx-font-size: 28; -fx-font-weight: bold;");
        scoreLabel.setStyle("-fx-text-fill: #4ade80; -fx-font-size: 42; -fx-font-weight: bold; -fx-letter-spacing: 2;");
        savescore.setStyle(
        "-fx-background-color: #4ade80;" +
        "-fx-text-fill: #0a1a0a;" +
        "-fx-font-size: 13;" +
        "-fx-font-weight: bold;" +
        "-fx-letter-spacing: 3;" +
        "-fx-padding: 14 24;" +
        "-fx-background-radius: 6;" +
        "-fx-cursor: hand;"
        );
    }

    private void menu() throws IOException {
        SceneManager.getInstance().setScene(Paths.MENU);
    }

    private void restart() throws IOException {
        String path = (String) SceneManager.getInstance().getStage().getScene().getUserData();
        SceneManager.getInstance().setScene(path);
    }  

    private void savescore() {
        String username = AppState.getInstance().getPlayerName();
        int newScore = Integer.parseInt(scoreLabel.getText());

        FileManager fileManager = new FileManager();
        fileManager.saveScores(gameController.getGameName(), username, newScore);
    }
}
