import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouseListener extends MouseAdapter {

    MyPanel panel;
    public boolean mbl = false;
    public boolean mbr = false;

    public MyMouseListener(MyPanel panel) {
        this.panel = panel;
    }

    public void mousePressed(MouseEvent e){
        UI.MouseClicked(e.getX(),e.getY());
        Vector2 coords = panel.myGrid.CoordsToCell(new Vector2(e.getX(),e.getY()));

        if(e.getButton()==1) {
            panel.myGrid.map.setCell(coords.x, coords.y, 1);

        } else {
            panel.myGrid.map.setCell(coords.x, coords.y, 0);
            System.out.println(panel.myGrid.map.exportMap());
        }

        System.out.println(e.getX()+"-"+e.getY()+"-"+coords.x+"-"+coords.y+" -> "+e.getButton());
    }

    public void mouseDragged(MouseEvent e){

        UI.MouseClicked(e.getX(),e.getY());
        Vector2 coords = panel.myGrid.CoordsToCell(new Vector2(e.getX(),e.getY()));

        if(SwingUtilities.isLeftMouseButton(e)) {
            panel.myGrid.map.setCell(coords.x, coords.y, 1);

        } else {
            panel.myGrid.map.setCell(coords.x, coords.y, 0);
        }

        System.out.println(e.getX()+"-"+e.getY()+"-"+coords.x+"-"+coords.y+" -> "+e.getButton());
    }
}
