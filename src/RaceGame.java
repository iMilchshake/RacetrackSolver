import java.awt.*;
import java.io.Console;
import java.util.ArrayList;

public class RaceGame {
    public static ArrayList<Player> players = new ArrayList<Player>();
    public static Player currentPlayer;
    public static int mode; //1=Game, 2=Editor

    public static void main(String[] args) throws Exception {
        String[] subMenuEntries = { "[1] Race", "[2] Map Editor", "[3] Exit" };
        System.out.print(MenuBuilder.BuildMenu("Racetrack", subMenuEntries, "fonts/standard.flf", 2, 1, 1, 0, 0));
        Integer selection = Integer.parseInt(MenuBuilder.ConsoleInput("Selection: "));

        if(selection==1) {
            mode=1;
            Map myMap = new Map("testMap",25,25);
            UI.createAndShowGUI(myMap);
            String map = MenuBuilder.ConsoleInput("Enter Map:");
            myMap.importMap(map);

            Color c = new Color(5, 114, 255);
            Player tmpPlayer = new Player("testPlayer",myMap.getSpawns().get(0),c);
            players.add(tmpPlayer);
            currentPlayer = tmpPlayer;
            UI.panel.repaint();

        } else if (selection == 2) {
            mode=2;
            Map myMap = new Map("testMap",25,25);
            UI.createAndShowGUI(myMap);
        }
        else {
            System.exit(2);
        }
    }

    public RaceGame(Map currentMap) {

    }
}
