package curves.drawers;

import curves.data.Parameters;
import curves.data.Point;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Drawer {

    public abstract List<List<Point>> drawCurves();

    public abstract List<Point> drawCurve();

    protected Parameters parameters;

    public Drawer(Parameters parameters) {
        this.parameters = parameters;
    }

    protected List<List<Point>> shiftCurves(List<List<Point>> curves) {
        return curves.stream()
                .map(this::shiftCurve)
                .collect(Collectors.toList());
    }

    private List<Point> shiftCurve(List<Point> curve) {
        return curve.stream()
                .map(point -> new Point(
                        point.getX() + parameters.getShift().getWidth(),
                        point.getY() + parameters.getShift().getHeight())
                )
                .collect(Collectors.toList());
    }
}
