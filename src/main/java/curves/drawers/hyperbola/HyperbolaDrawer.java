package curves.drawers.hyperbola;

import curves.data.Point;
import curves.drawers.Drawer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class HyperbolaDrawer extends Drawer {

    public HyperbolaDrawer(Dimension size, Dimension shift) {
        super(size, shift);
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
