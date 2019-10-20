//import com.sun.prism.GraphicsPipeline;
//import sun.java2d.loops.DrawLine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class UI {

    public static MyPanel panel;

    public static void createAndShowGUI(Map map) {
        System.out.println("Created GUI on EDT? " +
                SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Custom Title");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new MyPanel(map);
        f.add(panel);
        f.pack();

        f.setSize(500, 500);
        f.setVisible(true);

    }

    public static void MouseClicked(int x, int y) {
        panel.repaint();
    }
}

class MyPanel extends JPanel {

    int width, height;
    Grid myGrid;

    public MyPanel(Map map) {
        setFocusable(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
        width = this.getSize().width;
        height = this.getSize().height;
        myGrid = new Grid(map);

        addKeyListener(new MyKeyListener(this));
        MyMouseListener mListener = new MyMouseListener(this);
        addMouseListener(mListener);
        addMouseMotionListener(mListener);
    }

    public Dimension getPreferredSize() {
        return new Dimension(250, 250);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        myGrid.printGrid(g, getSize().width, getSize().height);
        myGrid.drawPlayers(g,RaceGame.players);
    }
}

class Grid {

    public int CellCountX, CellCountY, actualCellSize, gridWidth, gridHeight;
    Map map;

    public Grid(Map map) {
        this.CellCountX = map.width;
        this.CellCountY = map.height;
        this.map = map;
    }

    public void printGrid(Graphics g, int width, int height) {

        int maxCellSizeX = (width - CellCountX - 1) / CellCountX;
        int maxCellSizeY = (height - CellCountY - 1) / CellCountY;
        actualCellSize = Math.min(maxCellSizeX, maxCellSizeY);
        gridWidth = CellCountX * actualCellSize + (CellCountX); //Cells + Borders
        gridHeight = CellCountY * actualCellSize + (CellCountY);

        //Draw Grid (x bars)
        for (int x = 0; x < CellCountX + 1; x++) {
            g.drawLine(x * (actualCellSize + 1), 0, x * (actualCellSize + 1), gridHeight);
        }

        //Draw Grid (ybars)
        for (int y = 0; y < CellCountY + 1; y++) {
            g.drawLine(0, y * (actualCellSize + 1), gridWidth, y * (actualCellSize + 1));
        }

        //Draw Walls
        for(int x = 0; x < CellCountX; x++) {
            for(int y = 0; y < CellCountY; y++) {
                if(map.getCell(x,y)==1) {
                    g.setColor(Color.BLACK);
                    FillCell(g, x, y, actualCellSize);
                } else if (map.getCell(x,y)==2) {
                    g.setColor(Color.YELLOW);
                    FillCell(g, x, y, actualCellSize);
                } else if (map.getCell(x,y)==3) {
                    g.setColor(Color.GREEN);
                    FillCell(g, x, y, actualCellSize);
                }
            }
        }
    }

    public void drawPlayers(Graphics g, ArrayList<Player> playerList) {
        for(Player p: playerList) {
            System.out.println("printed player at"+p.location);
            g.drawOval(getCellMid(p.location.x,p.location.y,actualCellSize).x,getCellMid(p.location.x,p.location.y,actualCellSize).y,actualCellSize/2,actualCellSize/2);
        }
    }

    public void DrawCellLine(Graphics g, int x1, int y1, int x2, int y2,int cellSize) {
        int pdx, pdy, es, el, err;
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int incx = sign(x2-x1);
        int incy = sign(y2-y1);

        if (dx > dy){
            pdx = incx;
            pdy = 0;
            es = dy;
            el = dx;
        } else {
            pdx = 0;
            pdy = incy;
            es = dx;
            el = dy;
        }

        int x = x1;
        int y = y1;
        err = el / 2;
        //FillCell(g,x,y,cellSize);
        for (int t = 0; t < el; t++){
            err -= es;
            if (err < 0) {
                err += el;
                x += incx;
                y += incy;
            } else {
                x += pdx;
                y += pdy;
            }
            FillCell(g,x,y,cellSize);
        }
    }

    public int sign(int x) {
        return (x > 0) ? 1 : (x < 0) ? -1 : 0;
    }

    public void FillCell(Graphics g, int cellX, int cellY, int cellSize) {
        g.fillRect((cellSize + 1) * cellX + 1, (cellSize + 1) * cellY + 1, cellSize, cellSize);
    }

    public Vector2 getCellMid(int cellX, int cellY, int cellSize) {
        return new Vector2((cellSize + 1) * cellX + 1 + (cellSize/2),(cellSize + 1) * cellY + 1 + (cellSize/2));
    }

    public Vector2 CoordsToCell(Vector2 windowCoords) {
        //CellX = floor(WindowCoordsX-1)/(CellSize+1)
        double CellX = Math.floor(((double)windowCoords.x-1)/((double)actualCellSize+1));
        double CellY = Math.floor(((double)windowCoords.y-1)/((double)actualCellSize+1));
        return new Vector2((int)CellX,(int)CellY);
    }
}
