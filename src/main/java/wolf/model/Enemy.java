package wolf.model;

import app.common.Vector2d;

public class Enemy {
    public static final String f1  = "/sprites/enemy1.png";
    public static final String f2  = "/sprites/enemy1.png";
    public static final String f3  = "/sprites/enemy1.png";
    public static final String f4  = "/sprites/enemy1.png";
    private String[] sprites = {f1,f2,f3,f4};

    private double pi = Math.PI;
    private double tau = Math.TAU;

    private Vector2d pos;    
    private Vector2d dpos = new Vector2d(0, 0);
    private double angle;

     public Enemy() {
        //gun = new Gun();
        pos  = new Vector2d(60,40);

        angle  = 0;
        dpos.x = Math.cos(angle);
        dpos.y = Math.sin(angle);
    }   

    public void lookLeft() {
        angle -= 0.04;
        if (angle<0) { angle+=tau; }
        dpos.x = Math.cos(angle);
        dpos.y = Math.sin(angle);
    }

    public void lookRight() {
        angle += 0.04;
        if (angle>tau) { angle-=tau; }
        dpos.x = Math.cos(angle);
        dpos.y = Math.sin(angle);
    }

    public void moveForward(int[][] world) {
        Vector2d newpos = new Vector2d((pos.x+dpos.x), (pos.y+dpos.y));
        if (world[(int)(newpos.y/20)][(int)(newpos.x/20)]>0) { return; }
        pos = newpos;
    }

    public void moveBackward(int[][] world) {
        Vector2d newpos = new Vector2d((pos.x-dpos.x), (pos.y-dpos.y));
        if (world[(int)(newpos.y/20)][(int)(newpos.x/20)]>0) { return; }
        pos = newpos;
    }

    public void update() {

    }

    public String getSprite() {
        return sprites[0];
    }
}
