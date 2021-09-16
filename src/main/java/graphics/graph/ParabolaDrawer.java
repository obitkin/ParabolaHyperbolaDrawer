package graphics.graph;

import graphics.dto.Point;

import java.util.ArrayList;
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
        List<Point> res = new ArrayList<>();
        int sigma_min = 0;
        double sigma_max = Math.sqrt(centerX / (double) parameter);
        double delta_sigma = sigma_max / numberOfDots;
        double y = 0;
        double x = 0;
        res.add(new Point(x, y));
        for (double sigma = sigma_min; sigma < sigma_max; sigma += delta_sigma) {
            x = x + y * delta_sigma + parameter * delta_sigma * delta_sigma;
            y = y + 2 * parameter * delta_sigma;
            res.add(new Point(x, y));
            System.out.println("x = " + x + "  y = " + y);
        }
        System.out.println("Res size = " + res.size());
        return res;
    }

}
