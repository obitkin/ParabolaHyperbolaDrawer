package graphics.graph.hyperbola;

import graphics.dto.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BestHyperbolaDrawer extends HyperbolaDrawer {

    public BestHyperbolaDrawer(int width, int height) {
        super(width, height);
    }

    double deltaSigma;
    double x_1;
    double y_1;

    @Override
    protected List<List<Point>> draw(int numberOfDots, double a, double b) {
        double sigmaMin = 0;
        double tmp = centerX / a;
        double sigmaMax = Math.log(tmp + Math.sqrt(tmp * tmp - 1));
        deltaSigma = (sigmaMax - sigmaMin) / numberOfDots;
        List<List<Point>> lines = new ArrayList<>(4);
        x_1 = a;
        y_1 = 0;
        System.out.println("sigma MAX = " + sigmaMax);
        System.out.println("delta sigma = " + deltaSigma);
        var line = getLine(numberOfDots);
        line.forEach(point -> System.out.println(point.x + " " + point.y));
        System.out.println("sinh = " + Math.sinh(deltaSigma));
        System.out.println("cosh = " + Math.cosh(deltaSigma));
        lines.add(line.stream().map(point -> new Point(point.x, point.y)).collect(Collectors.toList()));
        lines.add(line.stream().map(point -> new Point(point.x, -point.y)).collect(Collectors.toList()));
        lines.add(line.stream().map(point -> new Point(-point.x, point.y)).collect(Collectors.toList()));
        lines.add(line.stream().map(point -> new Point(-point.x, -point.y)).collect(Collectors.toList()));
        return lines;
    }

    List<Point> getLine(int numberOfDots) {
        List<Point> points = new ArrayList<>();
        double x = x_1;
        double y = y_1;
        points.add(new Point(x, y));
        for (int i = 0; i < numberOfDots; i++) {
            Point newPoint = nextPoint(x, y);
            points.add(newPoint);
            x = newPoint.x;
            y = newPoint.y;
        }
        return points;
    }

    Point nextPoint(double x, double y) {
        return new Point(nextX(x, y), nextY(x, y));
    }

    private double nextX(double x, double y) {
        return x * Math.cosh(deltaSigma) + (a / b) * y * Math.sinh(deltaSigma);
    }

    private double nextY(double x, double y) {
        return (b / a) * x * Math.sinh(deltaSigma) + y * Math.cosh(deltaSigma);
    }
}
