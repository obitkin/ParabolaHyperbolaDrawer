package curves.drawers.parabola;

import curves.data.Parameters;
import curves.data.Point;
import curves.drawers.Drawer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ParabolaDrawer extends Drawer {

    protected double A;

    public ParabolaDrawer(Parameters parameters, double A) {
        super(parameters);
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
