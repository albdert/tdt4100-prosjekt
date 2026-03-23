package snake.model;

import app.common.Vector2di;

public class Snake {
    public enum Dir {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    private Vector2di[] segments;
    private int len = 0;

    private Dir dir = Dir.UP;
    private boolean dirChanged = false;
    private boolean dead = false;

    public Snake() {
        segments = new Vector2di[50];
        for (int i=0; i<5; i++) {
            segments[i] = new Vector2di((17/2), (15/2)+i);
            len++;
        }
    }

    public int size() {
        return len;
    }

    public Vector2di[] getSegments() {
        return segments;
    }

    public boolean posInSnake(Vector2di v, int start) {
        for (int i=start; i<len; i++) {
            if (segments[i].equals(v)) { return true; }
        }
        return false;
    }

    public void changeDir(Dir d) {
        if (dirChanged) { return; }
        if (dir==Dir.UP && d==Dir.DOWN) { return; }
        if (dir==Dir.DOWN && d==Dir.UP) { return; }
        if (dir==Dir.LEFT && d==Dir.RIGHT) { return; }
        if (dir==Dir.RIGHT && d==Dir.LEFT) { return; }
        dir = d;
        dirChanged = true;
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
        if (checkCollision()) {
            segments[0].x = prevx;
            segments[0].y = prevy;
            dead = true;
            return;
        }

        for (int i=1; i<len; i++) {
            int newx = segments[i].x;
            int newy = segments[i].y;
            segments[i].x = prevx;
            segments[i].y = prevy;
            prevx = newx;
            prevy = newy;
        }

        dirChanged = false;
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
    
    public boolean isOnFood(Food food) {
        if (segments[0].equals(food.getPos())) { return true; }
        return false;
    }

    public boolean checkCollision() {
        Vector2di head = segments[0];
        if (posInSnake(head, 1)) { return true; };
        if (head.x==-1 || head.x==Grid.ROWS) { return true;};
        if (head.y==-1 || head.y==Grid.COLS) { return true;};

        return false;
    }
    public boolean isDead() { return dead; }
}
