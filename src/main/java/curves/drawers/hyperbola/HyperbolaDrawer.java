package curves.drawers.hyperbola;

import curves.data.Point;
import curves.drawers.Drawer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class HyperbolaDrawer extends Drawer {

    protected int numberOfDots;
    protected double A;
    protected double B;

    public HyperbolaDrawer(double minX, double maxX, Dimension shift, int numberOfDots, double A, double B) {
        super(minX, maxX, shift);
        this.numberOfDots = numberOfDots;
        this.A = A;
        this.B = B;
    }

    @Override
    public List<List<Point>> drawCurves() {
        List<List<Point>> curves = new ArrayList<>(4);
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
        curves.add(curve
                .stream()
                .map(point -> new Point(-point.getX(), point.getY()))
                .collect(Collectors.toList())
        );
        curves.add(curve
                .stream()
                .map(point -> new Point(-point.getX(), -point.getY()))
                .collect(Collectors.toList())
        );
        return shiftCurves(curves);
    }
}
