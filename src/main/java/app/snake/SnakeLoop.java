package app.snake;

import app.controllers.SnakeController;
import javafx.animation.AnimationTimer;

public class SnakeLoop extends AnimationTimer{
    private final SnakeController c;
    private long updateTime;
    private long updateInterval;

    public SnakeLoop(SnakeController c) {
        this.c = c;
        updateTime = 0;
        updateInterval = 500_000_000;
    }

    @Override
    public void handle(long now) {
        if (now-updateTime >= updateInterval) {
            c.update();
            updateTime = now;
        }
    }
    

}
