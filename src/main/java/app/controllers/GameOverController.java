package app.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import app.SceneManager;
import app.common.Paths;

public class GameOverController {
    //private GameController gc;

    @FXML private Label scoreLabel;
    @FXML private Label highScoreLabel;
    @FXML private Label levelLabel;

    public Label getScoreLabel() { return scoreLabel; }
    public Label getLevelLabel() { return levelLabel; }

    @FXML
    private void handleButtonClick(ActionEvent e) throws IOException {
        String id = ( (Button)e.getSource() ).getId();
        switch (id) {
            case "mainmenu"  -> {menu();}
            case "restart"   -> {restart();}
            case "savescore" -> {savescore();}
        }
    }

    public void setGameController(GameController gc) {
        //this.gc = gc;
    }

    private void menu() throws IOException {
        SceneManager.getInstance().setScene(Paths.MENU);
    }

    private void restart() throws IOException {
        String path = (String) SceneManager.getInstance().getStage().getScene().getUserData();
        SceneManager.getInstance().setScene(path);
    }  

    private void savescore() {}
}
