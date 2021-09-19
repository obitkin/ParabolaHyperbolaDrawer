package curves.drawers.hyperbola;

import curves.data.Parameters;
import curves.data.Point;
import curves.drawers.Drawer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class HyperbolaDrawer extends Drawer {

    protected double A;
    protected double B;

    public HyperbolaDrawer(Parameters parameters, double A, double B) {
        super(parameters);
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
