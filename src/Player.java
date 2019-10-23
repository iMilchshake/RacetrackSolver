import java.awt.*;
import java.util.ArrayList;

public class Player {
    public String name;
    public Vector2 location;
    public Vector2 velocity;
    public Color playerColor;
    public ArrayList<Vector2> path = new ArrayList<Vector2>();
    private int moves = 0;

    public Player(String name, Vector2 spawnLocation, Color c) {
        this.name = name;
        playerColor = c;
        location = spawnLocation;
        velocity = new Vector2(0, 0);
        addCurrentLocationToPath();
    }

    public int getMoves() {
        return moves;
    }

    public void addCurrentLocationToPath() {
        path.add(new Vector2(location.x, location.y));
    }

    public boolean Move(Map m, Vector2 acceleration) throws Exception {
        if (Math.abs(acceleration.x) > 1 || Math.abs(acceleration.y) > 1) {
            return false;
        }

        Vector2 newVelocity = Vector2.add(velocity, acceleration);
        Vector2 to = Vector2.add(location, newVelocity);
        if (m.validMove(location, to)) { //valid move
            location.x = to.x;
            location.y = to.y;
            System.out.println(moves++);
            addCurrentLocationToPath();
            velocity = newVelocity;
            return true;
        }
        return false; //invalid move
    }

    public boolean undoLastMove(Map m) {
        if(path.size()==1) //Already at spawn, cant reset
            return false;
        else if(path.size()==2) { //Reset to spawn
            path.remove(path.size()-1); //Remove Last position
            velocity = Vector2.zero();
            location = path.get(0);
        }

        //Remove Last position
        path.remove(path.size()-1);

        //Calculate the Velocity
        Vector2 a = path.get(path.size()-2);
        Vector2 b = path.get(path.size()-1);

        //Set new velocity and location
        velocity = Vector2.substract(b,a);
        location = path.get(path.size()-1);

        return true;
    }
}
