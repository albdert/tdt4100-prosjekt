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

    private GameController gameController;

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
