package curves.drawers.parabola;

import curves.data.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

public class BestParabolaDrawer extends ParabolaDrawer {

    public BestParabolaDrawer(double minX, double maxX, Dimension shift, int numberOfDots, double A) {
        super(minX, maxX, shift, numberOfDots, A);
    }

    @Override
    public List<Point> drawCurve() {
        List<Point> curve = new ArrayList<>();
        double sigmaMin = sqrt(minX / A);
        double sigmaMax = sqrt(maxX / A);
        double sigmaDelta = (sigmaMax - sigmaMin) / numberOfDots;
        double x = minX;
        double y = 2 * sqrt(A * x);
        curve.add(new Point(x, y));
        for (int i = 0; i < numberOfDots; i++) {
            x = x + y * sigmaDelta + A * sigmaDelta * sigmaDelta;
            y = y + 2 * A * sigmaDelta;
            curve.add(new Point(x, y));
        }
        return curve;
    }

}
