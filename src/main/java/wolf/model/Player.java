package wolf.model;

import java.util.Vector;

import app.common.Vector2d;
import app.common.Vector2di;

public class Player {
    private double pi = Math.PI;
    private double tau = 2*Math.PI;
    private Vector2d pos = new Vector2d(0, 0);
    private Vector2d dpos = new Vector2d(0, 0);
    private double angle;

    public Player() {
        pos.x = 22;
        pos.y = 12;

        angle  = 0;
        dpos.x = Math.cos(angle)*2;
        dpos.y = Math.sin(angle)*2;
    }

    public void lookLeft() {
        angle -= 0.1;
        if (angle<0) { angle+=tau; }
        dpos.x = Math.cos(angle)*2;
        dpos.y = Math.sin(angle)*2;
    }

    public void lookRight() {
        angle += 0.1;
        if (angle>tau) { angle-=tau; }
        dpos.x = Math.cos(angle)*2;
        dpos.y = Math.sin(angle)*2;
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
    
    public Vector2d calcRay(int[][] world, Vector2d mouse) {
        Vector2d rpos = pos;
        Vector2d rangle = Vector2d.sub(mouse, pos).norm();    
        Vector2d runit = new Vector2d(Math.sqrt(1 + Math.pow(rangle.y/rangle.x,2)), Math.sqrt(1 + Math.pow(rangle.x/rangle.y,2)));

        Vector2di mapcheck = new Vector2di((int)rpos.x, (int)rpos.y);
        Vector2d rlen = new Vector2d(0,0);
        Vector2di step = new Vector2di(0,0);

        if (rangle.x<0) {
            step.x=-1;
            rlen.x = (rpos.x - (double)mapcheck.x) * step.x;
        } else {
            step.x=1;
            rlen.x = ((double)mapcheck.x+1 - rpos.x) * step.x;
        }
        if (rangle.y<0) {
            step.y=-1;
            rlen.y = (rpos.y - (double)mapcheck.y) * step.y;
        } else {
            step.y=1;
            rlen.y = ((double)mapcheck.y+1 - rpos.y) * step.y;
        }

        boolean tileFound = false;
        double maxdist = 100;
        double dist = 0;
        while (!tileFound && (dist<maxdist)) {
            if (rlen.x<rlen.y) {
                mapcheck.x += step.x;
                dist = rlen.x;
                rlen.x += runit.x;
            } else {
                mapcheck.y += step.y;
                dist = rlen.y;
                rlen.y += runit.y;
            }

            System.out.println("Test1");
            System.out.println(mapcheck.x+" "+mapcheck.y);
            if ((mapcheck.x>=0 && mapcheck.x<world.length) && (mapcheck.y>=0 && mapcheck.y<world[0].length)) {
                System.out.println("Test2");
                if (world[mapcheck.x][mapcheck.y]==1) {
                    tileFound = true;
                }
            }
        }

        Vector2d intersection = new Vector2d(0,0);
        if (tileFound) {
            intersection = Vector2d.add(rpos,rangle.times(dist));
        }
        return intersection;
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
