package app.snake;

public class Player {
    private enum Dir {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    private int posx;
    private int posy;
    private int len;

    private Dir dir;

    public Player() {
        posx = 14;
        posy = 11;
        dir = Dir.UP;
    }
    
    public void up()    { dir = Dir.UP;    }
    public void down()  { dir = Dir.DOWN;  }
    public void left()  { dir = Dir.LEFT;  }
    public void right() { dir = Dir.RIGHT; }
    public void move()  {
        switch (dir) {
            case UP    -> posy--; 
            case DOWN  -> posy++;
            case LEFT  -> posx--;
            case RIGHT -> posx++;
        }
    }

    public int getx() { return posx; }
    public int gety() { return posy; }
}
