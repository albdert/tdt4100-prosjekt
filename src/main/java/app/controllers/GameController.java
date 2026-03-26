package app.controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.input.KeyCode;
import app.SceneManager;
import app.common.Paths;

public abstract class GameController {
    @FXML protected StackPane root;

    private VBox pauseMenu;
    protected boolean isPaused = false;

    private VBox gameOverScreen;
    protected boolean isGameOver = false;

    protected final Set<KeyCode> activeKeys = new HashSet<>();

    protected String username = "player";
    protected IntegerProperty score = new SimpleIntegerProperty(0);
    protected IntegerProperty level = new SimpleIntegerProperty(0);

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
            SceneManager.getInstance().getStage().getScene().setOnKeyPressed(e -> {
                    activeKeys.add(e.getCode());
                    handleInput(e.getCode());
            });

            SceneManager.getInstance().getStage().getScene()
                .setOnKeyReleased(e -> activeKeys.remove(e.getCode()));
        });

        initPauseMenu();
        initGameOverScreen();
        initGame();
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
     * Laster inn GameOver FXML fil.
     */
    private void initGameOverScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.GAMEOVER));
            gameOverScreen = loader.load();
            GameOverController goc = loader.getController();
            goc.getScoreLabel().textProperty().bind(score.asString());
            goc.getLevelLabel().textProperty().bind(level.asString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Laster inn og lukker PauseMenu.
     */
    protected void togglePauseMenu() {
        if (isGameOver) { return; }
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
     * Game over overlay
     */
    public void gameOver() {
        root.getChildren().add(gameOverScreen);
        isGameOver = true;
        pauseGame();
    }

    /**
     * Holder username definert i hovedmeny for å lagre scores/highscore
     * @param name
     */
    public void setUsername(String name) {
        username = name;
    }
    /**
     * Getter for username-variabel
     * @return String username
     */
    public String getUsername() {
        return username;
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
