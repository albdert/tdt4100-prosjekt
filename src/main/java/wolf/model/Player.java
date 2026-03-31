package wolf.model;

import java.util.ArrayList;
import java.util.List;

import app.common.Vector2d;
import app.common.Vector2di;

public class Player {
    public Gun gun;

    private double pi = Math.PI;
    private double tau = Math.TAU;
    private Vector2d pos;    
    private Vector2d dpos = new Vector2d(0, 0);
    private Vector2d cplane = new Vector2d(0, 0);

    private double angle;

    public List<Vector2di> hits = new ArrayList<>();
    public double lwd = 0;
    public int lwt = 0;
    public int lws = 0;

    public Player() {
        gun = new Gun();
        pos  = new Vector2d(40,40);

        angle  = 0;
        dpos.x = Math.cos(angle);
        dpos.y = Math.sin(angle);
        cplane.x = Math.cos(angle + pi/2) * 0.66;
        cplane.y = Math.sin(angle + pi/2) * 0.66;
    }

    public void lookLeft() {
        angle -= 0.04;
        if (angle<0) { angle+=tau; }
        dpos.x = Math.cos(angle);
        dpos.y = Math.sin(angle);
        cplane.x = Math.cos(angle + pi/2) * 0.66;
        cplane.y = Math.sin(angle + pi/2) * 0.66;
    }

    public void lookRight() {
        angle += 0.04;
        if (angle>tau) { angle-=tau; }
        dpos.x = Math.cos(angle);
        dpos.y = Math.sin(angle);
        cplane.x = Math.cos(angle + pi/2) * 0.66;
        cplane.y = Math.sin(angle + pi/2) * 0.66;
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

    public void shoot() {
        gun.fire();
    }

    public Vector2d calcRay(int[][] world, double cameraX) {
        // ray start posisjon og retning
        Vector2di map = new Vector2di((int)pos.x/20, (int)pos.y/20);
        Vector2d start = new Vector2d(pos.x/20, pos.y/20);
        Vector2d dir = new Vector2d(Math.cos(angle)+cplane.x*cameraX, Math.sin(angle)+cplane.y*cameraX);

        // distanse beveget i hver retning, steg & delta steg
        Vector2d dist = new Vector2d(0,0);
        Vector2di step = new Vector2di(0, 0);
        Vector2d dstep = new Vector2d((dir.x==0)?1e30:Math.abs(1/dir.x), (dir.y==0)?1e30:Math.abs(1/dir.y));

        // truffet vegg, horisontal/vertikal vegg
        boolean hit = false;
        int side = 0;
        hits.clear();

        // finner retning for stegene, og første steg vector
        step.x = (dir.x<0)?-1:1;
        step.y = (dir.y<0)?-1:1;
        dist.x = (dir.x<0)?((start.x-map.x) * dstep.x):((map.x+1-start.x) * dstep.x);
        dist.y = (dir.y<0)?((start.y-map.y) * dstep.y):((map.y+1-start.y) * dstep.y);

        // DDA
        while (!hit) {
            // gå til neste posisjon (enten i x, eller y retning)
            if (dist.x<dist.y) {
                dist.x += dstep.x;
                map.x += step.x;
                side = 0;
            } else {
                dist.y += dstep.y;
                map.y += step.y;
                side = 1;
            }

            hits.add(new Vector2di(map.y,map.x));
            // sjekk om posisjon er vegg
            if (world[map.y][map.x]>0) { hit=true; }
        }

        lwd = (side==0)?(dist.x-dstep.x):(dist.y-dstep.y);
        lwt = world[map.y][map.x];
        lws = side;

        return new Vector2d(pos.x + dir.x * lwd * 20, pos.y + dir.y * lwd * 20);
    }

    public void distanceTo(Vector2d v) {
        
    }

    public void update() {
        gun.update();
    }

    public Gun getGun() {
        return gun;
    }
    public Vector2d getPos() {
        return new Vector2d(pos.x, pos.y);
    } 
    public Vector2d getDeltaPos() {
        return new Vector2d(dpos.x, dpos.y);
    }
}
