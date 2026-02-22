package app.controllers;

import java.io.IOException;

import app.Paths;
import app.SceneManager;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;

public class MainMenuController {
    @FXML
    private void handleButtonClick(Event e) throws IOException {
        String id = ( (Node)e.getSource() ).getId();

        switch (id) {
            case "snake"     -> {launchGame(Paths.SNAKE);}
            case "tictactoe" -> {launchGame(Paths.TEST);}
            case "test"      -> {launchGame(Paths.TEST);}
            case "settings"  -> {debug();}
            case "credits"   -> {}
            case "quit"      -> {Platform.exit();}
            default -> {
                System.out.println("hello world");
            }
        }
    }   
    //TODO: fjerne denne funksjonen når den ikke trengs lengre
    private void debug() {
        Parent root = SceneManager.getInstance().getStage().getScene().getRoot();
        System.out.println("\n");
        System.out.println("Window height : " + root.getLayoutBounds().getHeight());
        System.out.println("Window width: " + root.getLayoutBounds().getWidth());
        System.out.println("\n");
    }

    //TODO: endre funksjon til et mer generelt navn
    private void launchGame(String path) throws IOException {
        SceneManager.getInstance().setScene(path);
    }
}
