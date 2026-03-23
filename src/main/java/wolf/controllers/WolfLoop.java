package wolf.controllers;

import java.util.Set;

import app.common.Vector2d;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import wolf.model.Player;
import wolf.model.World;
import wolf.view.WolfRenderer;

public class WolfLoop extends AnimationTimer {
    private final GraphicsContext gc;
    private final WolfRenderer renderer;
    private final Player player;
    private final World world;

    Set<KeyCode> keys;

    private long updateTime;
    private long updateInterval;

    public WolfLoop(GraphicsContext g, WolfRenderer r, World w, Player p, Set<KeyCode> a) {
        updateTime = 0;
        updateInterval = 10_000_000;

        gc = g;
        renderer = r;
        player = p;
        world = w;

        keys = a;
    }

    @Override
    public void handle(long now) {
        if (now-updateTime >= updateInterval) {
            update();
            updateTime = now;
        }
    }

    private void handleInput() {
        if (keys.contains(KeyCode.W)) {player.posChange(new Vector2d(0, -2));};
        if (keys.contains(KeyCode.S)) {player.posChange(new Vector2d(0, 2));};
        if (keys.contains(KeyCode.A)) {player.posChange(new Vector2d(-2, 0));};
        if (keys.contains(KeyCode.D)) {player.posChange(new Vector2d(2, 0));};
    }

    private void move() {
    }

    private void checkCollision() {

    }

    private void render() {
        renderer.clear(gc);
        renderer.drawgrid(gc);
        renderer.render(gc, 640, 480);
        renderer.testRender(gc, player.getPos().x, player.getPos().y);
    }
    
    public void update() {
        handleInput();
        move();
        checkCollision();
        render();
    }
}