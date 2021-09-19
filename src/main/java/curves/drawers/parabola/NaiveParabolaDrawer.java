package curves.drawers.parabola;

import curves.data.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NaiveParabolaDrawer extends ParabolaDrawer {

    public NaiveParabolaDrawer(Dimension size, Dimension shift, int numberOfDots, double A) {
        super(size, shift, numberOfDots, A);
    }

    @Override
    public List<Point> drawCurve() {
        List<Point> curve = new ArrayList<>();
        double minX = 0;
        double maxX = getShift().getWidth();
        double deltaX = (maxX - minX) / numberOfDots;
        double x = minX;
        double y = 0;
        curve.add(new Point(x, y));
        for (int i = 0; i < numberOfDots; i++) {
            x += deltaX;
            y = 2 * Math.sqrt(A * x);
            curve.add(new Point(x, y));
        }
        return curve;
    }

}
