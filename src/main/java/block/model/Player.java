package block.model;

public class Player {
    private int column;

    public Player() {
        this.column = 4;
    }
    
    public boolean moveLeft() {
        if (column == 0) {
            return false;
        }
        column--;
        return true;
    }
    public boolean moveRight() {
        if (column == 8) {
            return false;
        }
        column++;
        return true;
    }

    public int getCol() {
        return column;
    }
}
