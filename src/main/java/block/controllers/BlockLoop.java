package block.controllers;

import block.model.Blocks;
import block.model.Player;
import block.view.BlockRenderer;
import javafx.animation.AnimationTimer;

public class BlockLoop extends AnimationTimer{
    private final BlockRenderer renderer;
    private final Player player;
    private final Blocks blocks;

    private long updateTime;
    private long updateInterval;
    private int frameCount;
    private int bui;
    private int bsi;
    private int maxBlocks;

    private Runnable onGameOver = () -> {};

    public BlockLoop(BlockRenderer r, Player p, Blocks b) {
        updateTime = 0;
        updateInterval = 50_000_000;
        frameCount = 0;
        bui = 5;
        bsi = 15;
        maxBlocks = 8;

        renderer = r;
        player = p;
        blocks = b;
    }

    // callback funksjon for å holde styr på gamestate
    public void setOnGameOver(Runnable callback) {
        this.onGameOver = callback;
    }

    @Override
    public void handle(long now) {
        if (now-updateTime >= updateInterval) {
            update();
            updateTime = now;
        }
    }

    public void update() {
        if (frameCount%bui==0) { blocks.update(player.getCol()); }
        if (frameCount%bsi==0) { 
            blocks.spawnBlock(); 
        }

        if (blocks.isHit()) { onGameOver.run(); }
        renderer.clear();
        renderer.renderBlocks(blocks);
        renderer.renderPlayer(player);
        frameCount ++;
    }
    public void setBlockSpawnInterval( int interval) {
        this.bsi = Math.max(interval, bui + 1);
    }
    public void setBlockUpdateInterval(int interval) {
        this.bui = interval;
        this.bsi = Math.max(bsi, bui + 1);
    }
    public void setMaxBlocks(int max) {
        this.maxBlocks = max;
    }
}