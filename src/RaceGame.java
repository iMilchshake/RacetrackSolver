import java.awt.*;
import java.io.Console;
import java.util.ArrayList;

public class RaceGame {
    public Map currentMap;
    public static ArrayList<Player> players = new ArrayList<Player>();

    public static void main(String[] args) throws Exception {
        String[] subMenuEntries = { "[1] Race", "[2] Map Editor", "[3] Exit" };
        System.out.print(MenuBuilder.BuildMenu("Racetrack", subMenuEntries, "fonts/standard.flf", 2, 1, 1, 0, 0));
        Integer selection = Integer.parseInt(MenuBuilder.ConsoleInput("Selection: "));

        if(selection==1) {
            Map myMap = new Map("testMap",25,25);
            UI.createAndShowGUI(myMap);
            String map = MenuBuilder.ConsoleInput("Enter Map:");
            myMap.importMap(map);
            UI.panel.repaint();
            Player tmpPlayer = new Player("testPlayer",myMap.getSpawns().get(0));
        } else if (selection == 2) {
            Map myMap = new Map("testMap",25,25);
            UI.createAndShowGUI(myMap);
        }
        else {
            System.exit(2);
        }



    }

    public RaceGame(Map currentMap) {
        this.currentMap = currentMap;
    }
}
