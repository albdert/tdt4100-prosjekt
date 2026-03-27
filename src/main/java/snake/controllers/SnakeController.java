package snake.controllers;

import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import app.controllers.GameController;
import snake.model.Food;
import snake.model.Grid;
import snake.model.Snake;
import snake.model.Snake.Dir;
import snake.view.SnakeRenderer;

public class SnakeController extends GameController {
    @FXML private Canvas canvas;
    private GraphicsContext gc;

    @FXML private Label scoreLabel;
    @FXML private Label highScoreLabel;
    @FXML private Label levelLabel;

    private Grid grid;
    private Snake snake;
    private Food food;
    private SnakeLoop loop;
    private SnakeRenderer renderer;

    @Override
    protected void handleInput(KeyCode key) {
        switch (key) {
            case UP,W    -> {snake.changeDir(Dir.UP);}
            case DOWN,S  -> {snake.changeDir(Dir.DOWN);}
            case LEFT,A  -> {snake.changeDir(Dir.LEFT);}
            case RIGHT,D -> {snake.changeDir(Dir.RIGHT);}
            case ESCAPE -> {togglePauseMenu();}
            //case SPACE -> {snake.extend();}
        }
    }

    @Override
    protected void initGame() {
        double w = canvas.getWidth();
        double h = canvas.getHeight();

        scoreLabel.textProperty().bind(score.asString());
        levelLabel.textProperty().bind(level.asString());

        gc = canvas.getGraphicsContext2D();

        renderer = new SnakeRenderer(gc, w, h);
        snake = new Snake();
        grid = new Grid();
        food = new Food(grid.ROWS, grid.COLS);

        loop = new SnakeLoop(renderer, snake, food);
        loop.setOnScoreChanged(s -> score.set(s));
        loop.setOnLevelChanged(l -> level.set(l));
        loop.setOnGameOver(() -> Platform.runLater(() -> gameOver()));
        loop.start();
    }

    @Override
    protected void pauseGame() { loop.stop(); }
    @Override
    protected void resumeGame() { loop.start(); }
} 