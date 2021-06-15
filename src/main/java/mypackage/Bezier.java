package mypackage;

import java.util.Random;

public class Bezier {

    public Bezier(Random r, int maxX, int maxY) {
        x1 = r.nextDouble() * maxX;
        y1 = r.nextDouble() * maxY;
        ctrlx1 = r.nextDouble() * maxX;
        ctrly1 = r.nextDouble() * maxY;
        ctrlx2 = r.nextDouble() * maxX;
        ctrly2 = r.nextDouble() * maxY;
        x2 = r.nextDouble() * maxX;
        y2 = r.nextDouble() * maxY;
    }

    double x1;
    double y1;
    double ctrlx1;
    double ctrly1;
    double ctrlx2;
    double ctrly2;
    double x2;
    double y2;

    public double[] getValues() {
        return new double[] {x1, y1, ctrlx1, ctrly1, ctrlx2, ctrly2, x2, y2};
    }
}
