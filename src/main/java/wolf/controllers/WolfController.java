package wolf.controllers;

import app.common.Vector2d;
import app.common.Vector2di;
import app.controllers.GameController;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import wolf.view.WolfRenderer;

public class WolfController extends GameController {

    @FXML private Canvas canvas;
    private GraphicsContext gc;
    public Vector2di dim;

    private WolfLoop loop;
    private WolfRenderer renderer;

    private Vector2d mouse = new Vector2d(0, 0);

    @Override
    protected void initGame() {
        canvas.setOnMouseMoved(e -> {mouse.x = e.getX(); mouse.y = e.getY();});
        canvas.setOnMouseDragged(e -> {mouse.x = e.getX(); mouse.y = e.getY();});

        gc = canvas.getGraphicsContext2D();
        dim = new Vector2di((int)canvas.getWidth(), (int)canvas.getHeight());

        renderer = new WolfRenderer(gc, dim);

        loop = new WolfLoop(renderer, dim, activeKeys, activeMouseButtons, mouse);
        loop.start();
    }

    @Override
    protected void handleInput(KeyCode key) {
        if (key==KeyCode.ESCAPE) { togglePauseMenu(); }
    }
    
    @Override protected void pauseGame() { loop.stop(); }
    @Override protected void resumeGame() { loop.start(); }
}
