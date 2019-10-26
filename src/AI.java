import java.awt.*;
import java.util.Stack;

public class AI extends  Player {

    public int shortestPath = 999;

    public AI(String name, Vector2 spawnLocation, Color c) {
        super(name, spawnLocation, c);
    }

    public boolean OLDfindShortestPathToPoint(Vector2 from, Vector2 to, Map m) throws Exception {
        for(int i=0; i<10; i++) {
            undoLastMove(m);
        }

        for(int i=0; i<10; i++) {
            if(location.equals(to))
                return true;

            Move(m,Vector2.rndDir());
        }
        return false;
    }

    public boolean findShortestPathToPoint(Vector2 from, Vector2 to, Map m, int d) throws Exception {

        if(d>3 || d>shortestPath) {
            System.out.println("Aborting"+d+"-"+shortestPath);
            return true;
        }

        if(location.equals(to)) {
            shortestPath=d;
            System.out.println(path);
            System.out.println(shortestPath);
        }

        for(int x=-1; x<=1; x++) {
            for(int y=-1; y<=1; y++) {
                boolean val = Move(m,new Vector2(x,y));
                if(val) {
                    System.out.println(d);
                    findShortestPathToPoint(from, to, m, d++);
                    undoLastMove(m);

                    if(d>shortestPath)
                        return true;
                }
            }
        }

        return true;

    }
}
