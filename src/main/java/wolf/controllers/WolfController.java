package wolf.controllers;

import app.SceneManager;
import app.controllers.GameController;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

import wolf.model.Player;
import wolf.model.World;
import wolf.view.WolfRenderer;

public class WolfController extends GameController{

    @FXML private Canvas canvas;
    private GraphicsContext gc;

    private Player player;
    private World world;
    private WolfLoop loop;
    private WolfRenderer renderer;

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

        renderer = new WolfRenderer();
        player = new Player();
        world = new World();

        loop = new WolfLoop(gc, renderer, world, player, activeKeys);
        loop.start();
    }

    @Override
    protected void handleInput(KeyCode key) {
        switch (key) {
            case SPACE -> {debug();}
            case ESCAPE -> {togglePauseMenu();}
        }
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
