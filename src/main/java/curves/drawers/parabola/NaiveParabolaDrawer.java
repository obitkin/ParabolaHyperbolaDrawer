package curves.drawers.parabola;

import curves.data.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class NaiveParabolaDrawer extends ParabolaDrawer {

    public NaiveParabolaDrawer(double minX, double maxX, Dimension shift, int numberOfDots, double A) {
        super(minX, maxX, shift, numberOfDots, A);
    }

    @Override
    public List<Point> drawCurve() {
        List<Point> curve = new ArrayList<>();
        double deltaX = (maxX - minX) / numberOfDots;
        double x = minX;
        double y = 2 * sqrt(A * x);
        curve.add(new Point(x, y));
        for (int i = 0; i < numberOfDots; i++) {
            x += deltaX;
            y = 2 * sqrt(A * x);
            curve.add(new Point(x, y));
        }
        return curve;
    }

}
