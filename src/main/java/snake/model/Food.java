package snake.model;

import java.util.Random;

import app.common.Vector2di;

public class Food {
    private Random r = new Random();
    private int rows, cols;
    private Vector2di pos;

    public Food(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        nextPos();
    }
    
    public void nextPos() {
        pos = new Vector2di(r.nextInt(rows), r.nextInt(cols));
    }

    public Vector2di getPos() {
        return pos;
    }
}
