package wolf.controllers;
import app.SceneManager;
import app.controllers.GameController;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import wolf.model.Player;

public class WolfController extends GameController{

    @FXML private Canvas canvas;
    private double w, h;
    private GraphicsContext gc;

    // testing
    private int[] playervec = {30,20};
    private Player player;

    private WolfLoop loop;

    // TODO: fjerne denne funksjonen når den ikke trengs
    private void debug() {
        Parent root = SceneManager.getInstance().getStage().getScene().getRoot();
        System.out.println("\n");
        System.out.println("Window height : " + root.getLayoutBounds().getHeight());
        System.out.println("Window width: " + root.getLayoutBounds().getWidth());
        System.out.println("\n");

        System.out.println("\n");
        System.out.println("canvas height : " + h);
        System.out.println("canvas width: " + w);
        System.out.println("\n");
    }

    private void initCanvas() {
        w = canvas.getWidth();
        h = canvas.getHeight();

        this.gc = canvas.getGraphicsContext2D();
        this.gc.setFill(Color.WHITE);
    }

    @Override
    protected void initGame() {
        initCanvas();
        loop = new WolfLoop(this);
        loop.start();
    }

    @Override
    protected void handleInput(KeyCode key) {
        switch (key) {
            case UP,W    -> {playervec[1] -= 1; }
            case DOWN,S  -> {playervec[1] += 1; }
            case LEFT,A  -> {playervec[0] -= 1; }
            case RIGHT,D -> {playervec[0] += 1; }
            case SPACE -> {debug();}

            case ESCAPE -> {togglePauseMenu();}
        }
    }
    
    @Override
    protected void pauseGame() {
        // TODO: rydde opp i denne funksjonen
        //throw new UnsupportedOperationException("Unimplemented method 'pauseGame'");
        System.out.println("\n\nPaused game\n\n");
        loop.stop();
    }

    @Override
    protected void resumeGame() {
        // TODO: rydde opp i denne funksjonen
        //throw new UnsupportedOperationException("Unimplemented method 'resumeGame'");
        System.out.println("\n\nUn-paused game\n\n");
        loop.start();
    }

    private void move() {
        //player.move();
    }

    private void checkCollision() {

    }

    private void render() {
        gc.clearRect(0, 0, w, h);
        gc.setFill(Color.BLUE);
        gc.fillRect(75, 75, 100, 100);
        gc.fillOval(200, 200, 30, 20);
    }

    public void update() {
        move();
        checkCollision();
        render();
    }
}
