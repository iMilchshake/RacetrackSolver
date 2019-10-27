import java.awt.*;
import java.util.Comparator;
import java.util.Vector;

public class Testing {

    public static void testCostFunction() {
        Vector2 a = new Vector2(2,3);
        Vector2 b = new Vector2(6,2);
        System.out.println("Cost from "+a+" to "+b+" is: "+AI.cost(a,b));

        a = new Vector2(0,0);
        b = new Vector2(0,0);
        System.out.println("Cost from "+a+" to "+b+" is: "+AI.cost(a,b));

        a = new Vector2(0,0);
        b = new Vector2(-3,-5);
        System.out.println("Cost from "+a+" to "+b+" is: "+AI.cost(a,b));
    }

    public static void testCostFunctionSorting() throws Exception {

        Vector2 a = new Vector2(2,3);
        Vector2 b = new Vector2(6,2);
        Vector2 or = Vector2.zero();

        System.out.println("Cost from "+or+" to "+a+" is: "+AI.cost(or,a));
        System.out.println("Cost from "+or+" to "+b+" is: "+AI.cost(or,b));

        Comparator c = new VectorComp(Vector2.zero());
        System.out.println(c.compare(a,b));

        AI ai = new AI("ai", Vector2.zero(), Color.BLACK);
        Map myMap = new Map("tmpMap",20,20);
        ai.findPath(a,myMap);
    }
}
