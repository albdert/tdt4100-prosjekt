package app.common;

public class Vector2di { 
    public int x, y; 
    public Vector2di(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Vector2di v) {
        if (x==v.x && y==v.y) {
            return true;
        }   return false;
    }
}
