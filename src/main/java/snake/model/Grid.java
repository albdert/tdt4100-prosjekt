package snake.model;

import java.util.Arrays;

public class Grid {
    public static final int ROWS = 30;
    public static final int COLS = 22;
    private final GridCell[][] cells;

    public Grid() {
        cells = new GridCell[ROWS][COLS];
        for (GridCell[] row : cells) {
            Arrays.fill(row, GridCell.EMPTY);
        }
    }

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
}
