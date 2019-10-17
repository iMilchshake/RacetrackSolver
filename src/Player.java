public class Player {
    public String name;
    public Vector2 location;
    public Vector2 speed = new Vector2(0,0);
    private int moves = 0;

    public Player(String name, Vector2 spawnLocation) {
        this.name = name;
        location = spawnLocation;
    }

    public int getMoves() {
        return moves;
    }

    public boolean Move(Map m, Vector2 change) throws Exception {
        if(Math.abs(change.x)>1 || Math.abs(change.y)>1)
            throw new Exception("Change Vector is too long!");

        Vector2 to = Vector2.add(location,change);
        if(m.validMove(location,to)) { //valid move
            location.x = to.x;
            location.y = to.y;
            moves++;
            return true;
        }
        return false; //invalid move
    }
}
