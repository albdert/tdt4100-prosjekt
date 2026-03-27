package wolf.model;

import app.common.Vector2d;
import app.common.Vector2di;

public class Player {
    private double pi = Math.PI;
    private double tau = 2*Math.PI;
    private Vector2d pos;    
    private Vector2d dpos = new Vector2d(0, 0);
    private Vector2d cplane = new Vector2d(0, 0);

    private double angle;

    public double lastWallDist = 0;
    public int lastWallType = 0;
    public int lastWallSide = 0;

    public Player() {
        pos  = new Vector2d(12,22);

        angle  = 0;
        dpos.x = Math.cos(angle)*2;
        dpos.y = Math.sin(angle)*2;
        cplane.x = Math.cos(angle + pi/2) * 0.66;
        cplane.y = Math.sin(angle + pi/2) * 0.66;
    }

    public void lookLeft() {
        angle -= 0.05;
        if (angle<0) { angle+=tau; }
        dpos.x = Math.cos(angle)*2;
        dpos.y = Math.sin(angle)*2;
        cplane.x = Math.cos(angle + pi/2) * 0.66;
        cplane.y = Math.sin(angle + pi/2) * 0.66;
    }

    public void lookRight() {
        angle += 0.05;
        if (angle>tau) { angle-=tau; }
        dpos.x = Math.cos(angle)*2;
        dpos.y = Math.sin(angle)*2;
        cplane.x = Math.cos(angle + pi/2) * 0.66;
        cplane.y = Math.sin(angle + pi/2) * 0.66;
    }

    public void moveForward() {
        pos.x+=dpos.x;
        pos.y+=dpos.y;
    }

    public void moveBackward() {
        pos.x-=dpos.x;
        pos.y-=dpos.y;
    }

    public void mouseDown(double x, double y) {

    }

    public void posChange(Vector2d v) {
        pos.x += v.x;
        pos.y += v.y;
    }

    public Vector2d calcRay2d(int[][] world) {

        // ray start posisjon og retning
        Vector2di map = new Vector2di((int)pos.x/20, (int)pos.y/20);
        Vector2d start = new Vector2d(pos.x/20, pos.y/20);
        Vector2d dir = new Vector2d(Math.cos(angle), Math.sin(angle));

        // distanse beveget i hver retning & delta steg
        Vector2d dist = new Vector2d(0,0);
        Vector2d dstep = new Vector2d((dir.x==0)?1e30:Math.abs(1/dir.x), (dir.y==0)?1e30:Math.abs(1/dir.y));

        // steg retning
        Vector2di step = new Vector2di(0, 0);

        // distanse til vegg
        double walldist = 0;

        // truffet vegg, horisontal/vertikal vegg
        boolean hit = false;
        int side = 0;

        // finner retning for stegene, og første steg vector
        if (dir.x<0) {
            step.x = -1;
            dist.x = (start.x-map.x) * dstep.x;
        } else {
            step.x = 1;
            dist.x = (map.x+1-start.x) * dstep.x;
        }
        if (dir.y<0) {
            step.y = -1;
            dist.y = (start.y-map.y) * dstep.y;
        } else {
            step.y = 1;
            dist.y = (map.y+1-start.y) * dstep.y;
        }

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

            // sjekk om posisjon er vegg
            if (world[map.y][map.x]>0) { hit=true; }
        }

        if (side==0) { walldist = (dist.x-dstep.x); }
        else         { walldist = (dist.y-dstep.y); }
        
        //Vector2d towall = dir.times(walldist).times(20);
        //Vector2d towall = new Vector2d(dir.x * walldist * 20, dir.y * walldist * 20);
        lastWallDist = walldist;
        lastWallType = world[map.y][map.x];
        lastWallSide = side;
        Vector2d endPos = new Vector2d(pos.x + dir.x * walldist * 20, pos.y + dir.y * walldist * 20);
        return endPos;
    }

    public Vector2d calcRay3d(int[][] world, double cameraX) {

        // ray start posisjon og retning
        Vector2di map = new Vector2di((int)pos.x/20, (int)pos.y/20);
        Vector2d start = new Vector2d(pos.x/20, pos.y/20);
        Vector2d pdir = new Vector2d(Math.cos(angle), Math.sin(angle));
        Vector2d dir = new Vector2d(pdir.x+cplane.x*cameraX, pdir.y+cplane.y*cameraX);

        // distanse beveget i hver retning & delta steg
        Vector2d dist = new Vector2d(0,0);
        Vector2d dstep = new Vector2d((dir.x==0)?1e30:Math.abs(1/dir.x), (dir.y==0)?1e30:Math.abs(1/dir.y));

        // steg retning
        Vector2di step = new Vector2di(0, 0);

        // distanse til vegg
        double walldist = 0;

        // truffet vegg, horisontal/vertikal vegg
        boolean hit = false;
        int side = 0;

        // finner retning for stegene, og første steg vector
        if (dir.x<0) {
            step.x = -1;
            dist.x = (start.x-map.x) * dstep.x;
        } else {
            step.x = 1;
            dist.x = (map.x+1-start.x) * dstep.x;
        }
        if (dir.y<0) {
            step.y = -1;
            dist.y = (start.y-map.y) * dstep.y;
        } else {
            step.y = 1;
            dist.y = (map.y+1-start.y) * dstep.y;
        }

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

            // sjekk om posisjon er vegg
            if (world[map.y][map.x]>0) { hit=true; }
        }

        if (side==0) { walldist = (dist.x-dstep.x); }
        else         { walldist = (dist.y-dstep.y); }
        
        lastWallDist = walldist;
        lastWallType = world[map.y][map.x];
        lastWallSide = side;
        Vector2d endPos = new Vector2d(pos.x + dir.x * walldist * 20, pos.y + dir.y * walldist * 20);
        return endPos;
    }


    public Vector2d getPos() {
        //return new Vector2di((int) pos.x, (int) pos.y);
        return new Vector2d(pos.x, pos.y);
    } 
    public Vector2d getDeltaPos() {
        return new Vector2d(dpos.x, dpos.y);
    }
    public double getAngle() {
        return angle;
    }
}
