package block.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Blocks {
    private List<Block> blocks = new ArrayList<>();
    private boolean hit = false;

    public List<Block> getBlocks() {
        return blocks;
    }

    public boolean isHit() {
        return hit;
    }

    public void spawnBlock() {
        Random random = new Random();
        int randomColumn = random.nextInt(9);
        Block randomBlockSpawn = new Block(randomColumn);
        blocks.add(randomBlockSpawn);
    }
    
    public void update(int col) {
        Iterator<Block> iterator = blocks.iterator();

        while (iterator.hasNext()) {
            Block block = iterator.next();
            block.moveDown();
            //&& block.getColumn() == player.getColumn()
            if (block.getRow() == 7 && block.getCol()==col) {
                hit = true;
            }
            if (block.getRow() == 8) {
                //score ++;
                iterator.remove();
            }
        }
    }
}
