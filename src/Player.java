import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class Player {
    public String name;
    public Vector2 location;
    public Vector2 velocity;
    public Color playerColor;
    //public ArrayList<Vector2> path = new ArrayList<Vector2>();
    public Stack<Vector2> path = new Stack<Vector2>();
    private int moves = 0;

    public Player(String name, Vector2 spawnLocation, Color c) {
        this.name = name;
        playerColor = c;
        location = spawnLocation;
        velocity = new Vector2(0, 0);
        path.push(location);
    }

    public int getMoves() {
        return moves;
    }

    public boolean Move(Map m, Vector2 acceleration) throws Exception {
        if (Math.abs(acceleration.x) > 1 || Math.abs(acceleration.y) > 1) {
            return false;
        }

        Vector2 newVelocity = Vector2.add(velocity, acceleration);
        Vector2 to = Vector2.add(location, newVelocity);

        if (m.validMove(location, to)) { //valid move
            location = to;
            path.push(location);
            velocity = newVelocity;
            return true;
        }
        return false; //invalid move
    }

    public boolean undoLastMove(Map m) {
        if(path.size()==1) //Already at spawn, cant reset
            return false;
        else if(path.size()==2) { //Reset to spawn
            path.pop();
            velocity = Vector2.zero();
            location = path.peek();
            return true;
        }

        //Remove Last position
        path.pop();

        //Calculate the Velocity
        Vector2 a = path.get(path.size()-2);
        Vector2 b = path.get(path.size()-1);

        //Set new velocity and location
        velocity = Vector2.substract(b,a);
        location = path.peek();

        return true;
    }
}
