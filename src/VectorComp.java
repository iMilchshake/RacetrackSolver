import java.util.Comparator;
import java.util.Vector;

public class VectorComp implements Comparator<Vector2> {

    public Vector2 goal;

    public VectorComp(Vector2 origin) {
        this.goal = origin;
    }

    @Override
    public int compare(Vector2 a, Vector2 b) {
        int costA = AI.cost(a,goal);
        int costB = AI.cost(b,goal);
        if(costA>costB) {
            return 1;
        }
        else if(costA<costB) {
            return -1;
        }
        else {
            return 0;
        }
    }

}
