package graphics.graph.parabola;

import graphics.dto.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BestParabolaDrawer extends ParabolaDrawer {

    public BestParabolaDrawer(int width, int height) {
        super(width, height);
    }

    @Override
    protected List<Point> draw(int numberOfDots, double parameterA) {
        List<Point> bot = new ArrayList<>();
        List<Point> top = new ArrayList<>();
        int sigma_min = 0;
        double sigma_max = Math.sqrt((width - 100) / parameterA);
        double delta_sigma = sigma_max / numberOfDots;
        double y = 0;
        double x = 0;
        top.add(new Point(0, 0));
        double sigma = 0;
        for (int i = 0; i < numberOfDots; i++) {
            sigma += delta_sigma;
            x = x + y * delta_sigma + parameterA * delta_sigma * delta_sigma;
            y = y + 2 * parameterA * delta_sigma;
            top.add(new Point(x, -y));
            bot.add(new Point(x, y));
        }
        Collections.reverse(top);
        top.addAll(bot);
        return top;
    }

}
