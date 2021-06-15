package mypackage.canvas;

import mypackage.Bezier;

import java.awt.*;
import java.awt.geom.CubicCurve2D;
import java.util.List;

public class LibBezierCanvas extends Canvas {

    private long timeEstimated;

    private TextField timeOut;

    private List<Bezier> bezierList;

    public LibBezierCanvas(List<Bezier> bezierList, int w, int h, TextField timeOut) {
        setBackground (Color.WHITE);
        setSize(w, h);
        this.bezierList = bezierList;
        this.timeOut = timeOut;
    }

    public void paint (Graphics g) {
        long time = System.currentTimeMillis();
        Graphics2D g2;
        g2 = (Graphics2D) g;

        // библиотечная встроенная функция рисования кривых Безье третьего порядка

        for (Bezier bezier: bezierList) {
            CubicCurve2D cc2d = new CubicCurve2D.Double(
                    bezier.getValues()[0],
                    bezier.getValues()[1],
                    bezier.getValues()[2],
                    bezier.getValues()[3],
                    bezier.getValues()[4],
                    bezier.getValues()[5],
                    bezier.getValues()[6],
                    bezier.getValues()[7]);
            g2.draw(cc2d);
        }

        timeEstimated = (System.currentTimeMillis() - time);
        if (timeOut != null)
            timeOut.setText(String.valueOf(timeEstimated));
    }
}
