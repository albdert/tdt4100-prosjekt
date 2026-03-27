package wolf.controllers;

import java.util.Set;

import app.common.Vector2d;
import app.common.Vector2di;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import wolf.model.Player;
import wolf.model.World;
import wolf.view.WolfRenderer;

public class WolfLoop extends AnimationTimer {
    private final GraphicsContext gc;
    private Vector2di dim;

    private final WolfRenderer renderer;
    private final Player player;
    private final World world;

    Set<KeyCode> keys;
    Set<MouseButton> mButtons;
    Vector2d mPos;

    boolean drawLine;
    boolean drawIntersection;

    private long updateTime;
    private long updateInterval;

    public WolfLoop(GraphicsContext g, WolfRenderer r, World w, Player p, 
                    Set<KeyCode> k, Set<MouseButton> m, Vector2d mouse, Vector2di d) {
        updateTime = 0;
        updateInterval = 10_000_000;

        gc = g;
        dim = d;
        renderer = r;
        player = p;
        world = w;

        keys = k;
        mButtons = m;
        mPos = mouse;
    }

    @Override
    public void handle(long now) {
        if (now-updateTime >= updateInterval) {
            update();
            updateTime = now;
        }
    }

    private void handleInput() {
        if (keys.contains(KeyCode.W)) {player.moveForward();};
        if (keys.contains(KeyCode.S)) {player.moveBackward();};
        if (keys.contains(KeyCode.A)) {player.lookLeft();};
        if (keys.contains(KeyCode.D)) {player.lookRight();};

        if (mButtons.contains(MouseButton.PRIMARY)) {drawLine=true;}
        else {drawLine=false;}
        if (mButtons.contains(MouseButton.SECONDARY)) {drawIntersection=true;}
        else {drawIntersection=false;}

    }

    private void move() {
    }

    private void checkCollision() {
    }

    private void render2d() {
        renderer.clear(Color.WHITE);
        renderer.drawgrid();
        renderer.drawWorld(world.getArr());
        renderer.drawPlayer2d(player.getPos());

        // player direction vector
        renderer.drawVec(player.getPos(), player.getDeltaPos(), Color.DARKCYAN);

        // tegner linje fra spiller til mus
        if (drawLine) {
            renderer.drawLine(player.getPos(), mPos);
        }
        // tegner linje fra spiller til nærmeste vegg i spillerens retnign
        if (drawIntersection) {
            renderer.drawLine(player.getPos(), player.calcRay2d(world.getArr()));
        }
    }

    private void render3d() {
        renderer.clear(Color.BLACK);
        for (int x=0; x<dim.x; x++) {
            double cameraX = 2 * x / (double)dim.y - 1; //x-coordinate in camera space
            player.calcRay3d(world.getArr(), cameraX);
            renderer.drawWallSegment(x,player.lastWallDist,player.lastWallType,player.lastWallSide);
        }
    }
    
    public void update() {
        handleInput();
        move();
        checkCollision();
        //render2d();
        render3d();
    }
}