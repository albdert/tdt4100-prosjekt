package block.view;

import block.model.Block;
import block.model.Blocks;
import block.model.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BlockRenderer {
    GraphicsContext gc;

    public BlockRenderer(GraphicsContext g){
        gc = g;
    }

    public void clear() {
        //tøm skjermen
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,450,400);
    }

    public void renderPlayer(Player p) {
        //tegn spilleren
        gc.setFill(Color.BLUE);
        gc.fillRect(p.getCol() * 50, 7*50, 50, 50);
    }

    public void renderBlocks(Blocks b) {
        //tegn blokkene
        gc.setFill(Color.RED);
        for (Block block : b.getBlocks()) {
            gc.fillRect(block.getCol() * 50, block.getRow() * 50, 50 ,50);
        }
    }
}
