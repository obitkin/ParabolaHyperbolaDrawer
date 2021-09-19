package curves.canvas;

import curves.drawers.Drawer;
import curves.data.Point;
import lombok.Setter;

import java.awt.*;
import java.util.List;

public class CurveCanvas extends Canvas {

    @Setter
    public Drawer drawer;

    @Setter
    private Boolean stateOfViewType = false;

    public CurveCanvas(Drawer drawer, Dimension sizeOfCanvas) {
        setBackground(Color.WHITE);
        this.drawer = drawer;
        this.setSize(sizeOfCanvas);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2;
        g2 = (Graphics2D) g;
        List<List<Point>> curves = drawer.drawCurves();
        for (var curve : curves) {
            if (stateOfViewType) {
                for (int i = 0; i < curve.size() - 1; i++) {
                    drawLine(g2, curve.get(i), curve.get(i + 1));
                }
            } else {
                for (Point point : curve) {
                    drawDot(g2, point);
                }
            }
        }
    }

    private void drawLine(Graphics2D graphics2D, Point first, Point second) {
        graphics2D.drawLine(first.getXAsInt(), first.getYAsInt(), second.getXAsInt(), second.getYAsInt());
    }

    private void drawDot(Graphics2D graphics2D, Point point) {
        graphics2D.drawOval(point.getXAsInt(), point.getYAsInt(), 3, 3);
    }

}
