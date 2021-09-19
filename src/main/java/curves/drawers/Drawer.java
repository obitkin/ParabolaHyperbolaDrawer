package curves.drawers;

import curves.data.Point;
import lombok.Getter;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Drawer {

    public abstract List<List<Point>> drawCurves();

    public abstract List<Point> drawCurve();

    @Getter protected Dimension size;
    @Getter protected Dimension shift;

    public Drawer(Dimension size, Dimension shift) {
        this.size = size;
        this.shift = shift;
    }

    protected List<List<Point>> shiftCurves(List<List<Point>> curves) {
        return curves.stream()
                .map(this::shiftCurve)
                .collect(Collectors.toList());
    }

    private List<Point> shiftCurve(List<Point> curve) {
        return curve.stream()
                .map(point -> new Point(
                        point.getX() + getShift().getWidth(),
                        point.getY() + getShift().getHeight())
                )
                .collect(Collectors.toList());
    }
}
