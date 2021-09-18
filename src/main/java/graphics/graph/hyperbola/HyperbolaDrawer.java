package graphics.graph.hyperbola;

import graphics.dto.Point;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

public abstract class HyperbolaDrawer {

    @Getter
    protected int width;
    @Getter protected int height;
    protected int centerX;
    protected int centerY;

    public HyperbolaDrawer(int width, int height) {
        this.width = width;
        this.height = height;
        this.centerX = width / 2 ;
        this.centerY = height / 2;
    }

    protected int numberOfDots;
    protected double a;
    protected double b;

    public HyperbolaDrawer setHyperbola(int numberOfDots, double a, double b) {
        this.numberOfDots = numberOfDots;
        this.a = a;
        this.b = b;
        return this;
    }

    protected abstract List<List<Point>> draw(int numberOfDots, double a, double b);

    public List<List<Point>> drawHyperbola() {
        return moveLines(draw(numberOfDots, a, b), centerX, centerY - 70);
    }

    private List<List<Point>> moveLines(List<List<Point>> lines, int delta_x, int delta_y) {
        return lines.stream()
                .map(line -> moveLine(line, delta_x, delta_y))
                .collect(Collectors.toList());
    }

    private List<Point> moveLine(List<Point> line, int delta_x, int delta_y) {
        return line.stream()
                .map(point -> new Point(point.getX() + delta_x, point.getY() + delta_y))
                .collect(Collectors.toList());
    }

}
