package curves.drawers.parabola;

import curves.data.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

public class BestParabolaDrawer extends ParabolaDrawer {

    public BestParabolaDrawer(Dimension size, Dimension shift, int numberOfDots, double A) {
        super(size, shift, numberOfDots, A);
    }

    @Override
    public List<Point> drawCurve() {
        List<Point> curve = new ArrayList<>();
        double sigmaMin = 0;
        double sigmaMax = sqrt(getSize().width / this.A);
        double sigmaDelta = (sigmaMax - sigmaMin) / numberOfDots;
        double y = 0;
        double x = 0;
        curve.add(new Point(0, 0));
        for (int i = 0; i < numberOfDots; i++) {
            x = x + y * sigmaDelta + A * sigmaDelta * sigmaDelta;
            y = y + 2 * A * sigmaDelta;
            curve.add(new Point(x, y));
        }
        return curve;
    }

}
