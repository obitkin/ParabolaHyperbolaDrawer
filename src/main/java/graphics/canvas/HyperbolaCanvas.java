package graphics.canvas;

import graphics.dto.Point;
import graphics.graph.hyperbola.HyperbolaDrawer;
import lombok.Setter;

import java.awt.*;
import java.util.List;

public class HyperbolaCanvas extends Canvas {

    public HyperbolaDrawer hyperbolaDrawer;
    @Setter
    private Boolean stateOfViewType = false;

    public HyperbolaCanvas(HyperbolaDrawer hyperbolaDrawer) {
        setBackground(Color.WHITE);
        setSize(hyperbolaDrawer.getWidth(), hyperbolaDrawer.getHeight());
        this.hyperbolaDrawer = hyperbolaDrawer;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2;
        g2 = (Graphics2D) g;
        List<List<Point>> lines = hyperbolaDrawer.drawHyperbola();
        for (var dots : lines) {
            if (stateOfViewType) {
                for (int i = 0; i < dots.size() - 1; i++) {
                    drawLine(g2, dots.get(i), dots.get(i + 1));
                }
            } else {
                for (graphics.dto.Point dot : dots) {
                    drawDot(g2, dot);
                }
            }
        }
    }

    private void drawLine(Graphics2D graphics2D, graphics.dto.Point first, graphics.dto.Point second) {
        graphics2D.drawLine(first.getX(), first.getY(), second.getX(), second.getY());
    }

    private void drawDot(Graphics2D graphics2D, Point point) {
        graphics2D.drawOval(point.getX(), point.getY(), 3, 3);
    }

}
