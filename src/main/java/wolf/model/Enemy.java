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

    private boolean dead = false;
    private int health = 2;

    private int aframe = 0;
    private int frames = 4;

    private int frameDelay = 4;
    private int frameTimer = 0;

    public Enemy() {
        //gun = new Gun();
        pos  = new Vector2d(40,120);

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

    public void shot() {
        if (health==0) { return; }
        health--;
        if (health==0) { frameTimer = frameDelay; }
    }

    public void update() {
        if (dead) { return; }
        if (health==0) {
            if (frameTimer>0) {
                frameTimer--;
            } else {
                nextFrame();
                frameTimer = frameDelay;
            }
            return;
        }
    }

    public void nextFrame() {
        aframe++;
        if (aframe>frames) { 
            aframe=0; 
            dead = true;
        }
    }

    public String getSprite() {
        return sprites[0];
    }

    public Vector2d getPos() {
        return pos;
    }
}
