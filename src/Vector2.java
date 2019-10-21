public class Vector2 {
    public int x,y;
    public Vector2(int x, int y){
        this.x = x;
        this.y = y;
    }

    public static Vector2 add(Vector2 a, Vector2 b) {
        return new Vector2(a.x+b.x,a.y+b.y);
    }

    public static Vector2 substract(Vector2 a, Vector2 b) {
        return new Vector2(a.x-b.x,a.y-b.y);
    }

    @Override
    public String toString() {
        return "["+x+","+y+"]";
    }
}
