import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class MyKeyListener extends KeyAdapter {

    MyPanel panel;

    public MyKeyListener(MyPanel panel) {
        this.panel = panel;
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar()=='i') {
            String map = MenuBuilder.ConsoleInput("Enter Map: ");
            panel.myGrid.map.importMap(map);
            panel.repaint();
        } else if (e.getKeyChar()=='e') {
            System.out.println(panel.myGrid.map.exportMap());
        }
    }

}
