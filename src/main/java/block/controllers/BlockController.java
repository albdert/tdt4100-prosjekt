package block.controllers;


import app.controllers.GameController;
import block.model.Blocks;
import block.model.Player;
import block.view.BlockRenderer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class BlockController extends GameController{   
    private Player player;
    private Blocks blocks;
    private BlockRenderer renderer;
    private BlockLoop loop;
    
    private String username;
    private int frameCount = 0;

    @FXML private Canvas canvas;
    private GraphicsContext gc;

    @FXML private Label scoreLabel;

    @FXML private TextField usernameField;

    @Override
    protected void handleInput(KeyCode key) {
        switch (key) {
            case LEFT,A  -> {player.moveLeft();}
            case RIGHT,D -> {player.moveRight();}
            case ESCAPE -> {togglePauseMenu();}
        }
    }

    @Override
    protected void initGame() {
        username = usernameField.getText();
        gc = canvas.getGraphicsContext2D();

        player = new Player();
        blocks = new Blocks();
        renderer = new BlockRenderer(gc);

        loop = new BlockLoop(renderer, player, blocks);
        loop.setOnGameOver(() -> Platform.runLater(() -> gameOver()));
        loop.start();
        pauseGame();
    }

    @Override
    protected void pauseGame() { loop.stop(); }

    @Override
    protected void resumeGame() { loop.start(); }
    public void startGame(){ loop.start(); }
}

