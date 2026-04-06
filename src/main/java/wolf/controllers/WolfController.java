package wolf.controllers;

import app.common.Vector2d;
import app.common.Vector2di;
import app.controllers.GameController;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import wolf.model.Player;
import wolf.view.WolfRenderer;

public class WolfController extends GameController {

    @FXML private Canvas canvas;
    private GraphicsContext gc;
    public Vector2di dim;

    @FXML private Label scoreLabel;
    @FXML private Label healthLabel;
    @FXML private Label armorLabel;
    @FXML private Label ammoLabel;

    private WolfLoop loop;
    private WolfRenderer renderer;
    private Player player;

    private Vector2d mouse = new Vector2d(0, 0);

    @Override
    protected void initGame() {
        scoreLabel.textProperty().bind(score.asString());

        canvas.setOnMouseMoved(e -> {mouse.x = e.getX(); mouse.y = e.getY();});
        canvas.setOnMouseDragged(e -> {mouse.x = e.getX(); mouse.y = e.getY();});

        gc = canvas.getGraphicsContext2D();
        dim = new Vector2di((int)canvas.getWidth(), (int)canvas.getHeight());

        renderer = new WolfRenderer(gc, dim);

        player = new Player(); 
        player.setOnGameOver(() -> gameOver());
        healthLabel.textProperty().bind(player.healthProperty().asString());
        armorLabel.textProperty().bind(player.armorProperty().asString());
        ammoLabel.textProperty().bind(player.ammoProperty().asString());

        loop = new WolfLoop(renderer, dim, player, activeKeys, activeMouseButtons, mouse);
        loop.setOnScoreChanged(s -> score.set(s));
        loop.start();
    }

    @Override
    protected void handleInput(KeyCode key) {
        if (key==KeyCode.ESCAPE) { togglePauseMenu(); }
    }
    
    @Override protected void pauseGame() { loop.stop(); }
    @Override protected void resumeGame() { loop.start(); }
    @Override public String getGameName() {
        return "Wolf";
    }
}
