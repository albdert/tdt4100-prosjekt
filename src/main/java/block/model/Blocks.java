package block.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class Blocks {
    private List<Block> blocks = new ArrayList<>();
    private boolean hit = false;

    private Consumer<Integer> onScoreChange;
    private Consumer<Integer> onLevelChange;
    private int score;
    private int level;

    public int getScore() { return score; }
    public int getLevel() { return level; }

    public List<Block> getBlocks() {
        return blocks;
    }

    // callback funksjoner for level og score
    public void setOnScoreChanged(Consumer<Integer> callback) {
        this.onScoreChange = callback;
    }
    public void setOnLevelChanged(Consumer<Integer> callback) {
        this.onLevelChange = callback;
    }

    public boolean isHit() {
        return hit;
    }
    
    private boolean isInAnotherBlock(int col) {
        return false;
    }

/* 
    public void spawnBlock() {
        Random random = new Random();
        for (int i=0; i<level+1; i++) {
            int randomCol = random.nextInt(9);
            if (isInAnotherBlock(randomCol)) { continue; }
            Block randomBlockSpawn = new Block(randomCol);
            blocks.add(randomBlockSpawn);
        }
    }
    */

    public void spawnBlock() {
        Random random = new Random();
        int blocksToSpawn = Math.min(level + 1,8);
        int spawned = 0;
    
    while (spawned < blocksToSpawn) {
        int randomCol = random.nextInt(9);
        if (!isInAnotherBlock(randomCol)) {
            Block randomBlockSpawn = new Block(randomCol);
            blocks.add(randomBlockSpawn);
            spawned++;
        }
    }
}
//sjekker om spilleren blir truffet og om blocken går forbi "row" nummer 8 og skal gi score++
    public void update(int col) {
        Iterator<Block> iterator = blocks.iterator();
        while (iterator.hasNext()) {
            Block block = iterator.next();
            block.moveDown();
            if (block.getRow() == 7 && block.getCol()==col) {
                hit = true;
                block.moveUp();
                return;
            }
            if (block.getRow() == 8) {
                iterator.remove();
                onScoreChange.accept(10);
                score+=10;
                if (score%100==0) { 
                    level++; 
                    onLevelChange.accept(1);
                }
            }
        }
    }
}
