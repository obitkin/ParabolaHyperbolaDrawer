package curves.drawers.hyperbola;

import curves.data.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class BestHyperbolaDrawer extends HyperbolaDrawer {

    public BestHyperbolaDrawer(double minX, double maxX, Dimension shift, int numberOfDots, double A, double B) {
        super(minX, maxX, shift, numberOfDots, A, B);
    }

    @Override
    public List<Point> drawCurve() {
        double sigmaMin = log(minX / A + sqrt(pow(minX / A, 2) - 1));
        double sigmaMax = log(maxX / A + sqrt(pow(maxX / A, 2) - 1));
        double sigmaDelta = (sigmaMax - sigmaMin) / numberOfDots;
        List<Point> curve = new ArrayList<>();
        double x = minX;
        double y = sqrt(pow(B, 2) * pow(x, 2) - pow(A, 2) * pow(B, 2)) / A;
        curve.add(new Point(x, y));
        for (int i = 0; i < numberOfDots; i++) {
            x = x * cosh(sigmaDelta) + (A / B) * y * sinh(sigmaDelta);
            y = (B / A) * x * sinh(sigmaDelta) + y * cosh(sigmaDelta);
            curve.add(new Point(x, y));
        }
        return curve;
    }
}
