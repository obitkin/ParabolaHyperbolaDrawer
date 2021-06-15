package mypackage.primitives;

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

    public Bezier(double x1, double y1, double ctrlx1, double ctrly1, double ctrlx2, double ctrly2, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.ctrlx1 = ctrlx1;
        this.ctrly1 = ctrly1;
        this.ctrlx2 = ctrlx2;
        this.ctrly2 = ctrly2;
        this.x2 = x2;
        this.y2 = y2;
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
