package block.controllers;

import java.util.function.Consumer;

import block.model.Blocks;
import block.model.Player;
import block.view.BlockRenderer;
import javafx.animation.AnimationTimer;

public class BlockLoop extends AnimationTimer{
    private final BlockRenderer renderer;
    private final Player player;
    private final Blocks blocks;


    private boolean gameOver;
    private int score;

    private long updateTime;
    private long updateInterval;
    private int frameCount;


    private Consumer<Integer> onScoreChanged = score -> {};
    private Consumer<Integer> onLevelChanged = level -> {};
    private Runnable onGameOver = () -> {};

    public BlockLoop(BlockRenderer r, Player p, Blocks b) {
        updateTime = 0;
        updateInterval = 50_000_000;
        frameCount = 0;

        renderer = r;
        player = p;
        blocks = b;
        this.gameOver = false;
        this.score = 0;
    }

    public void setOnGameOver(Runnable callback) {
        this.onGameOver = callback;
    }

    public int getScore() {
        return score;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public void handle(long now) {
        if (now-updateTime >= updateInterval) {
            update();
            updateTime = now;
        }
    }

    public void update() {
        frameCount ++;
        if (frameCount%30==0) {blocks.spawnBlock();}
        blocks.update(player.getCol());
        if (blocks.isHit()) { onGameOver.run(); }
        renderer.clear();
        renderer.renderBlocks(blocks);
        renderer.renderPlayer(player);
    }
}