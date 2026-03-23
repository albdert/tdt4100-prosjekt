package snake.view;

import app.common.Vector2di;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import snake.model.Food;
import snake.model.Grid;
import snake.model.Snake;

public class SnakeRenderer {
    private GraphicsContext gc;
    private double w, h;
    private int size = Grid.SIZE;

    public SnakeRenderer(GraphicsContext g, double w, double h) {
        gc = g;
        this.w = w;
        this.h = h;
    }

    public void clear() {
        gc.setFill(Color.WHITE);
        gc.clearRect(0, 0, w, h);
        gc.fillRect(0, 0, w, h);
    }

    public void renderGrid(Grid g) {
        gc.setFill(Color.LIGHTSEAGREEN);
        for (int i=0; i<Grid.ROWS; i++) {
            for (int j=0; j<Grid.COLS; j++) {
                /* 
                if ((i+j)%2==0) {
                    gc.setFill(Color.LIGHTSEAGREEN);
                } else {
                    gc.setFill(Color.LIGHTGREEN);
                }*/
                gc.setFill((i+j)%2==0 ? Color.LIGHTSEAGREEN : Color.LIGHTGREEN);
                gc.fillRect(i*size, j*size, size, size);
            }
        }
    };

    public void renderSnake(Snake p) {
        Vector2di[] segments = p.getSegments();
        gc.setFill(Color.DARKGREEN);
        gc.fillRoundRect(segments[0].x*size, segments[0].y*size, size, size, 15, 15);
        gc.setFill(Color.GREEN);
        for (int i=1; i<p.size(); i++) {
            gc.fillRoundRect(segments[i].x*size, segments[i].y*size, size, size, 15, 15);
        }
    };

    public void renderFood(Food f) {
        Vector2di pos = f.getPos();
        gc.setFill(Color.RED);
        gc.fillOval(pos.x*size, pos.y*size, size, size);
    };
}
