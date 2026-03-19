package snake.controllers;

import app.SceneManager;
import app.controllers.GameController;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import snake.model.Grid;
import snake.model.Player;
import snake.view.SnakeRenderer;

public class SnakeController extends GameController {
    @FXML private Canvas canvas;
    private GraphicsContext gc;

    private Player player;
    private Grid grid;
    private SnakeLoop loop;
    private SnakeRenderer renderer;

    @Override
    protected void handleInput(KeyCode key) {
        switch (key) {
            case UP,W    -> {player.up();}
            case DOWN,S  -> {player.down();}
            case LEFT,A  -> {player.left();}
            case RIGHT,D -> {player.right();}
            case SPACE -> {debug();}

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
        gc = canvas.getGraphicsContext2D();

        renderer = new SnakeRenderer();
        player = new Player();
        grid = new Grid();

        loop = new SnakeLoop(player, grid, renderer);
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