package curves.drawers.parabola;

import curves.data.Point;
import curves.drawers.Drawer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ParabolaDrawer extends Drawer {

    protected int numberOfDots;
    protected double A;

    public ParabolaDrawer(Dimension size, Dimension shift, int numberOfDots, double A) {
        super(size, shift);
        this.numberOfDots = numberOfDots;
        this.A = A;
    }

    @Override
    public List<List<Point>> drawCurves() {
        List<List<Point>> curves = new ArrayList<>(2);
        List<Point> curve = drawCurve();
        curves.add(curve
                .stream()
                .map(point -> new Point(point.getX(), point.getY()))
                .collect(Collectors.toList())
        );
        curves.add(curve
                .stream()
                .map(point -> new Point(point.getX(), -point.getY()))
                .collect(Collectors.toList())
        );
        return shiftCurves(curves);
    }
}
