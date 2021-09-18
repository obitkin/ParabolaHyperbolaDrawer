package graphics.graph.parabola;

import graphics.dto.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NaiveParabolaDrawer extends ParabolaDrawer {

    public NaiveParabolaDrawer(int width, int height) {
        super(width, height);
    }

    @Override
    protected List<Point> draw(int numberOfDots, double parameterA) {
        List<Point> bot = new ArrayList<>();
        List<Point> top = new ArrayList<>();
        double minX = 0;
        double maxX = width - 100;
        double delta_x = (maxX - minX) / (double) numberOfDots;
        top.add(new Point(0, 0));
        double x = minX;
        for (int i = 0; i < numberOfDots; i++) {
            x += delta_x;
            double y = 2 * Math.sqrt(parameterA * x);
            top.add(new Point(x, y));
            bot.add(new Point(x, -y));
        }
        Collections.reverse(top);
        top.addAll(bot);
        return top;
    }

}
