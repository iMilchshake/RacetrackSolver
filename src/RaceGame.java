import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;


public class RaceGame {
    public static ArrayList<Player> players = new ArrayList<Player>();
    public static Player currentPlayer;
    public static int mode; //1=Game, 2=Editor

    public RaceGame(Map currentMap) {

    }

    public static void main(String[] args) throws Exception {
        String[] subMenuEntries = {"[1] Race", "[2] Map Editor", "[3] AI-Test" , "[4] Testing","[5] Exit"};
        System.out.print(MenuBuilder.BuildMenu("Racetrack", subMenuEntries, "fonts/standard.flf", 2, 1, 1, 0, 0));
        Integer selection = Integer.parseInt(MenuBuilder.ConsoleInput("Selection: "));

        if (selection == 1) {
            mode = 1;
            Map myMap = new Map("testMap", 25, 25);
            UI.createAndShowGUI(myMap);
            String mapString = MenuBuilder.ConsoleInput("Enter Map:");
            myMap.importMap(mapString);

            Color c = new Color(5, 114, 255);
            AI tmpPlayer = new AI("testPlayer", myMap.getSpawns().get(0), c);
            players.add(tmpPlayer);
            currentPlayer = tmpPlayer;
            UI.panel.repaint();

        } else if (selection == 2) {
            mode = 2;
            Map myMap = new Map("testMap", 25, 25);
            UI.createAndShowGUI(myMap);
        } else if (selection == 3) { //AI
            mode = 1;
            Map myMap = new Map("testMap", 25, 25);
            UI.createAndShowGUI(myMap);
            String mapString = MenuBuilder.ConsoleInput("Enter Map:");
            myMap.importMap(mapString);

            Color c = new Color(5, 114, 255);
            AI tmpPlayer = new AI("aiPlayer", myMap.getSpawns().get(0), c);
            players.add(tmpPlayer);
            currentPlayer = tmpPlayer;

            boolean success = tmpPlayer.findPath(myMap.getChecks().get(0),myMap);
            System.out.println("found goal: "+success);
            UI.panel.repaint();

            System.out.println("DONE");
        }
        else if (selection == 4) {
            Testing.testCostFunction();
            Testing.testCostFunctionSorting();
        }
        else {
            System.exit(2);
        }
    }
}
