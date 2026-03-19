package snake.controllers;

import javafx.animation.AnimationTimer;
import snake.model.Grid;
import snake.model.Player;
import snake.view.SnakeRenderer;

public class SnakeLoop extends AnimationTimer{
    private final SnakeRenderer renderer;
    private final Player player;
    private final Grid grid;

    private long updateTime;
    private long updateInterval;

    public SnakeLoop(Player p, Grid g, SnakeRenderer r) {
        updateTime = 0;
        updateInterval = 250_000_000;

        renderer = r;
        player = p;
        grid = g;
    }

    @Override
    public void handle(long now) {
        if (now-updateTime >= updateInterval) {
            update();
            updateTime = now;
        }
    }

    private void move() {
        player.move();
    }
    private void checkCollision() {

    }
    private void render() {
        //cells[player.getx()][player.gety()].setFill(Color.RED);
        renderer.render(grid);
    }
    
    public void update() {
        //move();
        //checkCollision();
        //render();
    }
}
