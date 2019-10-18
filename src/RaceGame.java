import java.awt.*;
import java.util.ArrayList;

public class RaceGame {
    public Map currentMap;
    public ArrayList<Player> players = new ArrayList<Player>();


    public static void main(String[] args) throws Exception {
        Map myMap = new Map("myMap",10,9);
        int[][] tmp =  {{1,1,1,1,1,1,1,1,1},
                        {1,0,0,0,0,0,0,0,1},
                        {1,0,0,0,0,0,0,0,1},
                        {1,0,0,1,1,1,0,0,1},
                        {1,0,0,0,0,1,1,0,1},
                        {1,0,0,0,0,0,0,0,1},
                        {1,0,0,0,0,0,0,0,1},
                        {1,0,0,0,0,0,0,0,1},
                        {1,0,0,0,0,0,0,0,1},
                        {1,1,1,1,1,1,1,1,1}};
        myMap.importArrayAsMap(tmp,1,0);
        System.out.println(myMap.getCell(0,0));

        UI.createAndShowGUI(myMap);
    }

    public RaceGame(Map currentMap) {
        this.currentMap = currentMap;
    }


}
