import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouseListener extends MouseAdapter {

    MyPanel panel;

    public MyMouseListener(MyPanel panel) {
        this.panel = panel;
    }

    public void mousePressed(MouseEvent e){
        mousePressedOrDragged(e);
    }

    public void mouseDragged(MouseEvent e){
        mousePressedOrDragged(e);
    }

    public void mousePressedOrDragged(MouseEvent e) {
        UI.MouseClicked(e.getX(),e.getY());
        Vector2 coords = panel.myGrid.CoordsToCell(new Vector2(e.getX(),e.getY()));
        if(SwingUtilities.isLeftMouseButton(e))
            panel.myGrid.map.setCell(coords.x, coords.y, panel.myGrid.map.editorDrawMode);
    }
}
