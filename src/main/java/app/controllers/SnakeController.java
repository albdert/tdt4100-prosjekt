package app.controllers;

import app.SceneManager;
import app.snake.Player;
import app.snake.SnakeLoop;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

// import snake game components
//import app.snake.Player;

public class SnakeController extends GameController {
    private static final int ROWS = 30;
    private static final int COLS = 22;

    @FXML
    private GridPane grid;
    private Rectangle[][] cells = new Rectangle[ROWS][COLS];

    private Player player;
    private SnakeLoop loop;

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
    @FXML

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
        initGrid();
        player = new Player();
        loop = new SnakeLoop(this);
        loop.start();
    }

    public void update() {
        move();
        checkCollision();
        render();
    }

    @Override
    protected void pauseGame() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'pauseGame'");
        System.out.println("\n\nPaused game\n\n");
        loop.stop();
    }

    @Override
    protected void resumeGame() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'resumeGame'");
        System.out.println("\n\nUn-paused game\n\n");
        loop.start();
    }

    private void initGrid() {
        for (int i=0; i<ROWS; i++) {
            for (int j = 0; j<COLS; j++) {
                Rectangle cell = new Rectangle(20, 20);
                //cell.setFill(Color.DARKGRAY);
                grid.add(cell, i, j);
                cells[i][j] = cell;
            }
        }
    }

    private void move() {
        player.move();
    }
    private void checkCollision() {

    }
    private void render() {
        cells[player.getx()][player.gety()].setFill(Color.RED);
    }
} 