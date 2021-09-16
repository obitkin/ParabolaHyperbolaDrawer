package graphics.canvas;

import mypackage.canvas.MyBezierCanvas;
import mypackage.primitives.Bezier;
import mypackage.primitives.Point;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Thread.yield;

public class ParabolaCanvas extends Canvas {

    private int a;
    private int degree;

//    public static List<Point> getParabola(int a, int delta, int x_start, int y_start, int width, int height) {
//        return null;
//        //TODO
//    }


    public ParabolaCanvas(int width, int height) {
        setBackground(Color.WHITE);
        setSize(width, height);
    }

    Random random = new Random();

    public int value;

    @Override
    public void paint(Graphics g) {
        Graphics2D g2;
        g2 = (Graphics2D) g;
        System.out.println("--------++++++++++++");
        g2.draw3DRect(100, value, 200, 200, true);

    }
}
