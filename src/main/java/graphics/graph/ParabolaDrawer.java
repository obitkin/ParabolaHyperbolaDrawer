package graphics.graph;

import graphics.dto.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ParabolaDrawer {

    private int width;
    private int height;
    private int centerX;
    private int centerY;

    public ParabolaDrawer(int width, int height) {
        this.width = width;
        this.height = height;
        this.centerX = width / 2 ;
        this.centerY = height / 2;
    }

    private int angleOfRotation;
    private int numberOfDots;
    private int parameter;

    public ParabolaDrawer setParabola(int angleOfRotation, int numberOfDots, int parameter) {
        this.angleOfRotation = angleOfRotation;
        this.numberOfDots = numberOfDots;
        this.parameter = parameter;
        return this;
    }

    public List<Point> drawParabola() {
        List<Point> bot = new ArrayList<>();
        List<Point> top = new ArrayList<>();
        int sigma_min = 0;
        double sigma_max = Math.sqrt(centerX / (double) parameter);
        double delta_sigma = sigma_max / numberOfDots;
        double y = 0;
        double x = 0;
        top.add(new Point(0, 0));
        for (double sigma = sigma_min; sigma < sigma_max; sigma += delta_sigma) {
            x = x + y * delta_sigma + parameter * delta_sigma * delta_sigma;
            y = y + 2 * parameter * delta_sigma;
            top.add(new Point(x, -y));
            bot.add(new Point(x, y));
        }
        Collections.reverse(top);
        top.addAll(bot);
        top = rotate(angleOfRotation, top);
        return rotate(angleOfRotation, top).stream()
                .map(point -> new Point(point.getX() + centerX, point.getY() + centerY))
                .collect(Collectors.toList());
    }

    private List<Point> rotate(int angleOfRotation, List<Point> points) {
        return points.stream()
                .map(point -> rotate(angleOfRotation, point))
                .collect(Collectors.toList());
    }

    private Point rotate(int angleOfRotation, Point point) {
        double angleInRadians = Math.toRadians(angleOfRotation);
        double newX = point.x * Math.cos(angleInRadians) - point.y * Math.sin(angleInRadians);
        double newY = point.x * Math.sin(angleInRadians) + point.y * Math.cos(angleInRadians);
        return new Point(newX, newY);
    }

}
