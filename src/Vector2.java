import java.util.Random;

public class Vector2 {
    public int x, y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2 add(Vector2 a, Vector2 b) {
        return new Vector2(a.x + b.x, a.y + b.y);
    }

    public static Vector2 substract(Vector2 a, Vector2 b) {
        return new Vector2(a.x - b.x, a.y - b.y);
    }

    public static Vector2 zero() {
        return new Vector2(0, 0);
    }

    public static Vector2 rndDir() {
        Random rnd = new Random();
        return new Vector2(rnd.nextInt(3)-1,rnd.nextInt(3)-1);
    }

    public boolean equals(Vector2 v) {
       return (x==v.x && y==v.y);
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }
}
