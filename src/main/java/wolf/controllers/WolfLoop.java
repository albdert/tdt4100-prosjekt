package wolf.controllers;

import javafx.animation.AnimationTimer;

public class WolfLoop extends AnimationTimer {
    private final WolfController c;
    private long updateTime;
    private long updateInterval;

    public WolfLoop(WolfController c) {
        this.c = c;
        updateTime = 0;
        updateInterval = 50_000_000;
    }

    @Override
    public void handle(long now) {
        if (now-updateTime >= updateInterval) {
            c.update();
            updateTime = now;
        }
    }
}