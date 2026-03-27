package wolf.controllers;

import app.common.Vector2d;
import app.controllers.GameController;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import wolf.model.Player;
import wolf.model.World;
import wolf.view.WolfRenderer;

public class WolfController extends GameController {

    @FXML private Canvas canvas;
    private GraphicsContext gc;

    private Player player;
    private World world;
    private WolfLoop loop;
    private WolfRenderer renderer;

    private Vector2d mouse = new Vector2d(0, 0);

    @Override
    protected void initGame() {
        canvas.setOnMouseMoved(e -> {mouse.x = e.getX(); mouse.y = e.getY();});
        canvas.setOnMouseDragged(e -> {mouse.x = e.getX(); mouse.y = e.getY();});

        gc = canvas.getGraphicsContext2D();

        renderer = new WolfRenderer(gc);
        player = new Player();
        world = new World();

        loop = new WolfLoop(gc, renderer, world, player, activeKeys, activeMouseButtons, mouse);
        loop.start();
    }

    @Override
    protected void handleInput(KeyCode key) {
        switch (key) {
            case SPACE -> {}
            case ESCAPE -> {togglePauseMenu();}
        }
    }
    
    @Override protected void pauseGame() { loop.stop(); }
    @Override protected void resumeGame() { loop.start(); }
}
