package graphics.graph;

import graphics.dto.Point;

import java.util.List;

public class ParabolaDrawer {

    private int width;
    private int height;
    private int centerX;
    private int centerY;

    public ParabolaDrawer(int width, int height) {
        this.width = width;
        this.height = height;
        this.centerX = width / 2;
        this.centerY = height / 2;
    }

    private int parameter;
    private int angleOfRotation;
    private int numberOfDots;

    public ParabolaDrawer setParabola(int angleOfRotation, int numberOfDots, int parameter) {
        this.angleOfRotation = angleOfRotation;
        this.numberOfDots = numberOfDots;
        this.parameter = parameter;
        return this;
    }

    public List<Point> drawParabola() {
        return null;
    }

}
