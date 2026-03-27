package wolf.view;

import app.common.Vector2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WolfRenderer {
    GraphicsContext gc;

    public WolfRenderer(GraphicsContext g) {
        gc = g;
    }

    public void clear() {
        gc.setFill(Color.WHITE);
        gc.clearRect(0, 0, 640, 480);
        gc.fillRect(0, 0, 640, 480);
    }

    public void drawLine(Vector2d s, Vector2d e, Color c) {
        gc.setStroke(c);
        gc.setLineWidth(1);
        gc.strokeLine(s.x, s.y, s.x+e.x*10, s.y+e.y*10);
    }
    public void testLine(Vector2d s, Vector2d e) {
        gc.setStroke(Color.RED);
        gc.setLineWidth(1);
        gc.strokeLine(s.x, s.y, e.x, e.y);
    }
    
    public void drawgrid() {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        for (int i=0; i<640; i+=20) {
            gc.strokeLine(i,0,i,480);
        }
        for (int i=0; i<480; i+=20) {
            gc.strokeLine(0, i, 640, i);
        }
    }

    public void drawWorld(int[][] map) {
        for (int i=0; i<map.length; i++) {
            for (int j=0; j<map[0].length; j++) {
                switch(map[i][j]) {
                case 1 -> gc.setFill(Color.BLUE);
                case 2 -> gc.setFill(Color.DARKBLUE);
                case 3 -> gc.setFill(Color.GREEN);
                case 4 -> gc.setFill(Color.DARKGREEN);
                }
                if (map[i][j]==0) {continue;}
                gc.fillRect(j*20, i*20, 20, 20);
            }
        }
    }

    public void drawPlayer(Vector2d p) {
        gc.setFill(Color.RED);
        gc.fillRect(p.x-5, p.y-5, 10, 10);
    }
    
    public void render() {
        gc.setFill(Color.BLUE);
        gc.fillRect(75, 75, 100, 100);
        gc.fillOval(200, 200, 30, 20);
    }
    public void testRender(int x, int y) {
        gc.setFill(Color.RED);
        gc.fillRect(x, y, 10, 10);
    }
}
