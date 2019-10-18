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
            System.out.println("Enter Map");
            Scanner sc = new Scanner(System.in);
            String map = sc.next();
            sc.close();
            panel.myGrid.map.importMap(map);
            panel.repaint();
        }
    }

}
