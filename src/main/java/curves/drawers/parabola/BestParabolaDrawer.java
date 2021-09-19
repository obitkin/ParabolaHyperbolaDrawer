package curves.drawers.parabola;

import curves.data.Parameters;
import curves.data.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

public class BestParabolaDrawer extends ParabolaDrawer {

    public BestParabolaDrawer(Parameters parameters, double A) {
        super(parameters, A);
    }

    @Override
    public List<Point> drawCurve() {
        double maxX = parameters.getMaxX();
        double minX = parameters.getMinX();
        int numberOfDots = parameters.getNumberOfDots();
        int numberOfInterval = parameters.getNumberOfDots() - 1;

        List<Point> curve = new ArrayList<>();
        double sigmaMin = sqrt(minX / A);
        double sigmaMax = sqrt(maxX / A);
        double sigmaDelta = (sigmaMax - sigmaMin) / numberOfInterval;
        double x = minX;
        double y = 2 * sqrt(A * x);
        for (int i = 0; i < numberOfDots; i++) {
            curve.add(new Point(x, y));
            x = x + y * sigmaDelta + A * sigmaDelta * sigmaDelta;
            y = y + 2 * A * sigmaDelta;
        }
        return curve;
    }

}
