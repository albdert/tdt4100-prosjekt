package wolf.view;

import app.common.Vector2d;
import app.common.Vector2di;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class WolfRenderer {
    GraphicsContext gc;
    Vector2di dim;

    public WolfRenderer(GraphicsContext g, Vector2di d) {
        gc = g;
        dim = d;
    }

    public void clear(Color c) {
        gc.setFill(c);
        gc.clearRect(0, 0, 640, 480);
        gc.fillRect(0, 0, 640, 480);
    }

    public void clearTop(Color c) {
        gc.setFill(c);
        gc.fillRect(0, 0, 640, 240);
    }
    public void clearBot(Color c) {
        gc.setFill(c);
        gc.fillRect(0, 240, 640, 480);
    }

    //---------------------
    // Rendering i 2d
    //---------------------
    public void drawVec(Vector2d s, Vector2d e, Color c) {
        gc.setStroke(c);
        gc.setLineWidth(1);
        gc.strokeLine(s.x, s.y, s.x+e.x*10, s.y+e.y*10);
    }

    public void drawLine(Vector2d s, Vector2d e) {
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

    public void drawPlayer2d(Vector2d p) {
        gc.setFill(Color.RED);
        gc.fillRect(p.x-5, p.y-5, 10, 10);
    }

    
    //---------------------
    // Rendering i 3d
    //---------------------
    public void drawWallSegment(int x, double dist, int type, int side) {
        int lineHeight = (int) (dim.y/dist);

        // finner start og end pixel
        int start = (-lineHeight / 2) + (dim.y/2);
        if (start<0) { start=0; }
        int end = (lineHeight / 2) + (dim.y/2);
        if (end>=dim.y) { end=dim.y-1; }
        
        switch (type) {
        case 1 -> gc.setStroke(Color.RED);
        case 2 -> gc.setStroke(Color.GREEN);
        case 3 -> gc.setStroke(Color.BLUE);
        case 4 -> gc.setStroke(Color.WHITE);
        default-> gc.setStroke(Color.YELLOW);
        }

        if (side == 1) {
            gc.setStroke(((Color)gc.getStroke()).darker());
        }

        gc.setLineWidth(1);
        gc.strokeLine(x+0.5, start, x+0.5, end);
    }

    public void drawGun(String path) {
        Image gun = new Image(path);
        gc.drawImage(gun, dim.x/2-32, dim.y-64);    
        gc.drawImage(gun, dim.x/2-128, dim.y-256, 256,256);
    }

    public void drawEnemy(String path, Vector2d projection, double[] zBuffer) {
        int spriteHeight = (int)(dim.y / (projection.y));
        int spriteWidth = spriteHeight; 
        
        int screenX = (int)(dim.x / 2 * (1 + projection.x));
        
        int startX = screenX - spriteWidth / 2;
        int endX = screenX + spriteWidth / 2;
        
        int startY = dim.y / 2 - spriteHeight / 2;
        
        //TODO: lagre denne og ikke opprett ny image hver gang
        Image sprite = new Image(path);
        
        for (int x = startX; x < endX; x++) {
            if (x < 0 || x >= dim.x) continue; // off screen
            if (projection.y < zBuffer[x]) {  
                double spritecol = (x - startX) / (double)spriteWidth;
                int srcX = (int)(spritecol * sprite.getWidth());
                gc.drawImage(sprite, srcX, 0, 1, sprite.getHeight(), 
                             x, startY, 1, spriteHeight);            
            }
        }
    }
}
