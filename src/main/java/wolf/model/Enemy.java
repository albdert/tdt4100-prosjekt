package wolf.model;

import app.common.Vector2d;
import app.common.Vector2di;

public class Enemy {
    public static final String r1  = "/sprites/enemy/enemy-run1.png";
    public static final String r2  = "/sprites/enemy/enemy-run2.png";
    public static final String r3  = "/sprites/enemy/enemy-run3.png";
    public static final String r4  = "/sprites/enemy/enemy-run4.png";

    public static final String d1  = "/sprites/enemy/enemy-death1.png";
    public static final String d2  = "/sprites/enemy/enemy-death2.png";
    public static final String d3  = "/sprites/enemy/enemy-death3.png";
    public static final String d4  = "/sprites/enemy/enemy-death4.png";

    public static final String a1  = "/sprites/enemy/enemy-attack1.png";
    public static final String a2  = "/sprites/enemy/enemy-attack2.png";

    private String[] running = {r1,r2,r3,r4};
    private String[] attacks = {a1,a2};
    private String[] death   = {d1,d2,d3,d4};

    private double pi = Math.PI;
    private double tau = Math.TAU;

    private Vector2d pos;    
    private Vector2d dpos = new Vector2d(0, 0);
    private double angle;

    private boolean attacking = false;
    private boolean dead = false;
    private int health = 2;

    Animator runAnim;
    Animator attackAnim;
    Animator deathAnim;
    Animator current;

    private int cooldownFrames = 4;
    private int cooldownTimer = 0;

    public Enemy() {
        pos  = new Vector2d(40,120);

        angle  = 0;
        dpos.x = Math.cos(angle);
        dpos.y = Math.sin(angle);

        runAnim = new Animator(running, 6);
        attackAnim = new Animator(attacks, 10);
        deathAnim = new Animator(death, 6);
        current = runAnim;
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
        if (dead) { return; }
        health--;
        if (health <= 0) {
            deathAnim.reset();
            current = deathAnim;
        }
    }

    public void attack() {
        if (!attacking && cooldownTimer==0) { 
            attacking = true;
            current = attackAnim;
            current.reset();
            cooldownTimer = cooldownFrames;
        }
    }

    public void update() {
        if (dead) { return; }
        current.update();
        if (!current.isFinished()) { return; }
        if (current==runAnim) { current.reset(); }
        if (current==attackAnim) { current = runAnim; }
        if (current==deathAnim) { dead = true; }
        if (cooldownTimer>0) { cooldownTimer--; }
        if (cooldownTimer==0) { attacking=false; }
    }

    public String getSprite() {
        return current.current();
    }

    public Vector2d getPos() {
        return pos;
    }

    public Vector2di getMapPos() {
        return new Vector2di((int)pos.y/20, (int)pos.x/20);
    }
}
