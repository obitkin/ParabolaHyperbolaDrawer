package graphics.graph.parabola;

import graphics.dto.Point;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ParabolaDrawer {

    @Getter protected int width;
    @Getter protected int height;
    protected int centerX;
    protected int centerY;

    public ParabolaDrawer(int width, int height) {
        this.width = width;
        this.height = height;
        this.centerX = width / 2 ;
        this.centerY = height / 2;
    }

    protected int numberOfDots;
    protected double parameter;

    public ParabolaDrawer setParabola(int numberOfDots, double parameter) {
        this.numberOfDots = numberOfDots;
        this.parameter = parameter;
        return this;
    }

    protected abstract List<Point> draw(int numberOfDots, double parameterA);

    public List<Point> drawParabola() {
        return move(draw(numberOfDots, parameter), 50, centerY - 70);
    }

    private List<Point> move(List<Point> points, int delta_x, int delta_y) {
        return points.stream()
                .map(point -> new Point(point.getX() + delta_x, point.getY() + delta_y))
                .collect(Collectors.toList());
    }
}
