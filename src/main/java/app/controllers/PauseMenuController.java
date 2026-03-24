package app.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import app.SceneManager;
import app.common.Paths;

public class PauseMenuController {

    private GameController gc;

    @FXML
    private void handleButtonClick(ActionEvent e) throws IOException {
        String id = ( (Button)e.getSource() ).getId();
        switch (id) {
            case "mainmenu" -> {menu();}
            case "resume"   -> {resume();}
        }
    }

    public void setGameController(GameController gc) {
        this.gc = gc;
    }

    private void menu() throws IOException {
        SceneManager.getInstance().setScene(Paths.MENU);
    }

    private void resume() {
        gc.togglePauseMenu();
    }
}
