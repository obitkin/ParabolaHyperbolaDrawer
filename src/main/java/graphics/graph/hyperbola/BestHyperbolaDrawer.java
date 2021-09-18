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
        double sigmaMax = Math.PI / 2;
        deltaSigma = (sigmaMax - sigmaMin) / numberOfDots;
        List<List<Point>> lines = new ArrayList<>(4);
        x_1 = - a * Math.cosh(sigmaMin);
        y_1 = b * Math.sinh(sigmaMin);
        var line = getLine(numberOfDots, 1, 1);
        lines.add(line.stream().map(point -> new Point(point.x, point.y)).collect(Collectors.toList()));
        lines.add(line.stream().map(point -> new Point(point.x, -point.y)).collect(Collectors.toList()));
        lines.add(line.stream().map(point -> new Point(-point.x, point.y)).collect(Collectors.toList()));
        lines.add(line.stream().map(point -> new Point(-point.x, -point.y)).collect(Collectors.toList()));

//        lines.add(getLine(numberOfDots, 1, -1));
//        lines.add(getLine(numberOfDots, -1, 1));
//        lines.add(getLine(numberOfDots, -1, -1));
//        System.out.println("1 1");
//        System.out.println(lines.get(0));
//        System.out.println("1 -1");
//        System.out.println(lines.get(0));
//        System.out.println("-1 1");
//        System.out.println(lines.get(2));
//        System.out.println("-1 -1");
//        System.out.println(lines.get(3));
        return lines;
    }

    List<Point> getLine(int numberOfDots, int p1, int p2) {
        List<Point> points = new ArrayList<>();
        double x = x_1;
        double y = y_1;
        points.add(new Point(x, y));
        for (int i = 1; i < numberOfDots; i++) {
            Point newPoint = nextPoint(x, y, p1, p2);
            points.add(nextPoint(x, y, p1, p2));
            x = newPoint.x;
            y = newPoint.y;
        }
        return points;
    }

    Point nextPoint(double x, double y, int p1, int p2) {
        return new Point(p1 * nextX(x, y), p2 * nextY(x, y));
    }

    private double nextX(double x, double y) {
        return (b * x) / (b * Math.cos(deltaSigma) - y * Math.sin(deltaSigma));
    }

    private double nextY(double x, double y) {
        return b * (y + b * Math.tan(deltaSigma)) / (b - y * Math.tan(deltaSigma));
    }
}
