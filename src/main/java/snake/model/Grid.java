package snake.model;

import java.util.Arrays;

public class Grid {
    public static final int ROWS = 17;
    public static final int COLS = 15;
    public static final int SIZE = 30;
    private final int[][] cells;

    public Grid() {
        cells = new int[ROWS][COLS];
        /*
        for (GridCell[] row : cells) {
            Arrays.fill(row, GridCell.EMPTY);
        }
        */
    }

    //public int getSize() { return SIZE; }

    /*
    public void clearCells() {
        for (GridCell[] row : cells) {
            Arrays.fill(row, GridCell.EMPTY);
        }
    } 

    public GridCell getCell(int row, int col) {
        return cells[row][col];
    }

    public void setCell(int row, int col, GridCell type) {
        cells[row][col] = type;
    }
 */
}
