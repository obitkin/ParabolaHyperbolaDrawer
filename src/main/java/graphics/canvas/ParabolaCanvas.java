package graphics.canvas;

import graphics.graph.ParabolaDrawer;

import java.awt.*;

public class ParabolaCanvas extends Canvas {

    public ParabolaDrawer parabolaDrawer;

    public ParabolaCanvas(int width, int height) {
        setBackground(Color.WHITE);
        setSize(width, height);
        parabolaDrawer = new ParabolaDrawer(width, height);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2;
        g2 = (Graphics2D) g;
        System.out.println("Рисуем параболу");
        g2.draw3DRect(100, 100, 200, 200, true);

    }
}
