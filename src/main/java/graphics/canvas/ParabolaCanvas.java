package graphics.canvas;

import graphics.graph.ParabolaDrawer;
import graphics.dto.Point;

import java.awt.*;
import java.util.List;

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
        List<Point> dots = parabolaDrawer.drawParabola();
        for (int i = 0; i < dots.size() - 1; i++) {
            drawLine(g2, dots.get(i), dots.get(i + 1));
        }
    }

    private void drawLine(Graphics2D graphics2D, Point first, Point second) {
        graphics2D.drawLine(first.getX(), first.getY(), second.getX(), second.getY());
    }
}
