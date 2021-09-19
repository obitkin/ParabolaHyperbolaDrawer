package curves.drawers.hyperbola;

import curves.data.Parameters;
import curves.data.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class BestHyperbolaDrawer extends HyperbolaDrawer {

    public BestHyperbolaDrawer(Parameters parameters, double A, double B) {
        super(parameters, A, B);
    }

    @Override
    public List<Point> drawCurve() {
        double maxX = parameters.getMaxX();
        double minX = parameters.getMinX();
        int numberOfDots = parameters.getNumberOfDots();
        int numberOfInterval = parameters.getNumberOfDots() - 1;

        List<Point> curve = new ArrayList<>();
        double sigmaMin = log(minX / A + sqrt(pow(minX / A, 2) - 1));
        double sigmaMax = log(maxX / A + sqrt(pow(maxX / A, 2) - 1));
        double sigmaDelta = (sigmaMax - sigmaMin) / numberOfInterval;
        double x = minX;
        double y = sqrt(pow(B, 2) * pow(x, 2) - pow(A, 2) * pow(B, 2)) / A;
        for (int i = 0; i < numberOfDots; i++) {
            curve.add(new Point(x, y));
            double x_i = x;
            x = x * cosh(sigmaDelta) + (A / B) * y * sinh(sigmaDelta);
            y = (B / A) * x_i * sinh(sigmaDelta) + y * cosh(sigmaDelta);
        }
        return curve;
    }
}
