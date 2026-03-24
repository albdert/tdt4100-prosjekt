package block.model;

public class Block {
    private int column;
    private int row;

    public Block(int column) {
        this.column = column;
        this.row = 0;
    }

    public void moveDown() {
        row++;
    }

    public int getCol() {
        return column;
    }
    
    public int getRow() {
        return row;
    }
}
