package app.controllers;

import java.io.IOException;

import app.Paths;
import app.SceneManager;
import javafx.application.Platform;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public abstract class GameController {
    @FXML
    protected StackPane root;

    private VBox pauseMenu;
    protected boolean isPaused = false;

    /**
     * Initialize metode for GameController klasser.
     * <p>Denne metoden kalles automatisk av FXML når den leser inn en 
     * .fxml fil. 
     * <p>Metoden kaller på initGame() for å sette opp spillet og 
     * Oppretter en keyboard input eventlistener. 
     */ 
    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            SceneManager.getInstance().getStage().getScene()
                .setOnKeyPressed(e -> handleInput(e.getCode()));
        });

        initPauseMenu();
        initGame();
    }

    /**
     * Laster inn og lukker PauseMenu.
     */
    protected void togglePauseMenu() {
        if (isPaused) {
            root.getChildren().remove(pauseMenu);
            isPaused = false;
            resumeGame();
        } else {
            root.getChildren().add(pauseMenu);
            isPaused = true;
            pauseGame();
        }
    }

    /**
     * Laster inn PauseMenu FXML fil.
     */
    private void initPauseMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.PAUSE));
            pauseMenu = loader.load();
            PauseMenuController pc = loader.getController();
            pc.setGameController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Håndterer keyboard input fra bruker. 
     * <p>
     * Tar inn en Javafx KeyCode (se offisiell dokumentasjon).
     * <p>
     * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/input/KeyCode.html
     * 
     * @param key – Javafx KeyCode
     */
    protected abstract void handleInput(KeyCode key);
    /**
     * Metoden skal initialisere spillet.
     */
    protected abstract void initGame();
    /**
     * Klasse-spesifikk implementasjon for å sette spillet på pause.
     */
    protected abstract void pauseGame();
    /**
     * Klasse-spesifikk implementasjon for å fortsette spillet.
     */
    protected abstract void resumeGame();
}
