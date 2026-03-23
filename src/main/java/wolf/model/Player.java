package wolf.model;

import app.common.Vector2d;
import app.common.Vector2di;

public class Player {
    private Vector2d pos = new Vector2d(0, 0);
    private Vector2d dir = new Vector2d(0, 0);
    private Vector2d plane = new Vector2d(0, 0);

    public Player() {
        pos.x = 22;
        pos.y = 12;
        dir.x = -1;
        dir.y = 0;
        plane.x = 0;
        plane.y = 0.66;
    }

    public void posChange(Vector2d v) {
        pos.x += v.x;
        pos.y += v.y;
    }

    public Vector2di getPos() {
        return new Vector2di((int) pos.x, (int) pos.y);
    } 
    public Vector2d getDir() {
        return new Vector2d(dir.x, dir.y);
    }
    public Vector2d getPlane() {
        return new Vector2d(plane.x, plane.y);
    }
}
