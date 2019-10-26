import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyListener extends KeyAdapter {

    MyPanel panel;

    public MyKeyListener(MyPanel panel) {
        this.panel = panel;
    }

    public void keyReleased(KeyEvent e) {

        int numb;
        try {
            numb = Integer.parseInt(""+e.getKeyChar());
        } catch (Exception ex) {
            numb=-1; //not a number
        }

        if(e.getKeyChar()=='i') { //Import
            String map = MenuBuilder.ConsoleInput("Enter Map: ");
            panel.myGrid.map.importMap(map);
            panel.repaint();
        } else if (e.getKeyChar()=='e') { //Export
            System.out.println(panel.myGrid.map.exportMap());
            System.out.println(panel.myGrid.map.getSpawns());
            System.out.println(panel.myGrid.map.getSpawns().size());
            System.out.println(panel.myGrid.map.getChecks());
            System.out.println(panel.myGrid.map.getChecks().size());
        } else if (e.getKeyChar()=='r') {
            RaceGame.currentPlayer.undoLastMove(panel.myGrid.map);
            panel.repaint();
        } else if (numb!=-1) { //number!
            panel.myGrid.map.editorDrawMode = numb;
        }
    }

}
