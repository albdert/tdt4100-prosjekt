package snake.controllers;

import app.SceneManager;
import app.controllers.GameController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
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
            //case SPACE -> {debug();}
            case SPACE -> {snake.extend();}

            case ESCAPE -> {togglePauseMenu();}
        }
    }

    // TODO: fjerne denne funksjonen når den ikke trengs
    private void debug() {
        Parent root = SceneManager.getInstance().getStage().getScene().getRoot();
        System.out.println("\n");
        System.out.println("Window height : " + root.getLayoutBounds().getHeight());
        System.out.println("Window width: " + root.getLayoutBounds().getWidth());
        System.out.println("\n");
    }

    @Override
    protected void initGame() {
        double w = canvas.getWidth();
        double h = canvas.getHeight();
        gc = canvas.getGraphicsContext2D();

        renderer = new SnakeRenderer(gc, w, h);
        snake = new Snake();
        grid = new Grid();
        food = new Food(grid.ROWS, grid.COLS);

        loop = new SnakeLoop(renderer, snake, food);
        loop.setOnScoreChanged(score -> scoreLabel.setText(String.valueOf(score)));
        loop.setOnLevelChanged(level -> levelLabel.setText(String.valueOf(level)));
        loop.setOnGameOver(() -> Platform.runLater(() -> gameOver()));
        loop.start();
    }

    @Override
    protected void pauseGame() {
        // TODO: rydde opp i denne funksjonen
        System.out.println("\n\nPaused game\n\n");
        loop.stop();
    }

    @Override
    protected void resumeGame() {
        // TODO: rydde opp i denne funksjonen
        System.out.println("\n\nUn-paused game\n\n");
        loop.start();
    }
} 