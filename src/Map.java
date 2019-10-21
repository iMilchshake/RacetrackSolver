import java.lang.reflect.Array;
import java.util.ArrayList;

public class Map {

    public String name;
    public int width, height;
    private int[][] grid; //1=Wall, 0=Empty Cell, 2=Spawn, 3=Checkpoint
    public int editorDrawMode = 1; //wall default/

    public Map(String name, int width, int height) throws Exception {
        this.name = name;
        if(width<1 || height<0)
            throw new Exception("width and height must be positive");
        this.width = width;
        this.height = height;
        grid = new int[width][height];
    }

    public void importArrayAsMap(int[][] arraymap, int wall, int empty) {
        for(int x = 0; x < arraymap.length; x++) {
            for(int y = 0; y < arraymap[x].length; y++) {
                if(arraymap[x][y]==wall)
                    setCell(x,y,1);
                if(arraymap[x][y]==empty)
                    setCell(x,y,0);
            }
        }
    }

    public void setCell(int x, int y, int state) {
        try {
            grid[x][y] = state;
        } catch (Exception e) {
            System.err.println("Invalid Cell was accessed. x="+x+", y="+y+" Map: "+this);
            //e.printStackTrace();
        }
    }
    public int getCell(int x, int y) {
        try {
            return grid[x][y];
        } catch (Exception ex) {
            return 1; //if a Cell outside the Grid was accessed, return default value
        }
    }

    public boolean validMove(Vector2 from, Vector2 to) {
        int pdx, pdy, es, el, err;
        int dx = Math.abs(to.x - from.x);
        int dy = Math.abs(to.y - from.y);
        int incx = sign(to.x-from.x);
        int incy = sign(to.y-from.y);

        if (dx > dy){
            pdx = incx;
            pdy = 0;
            es = dy;
            el = dx;
        } else {
            pdx = 0;
            pdy = incy;
            es = dx;
            el = dy;
        }

        int x = from.x;
        int y = from.y;
        err = el / 2;

        //Iterate through the line of Cells (x,y)
        for (int t = 0; t < el; t++){
            err -= es;
            if (err < 0) {
                err += el;
                x += incx;
                y += incy;
            } else {
                x += pdx;
                y += pdy;
            }

            //Test if any Cell on the Line is a Wall
            if(getCell(x,y)==1) {
                return false;
            }
        }
        return true;
    }


    public int sign(int x) {
        return (x > 0) ? 1 : (x < 0) ? -1 : 0;
    }

    public String exportMap() {
        String output = "";
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                output+=getCell(x,y);
            }
        }
        return output;
    }

    public void importMap(String mapString) {
        int i = 0;
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                setCell(x,y,Integer.parseInt(""+mapString.charAt(i++)));
            }
        }
    }

    public ArrayList<Vector2> getSpawns() {
        ArrayList<Vector2> spawns = new ArrayList<Vector2>();
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                if(getCell(x,y)==2) { //Found a Spawn
                    spawns.add(new Vector2(x,y));
                }
            }
        }
        return spawns;
    }

    public String toString() {
        return "["+name+" - ("+width+","+height+")]";
    }
}
