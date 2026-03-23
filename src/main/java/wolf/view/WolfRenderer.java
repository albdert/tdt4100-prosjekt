package wolf.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WolfRenderer {

    public void clear(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.clearRect(0, 0, 640, 480);
        gc.fillRect(0, 0, 640, 480);
    }
    
    public void drawgrid(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        for (int i=0; i<640; i+=10) {
            gc.strokeLine(i,0,i,480);
        }
        for (int i=0; i<480; i+=10) {
            gc.strokeLine(0, i, 640, i);
        }
    }
    public void render(GraphicsContext gc, int w, int h) {
        gc.setFill(Color.BLUE);
        gc.fillRect(75, 75, 100, 100);
        gc.fillOval(200, 200, 30, 20);
    }
    public void testRender(GraphicsContext gc, int x, int y) {
        gc.setFill(Color.RED);
        gc.fillRect(x, y, 20, 20);
    }
}
