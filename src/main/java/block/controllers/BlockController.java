package block.controllers;

import app.controllers.GameController;
import block.model.Blocks;
import block.model.Player;
import block.view.BlockRenderer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class BlockController extends GameController {   
    private BlockRenderer renderer;
    private BlockLoop loop;
    private Player player;
    private Blocks blocks;


    @FXML private Canvas canvas;
    private GraphicsContext gc;

    @FXML private Label scoreLabel;
    @FXML private Label highScoreLabel;
    @FXML private Label levelLabel;

    @FXML private TextField usernameField;

    @Override
    protected void handleInput(KeyCode key) {
        switch (key) {
        case LEFT,A  -> {player.moveLeft();}
        case RIGHT,D -> {player.moveRight();}
        case ESCAPE  -> {togglePauseMenu();}
        }
    }

    @Override
    protected void initGame() {
        scoreLabel.textProperty().bind(score.asString());
        levelLabel.textProperty().bind(level.asString());

        gc = canvas.getGraphicsContext2D();

        renderer = new BlockRenderer(gc);
        player = new Player();

        blocks = new Blocks();
        blocks.setOnScoreChanged(p -> score.set(score.get()+p));
        blocks.setOnLevelChanged(i -> level.set(level.get()+i));

        loop = new BlockLoop(renderer, player, blocks);
        loop.setOnGameOver(() -> gameOver());
        loop.start();
        pauseGame();
    }

    @Override
    protected void pauseGame() { loop.stop(); }
    @Override
    protected void resumeGame() { loop.start(); }

    public void startGame(){ loop.start(); }

    @Override
    public String getGameName() {
        return "Block";
    }
}

