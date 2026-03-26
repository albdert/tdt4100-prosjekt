package app.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.scene.Node;

import app.SceneManager;
import app.common.Paths;

public class MainMenuController {
    @FXML
    private void handleButtonClick(Event e) throws IOException {
        String id = ( (Node)e.getSource() ).getId();

        switch (id) {
            case "snake"      -> {setScene(Paths.SNAKE);}
            case "tictactoe"  -> {setScene(Paths.BLOCK);}
            case "test"       -> {setScene(Paths.WOLF);}
            case "settings"   -> {}
            case "highscores" -> {setScene(Paths.HIGHSCORE);}
            case "quit"       -> {Platform.exit();}
        }
    }   

    private void setScene(String path) throws IOException {
        SceneManager.getInstance().setScene(path);
    }
}
