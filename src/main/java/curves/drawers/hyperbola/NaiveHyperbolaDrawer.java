package curves.drawers.hyperbola;

import curves.data.Parameters;
import curves.data.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class NaiveHyperbolaDrawer extends HyperbolaDrawer {

    public NaiveHyperbolaDrawer(Parameters parameters, double A, double B) {
        super(parameters, A, B);
    }

    @Override
    public List<Point> drawCurve() {
        double maxX = parameters.getMaxX();
        double minX = parameters.getMinX();
        int numberOfDots = parameters.getNumberOfDots();

        List<Point> curve = new ArrayList<>();
        double x = minX;
        double y = sqrt(pow(B, 2) * pow(x, 2) - pow(A, 2) * pow(B, 2)) / A;
        double deltaX = (maxX - minX) / numberOfDots;
        curve.add(new Point(x, y));
        for (int i = 0; i < numberOfDots; i++) {
            curve.add(new Point(x, y));
            x += deltaX;
            y = sqrt(pow(B, 2) * pow(x, 2) - pow(A, 2) * pow(B, 2)) / A;
        }
        return curve;
    }
}
