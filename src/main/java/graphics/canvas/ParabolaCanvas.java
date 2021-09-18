package graphics.canvas;

import graphics.graph.parabola.ParabolaDrawer;
import graphics.dto.Point;
import lombok.Setter;

import java.awt.*;
import java.util.List;

public class ParabolaCanvas extends Canvas {

    public ParabolaDrawer parabolaDrawer;
    @Setter private Boolean stateOfViewType = false;

    public ParabolaCanvas(ParabolaDrawer parabolaDrawer) {
        setBackground(Color.WHITE);
        setSize(parabolaDrawer.getWidth(), parabolaDrawer.getHeight());
        this.parabolaDrawer = parabolaDrawer;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2;
        g2 = (Graphics2D) g;
        List<Point> dots = parabolaDrawer.drawParabola();
        if (stateOfViewType) {
            for (int i = 0; i < dots.size() - 1; i++) {
                drawLine(g2, dots.get(i), dots.get(i + 1));
            }
        } else {
            for (Point dot : dots) {
                drawDot(g2, dot);
            }
        }
    }

    private void drawLine(Graphics2D graphics2D, Point first, Point second) {
        graphics2D.drawLine(first.getX(), first.getY(), second.getX(), second.getY());
    }

    private void drawDot(Graphics2D graphics2D, Point point) {
        graphics2D.drawOval(point.getX(), point.getY(), 3, 3);
    }
}
