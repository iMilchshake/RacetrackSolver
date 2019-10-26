import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseListener extends MouseAdapter {

    MyPanel panel;

    public MyMouseListener(MyPanel panel) {
        this.panel = panel;
    }

    public void mouseReleased(MouseEvent e) {
        try {
            mousePressedOrDragged(e);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            if (RaceGame.mode == 1) { //GAME INPUT
                Map myMap = panel.myGrid.map;
                Vector2 coords = panel.myGrid.CoordsToCell(new Vector2(e.getX(), e.getY()));
                Vector2 estimated = Vector2.add(RaceGame.currentPlayer.location, RaceGame.currentPlayer.velocity);
                boolean moved = RaceGame.currentPlayer.Move(myMap, Vector2.substract(coords, estimated));
                if (!moved)
                    System.err.println("invalid move");
                UI.panel.repaint();
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void mouseDragged(MouseEvent e) {
        try {
            mousePressedOrDragged(e);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void mousePressedOrDragged(MouseEvent e) throws Exception {
        if (RaceGame.mode == 2) { //editor
            UI.MouseClicked(e.getX(), e.getY());
            Vector2 coords = panel.myGrid.CoordsToCell(new Vector2(e.getX(), e.getY()));
            if (SwingUtilities.isLeftMouseButton(e))
                panel.myGrid.map.setCell(coords.x, coords.y, panel.myGrid.map.editorDrawMode);
        }
    }
}
