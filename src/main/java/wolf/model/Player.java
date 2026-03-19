package wolf.model;

import app.common.Vector2d;
import app.common.Vector2di;

public class Player {
    private Vector2d pos;
    private Vector2d dir;
    private Vector2d plane;

    public Player() {
        pos.x = 22;
        pos.y = 12;
        dir.x = -1;
        dir.y = 0;
        plane.x = 0;
        plane.y = 0.66;
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
