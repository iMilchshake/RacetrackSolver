import com.sun.deploy.util.ArrayUtil;

import java.awt.*;
import java.util.PriorityQueue;
import java.util.Vector;

public class AI extends Player {

    public PriorityQueue currentNodes;

    public AI(String name, Vector2 spawnLocation, Color c) {
        super(name, spawnLocation, c);
        currentNodes = new PriorityQueue();
    }

    public static int cost(Vector2 from, Vector2 to) { //x diff + y diff
        Vector2 diff = Vector2.substract(from, to);
        return Math.abs(diff.x) + Math.abs(diff.y);
    }


    public boolean findPath(Vector2 to, Map m) throws Exception {
        return findPath(to,m,0);
    }

    public boolean findPath(Vector2 to, Map m, int d) throws Exception {

        if(d>20)
            return false;

        if(location.equals(to)) {
            System.err.println("FOUND GOAL");
            return true;
        }

        PriorityQueue<Vector2> nextNodes = new PriorityQueue<Vector2>(new VectorComp(to));
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                nextNodes.add(Vector2.add(location,new Vector2(x,y)));
            }
        }

        //MenuBuilder.ConsoleInput("enter pls");

        while(!nextNodes.isEmpty()) //there are possible steps left
        {
            Vector2 tmp = nextNodes.poll(); //next goal
            Vector2 neededVel = Vector2.substract(tmp,location);

            //System.out.println(tmp + " - " + AI.cost(tmp,to));

            boolean moved = Move(m,neededVel); //do the step


            //System.out.println(neededVel+ " - " + d + " - " + nextNodes.size());

            if(moved) {
                //UI.panel.repaint();
                if(findPath(to, m, d + 1))
                    return true;
                undoLastMove(m);
            }
        }




        return false;
    }

    public boolean findShortestPathToPointRandomly(Vector2 from, Vector2 to, Map m) throws Exception {
        for (int i = 0; i < 10; i++) {
            undoLastMove(m);
        }

        for (int i = 0; i < 10; i++) {
            if (location.equals(to))
                return true;

            Move(m, Vector2.rndDir());
        }
        return false;
    }

    /*public boolean findShortestPathToPoint(Vector2 from, Vector2 to, Map m, int d) throws Exception {

        if (d > 3 || d > shortestPath) {
            System.out.println("Aborting" + d + "-" + shortestPath);
            return true;
        }

        if (location.equals(to)) {
            shortestPath = d;
            System.out.println(path);
            System.out.println(shortestPath);
        }

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                boolean val = Move(m, new Vector2(x, y));
                if (val) {
                    System.out.println(d);
                    findShortestPathToPoint(from, to, m, d++);
                    undoLastMove(m);

                    if (d > shortestPath)
                        return true;
                }
            }
        }
        return true;
    }*/
}
