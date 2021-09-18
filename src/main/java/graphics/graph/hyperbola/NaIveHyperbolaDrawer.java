package graphics.graph.hyperbola;

import graphics.dto.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NaIveHyperbolaDrawer extends HyperbolaDrawer {

    public NaIveHyperbolaDrawer(int width, int height) {
        super(width, height);
    }

    @Override
    protected List<List<Point>> draw(int numberOfDots, double a, double b) {
        List<List<Point>> lines = new ArrayList<>();
        List<Point> line = new ArrayList<>();
        double y = 0;
        double x = a;
        double deltaX = (centerX - a) / numberOfDots;
        for (int i = 0; i < numberOfDots; i++) {
            line.add(new Point(x, y));
            x += deltaX;
            y = Math.sqrt(b*b*x*x - a*a*b*b) / a;
        }
        lines.add(line.stream().map(point -> new Point(point.x, point.y)).collect(Collectors.toList()));
        lines.add(line.stream().map(point -> new Point(point.x, -point.y)).collect(Collectors.toList()));
        lines.add(line.stream().map(point -> new Point(-point.x, point.y)).collect(Collectors.toList()));
        lines.add(line.stream().map(point -> new Point(-point.x, -point.y)).collect(Collectors.toList()));
        return lines;
    }
}
