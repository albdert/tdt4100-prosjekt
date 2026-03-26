package app.controllers;

import java.io.IOException;

import app.AppState;
import app.SceneManager;
import app.common.Paths;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NameInputController {

    @FXML private TextField nameInput;
    @FXML private Label errorLabel;

    @FXML
    private void handleSaveName() throws IOException {
        String name = nameInput.getText().trim();

        if (name.isEmpty()) {
            errorLabel.setText("⚠  Name cannot be empty.");
            return;
        }
        if (name.length() < 2) {
            errorLabel.setText("⚠  Name must be at least 2 characters.");
            return;
        }
        if (name.length() > 20) {
            errorLabel.setText("⚠  Name cannot exceed 20 characters.");
            return;
        }
        if (!name.matches("[a-zA-Z0-9 _-]+")) {
            errorLabel.setText("⚠  Only letters, numbers, spaces, _ and - allowed.");
            return;
        }

        errorLabel.setText(""); 
        AppState.getInstance().setPlayerName(name);
        setScene(Paths.MENU);
    }

    @FXML
    private void handleButtonClick(Event e) throws IOException {
        String id = ( (Node)e.getSource() ).getId();
        switch (id) {
            case "menu" -> {setScene(Paths.MENU);}
            case "quit" -> {Platform.exit();}
        }
    }
    
    private void setScene(String path) throws IOException {
        SceneManager.getInstance().setScene(path);
    }
}