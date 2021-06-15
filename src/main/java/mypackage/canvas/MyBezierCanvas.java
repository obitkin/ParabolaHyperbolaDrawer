package mypackage.canvas;

import mypackage.primitives.Bezier;
import mypackage.primitives.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class MyBezierCanvas extends Canvas {

    private long timeEstimated;

    double m_distance_tolerance_manhattan = 4;

    private TextField timeOut;

    private List<Bezier> bezierList;

    private List<Point> pointList;

    public MyBezierCanvas(List<Bezier> bezierList, int w, int h, TextField timeOut) {
        setBackground (Color.WHITE);
        setSize(w, h);
        this.bezierList = bezierList;
        this.timeOut = timeOut;
    }

    void bezier(double x1, double y1,
                double x2, double y2,
                double x3, double y3,
                double x4, double y4)
    {
        pointList = new ArrayList<>();
        pointList.add(new Point((int) x1, (int) y1));
        recursive_bezier(x1, y1, x2, y2, x3, y3, x4, y4);
        pointList.add(new Point((int) x4, (int) y4));
    }

    void recursive_bezier(double x1, double y1,
                          double x2, double y2,
                          double x3, double y3,
                          double x4, double y4)
    {

        // Попытка аппроксимировать всю кривую одним отрезком//------------------
        double a = abs(x1 + x3 - x2 - x2);
        double b = abs(y1 + y3 - y2 - y2);
        double c = abs(x2 + x4 - x3 - x3);
        double d = abs(y2 + y4 - y3 - y3);
        if(a + b + c + d < m_distance_tolerance_manhattan)
        {
            pointList.add(new Point((int) (x2 + x3) / 2, (int) (y2 + y3) / 2));
            return;
        }

        // Вычислить все средние точек отрезков//----------------------
        double x12  = (x1 + x2) / 2;
        double y12  = (y1 + y2) / 2;
        double x23  = (x2 + x3) / 2;
        double y23  = (y2 + y3) / 2;
        double x34  = (x3 + x4) / 2;
        double y34  = (y3 + y4) / 2;
        double x123  = (x12 + x23) / 2;
        double y123  = (y12 + y23) / 2;
        double x234  = (x23 + x34) / 2;
        double y234  = (y23 + y34) / 2;
        double x1234 = (x123 + x234) / 2;
        double y1234 = (y123 + y234) / 2;

        // Продожить деление//----------------------
        recursive_bezier(x1, y1, x12, y12, x123, y123, x1234, y1234);
        recursive_bezier(x1234, y1234, x234, y234, x34, y34, x4, y4);
    }

    private void drawLine(Graphics2D graphics2D, Point first, Point second) {
        graphics2D.drawLine(first.getX(), first.getY(), second.getX(), second.getY());
    }

    public void paint (Graphics g) {
        timeEstimated = System.currentTimeMillis();
        Graphics2D g2;
        g2 = (Graphics2D) g;

        for (Bezier bezier: bezierList) {
            bezier(
                    bezier.getValues()[0],
                    bezier.getValues()[1],
                    bezier.getValues()[2],
                    bezier.getValues()[3],
                    bezier.getValues()[4],
                    bezier.getValues()[5],
                    bezier.getValues()[6],
                    bezier.getValues()[7]);
            for (int i = 0; i < pointList.size() - 1; i++) {
                drawLine(g2, pointList.get(i), pointList.get(i + 1));
            }
        }
        timeEstimated = System.currentTimeMillis() - timeEstimated;
        if (timeOut != null)
            timeOut.setText(String.valueOf(timeEstimated));
    }
}
