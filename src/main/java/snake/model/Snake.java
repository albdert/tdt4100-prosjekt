package snake.model;

import app.common.Vector2di;

public class Snake {
    public enum Dir {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    private Dir dir;
    private Vector2di[] segments;
    private int len;

    public Snake() {
        len = 0;
        dir = Dir.UP;
        segments = new Vector2di[50];
        for (int i=0; i<5; i++) {
            segments[i] = new Vector2di((17/2), (15/2)+i);
            len++;
        }
    }

    public Vector2di getHeadPos() {
        return segments[0];
    }

    public void changeDir(Dir d) {
        dir = d;
    }

    public Vector2di[] getSegments() {
        return segments;
    }

    public boolean posInSnake(Vector2di v) {
        for (int i=0; i<len; i++) {
            if (segments[i].equals(v)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return len;
    }

    public void move() {
        int prevx = segments[0].x;
        int prevy = segments[0].y;

        switch (dir) {
            case UP   -> segments[0].y-=1;
            case DOWN -> segments[0].y+=1;
            case LEFT -> segments[0].x-=1;
            case RIGHT-> segments[0].x+=1;
        }

        /* 
        if (warp) {
            if (snake->segments[0].y<0) { snake->segments[0].y=SIZE*(COLS-1); }
            if (snake->segments[0].y>=SCREEN_H) { snake->segments[0].y=0; }
            if (snake->segments[0].x<0) { snake->segments[0].x=SIZE*(ROWS-1); }
            if (snake->segments[0].x>=SCREEN_W) { snake->segments[0].x=0; }
        }*/

        for (int i=1; i<len; i++) {
            int newx = segments[i].x;
            int newy = segments[i].y;
            segments[i].x = prevx;
            segments[i].y = prevy;
            prevx = newx;
            prevy = newy;
        }
    }

    public void extend() {
        if (len+1==50) { return; } 
        int diffx = segments[len-2].x - segments[len-1].x;
        int diffy = segments[len-2].y - segments[len-1].y;

        Dir dir = Dir.UP;
        if (diffy>0) { dir = Dir.DOWN; }
        if (diffy<0) { dir = Dir.UP; }
        if (diffx>0) { dir = Dir.RIGHT; }
        if (diffx<0) { dir = Dir.LEFT; }

        int prevx = segments[len-1].x;
        int prevy = segments[len-1].y;

        Vector2di newSegment;
        switch (dir) {
            case UP -> newSegment = new Vector2di(prevx, prevy+1);
            case DOWN -> newSegment = new Vector2di(prevx, prevy-1);
            case LEFT -> newSegment = new Vector2di(prevx+1, prevy);
            case RIGHT -> newSegment = new Vector2di(prevx-1, prevy);
            default -> newSegment = new Vector2di(0,0);
        }

        segments[len] = newSegment;
        len++;
    }
}
