package wolf.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import app.common.Vector2d;
import app.common.Vector2di;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import wolf.model.Enemy;
import wolf.model.Player;
import wolf.model.World;
import wolf.view.WolfRenderer;

public class WolfLoop extends AnimationTimer {
    private Vector2di dim;

    private final WolfRenderer renderer;
    private final World world;
    private final Player player;
    private final List<Enemy> enemies;

    Set<KeyCode> keys;
    Set<MouseButton> mButtons;
    Vector2d mPos;

    boolean drawLine;
    boolean drawIntersection;

    private long updateTime;
    private long updateInterval;

    public WolfLoop(WolfRenderer r, Vector2di d, Set<KeyCode> k, Set<MouseButton> m, Vector2d mouse) {
        updateTime = 0;
        updateInterval = 10_000_000;

        dim = d;
        renderer = r;

        world = new World();
        player = new Player();
        enemies = new ArrayList<>();
        enemies.add(new Enemy());

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
        if (keys.contains(KeyCode.W)) {player.moveForward(world.getArr());};
        if (keys.contains(KeyCode.S)) {player.moveBackward(world.getArr());};
        if (keys.contains(KeyCode.A)) {player.lookLeft();};
        if (keys.contains(KeyCode.D)) {player.lookRight();};

        if (keys.contains(KeyCode.SPACE)) {player.shoot();}

        // 2d debugging inputs
        if (mButtons.contains(MouseButton.PRIMARY)) {drawLine=true;}
        else {drawLine=false;}
        if (mButtons.contains(MouseButton.SECONDARY)) {drawIntersection=true;}
        else {drawIntersection=false;} 
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
            double cameraX = 2 * 320 / (double)dim.x - 1; //x-coordinate in camera space
            renderer.drawLine(player.getPos(), player.calcRay(world.getArr(), cameraX));
        }
    }

    private void render3d() {
        renderer.clear(Color.BLACK);
        renderer.clearTop(Color.LIGHTBLUE);
        renderer.clearBot(Color.GRAY);

        double[] zBuffer = new double[dim.x];
        for (int x=0; x<dim.x; x++) {
            double cameraX = 2 * x / (double)dim.x - 1;             
            player.calcRay(world.getArr(), cameraX);
            zBuffer[x] = player.lwd;
            renderer.drawWallSegment(x,player.lwd,player.lwt,player.lws);
        }

        for (Enemy enemy : enemies) {
            Vector2d projection = player.projectEnemy(enemy);
            if (projection.y > 0 && Math.abs(projection.x) < 1) {
                renderer.drawEnemy(enemy.getSprite(), projection, zBuffer);
            }
        }

        renderer.drawGun(player.gun.getSprite());
    }

    public void hitchecks() {
        for (Enemy enemy : enemies) {
            if (player.getGun().isFiring()) {
                System.out.println("Firing");
                if (player.checkHit(enemy.getPos(), world.getArr())) {
                    System.out.println("\nTreff!\n");
                    enemy.shot();
                }
            }
        }
    }

    public void update() {
        handleInput();
        player.update();
        hitchecks();
        //enemies.update();
        render3d();

        //render2d();
    }
}