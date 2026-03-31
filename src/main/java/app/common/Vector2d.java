package app.common;

public class Vector2d { 
    public double x, y; 
    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Vector2d norm() {
        double len = Math.sqrt((x*x)+(y*y));
        return new Vector2d(x/len, y/len);
    }
    public Vector2d times(double n) {
        return new Vector2d(x*n, y*n);
    }
    public Vector2d div(double n) {
        return new Vector2d(x/n, y/n);
    }
    public static Vector2d add(Vector2d v1, Vector2d v2) {
        return new Vector2d(v1.x+v2.x, v1.y+v2.y);
    }
    public static Vector2d sub(Vector2d v1, Vector2d v2) {
        return new Vector2d(v1.x-v2.x, v1.y-v2.y);
    }
}

