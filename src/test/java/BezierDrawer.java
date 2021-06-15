import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import static java.lang.Math.abs;

//https://stackoverflow.com/questions/3325546/how-to-color-a-pixel

public class BezierDrawer extends Canvas {
    private final static float m_distance_tolerance = 0.25f; // даёт хороший результат для типичных экранных решений
    private long timeEstimated;
    private BufferedImage canvas;
    private Window context;
    private java.util.List<Integer> poly;

    private int blackRGB = Color.black.getRGB();
    BezierDrawer(Window ctx, int w, int h) {
        canvas = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        setBackground (Color.GRAY);
        setSize(w, h);
        this.context = ctx;
        poly = new LinkedList<>();
    }
    long getTimeEstimated() {
        return timeEstimated;
    }
    @Override
    public void paint(Graphics g) {
        long time = System.nanoTime();
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        //_drawBezier(0, 0, 0, 100, 100, 100, 100, 0);
        double[] d = context.data;
        for (int k = 0; k < d.length / 8; k++) {
            int offset = k * 8;
            _drawBezier(d[offset], d[offset + 1], d[offset + 2], d[offset + 3], d[offset + 4], d[offset + 5], d[offset + 6], d[offset + 7]);
        }
        g2.drawImage(canvas, null, null);
        timeEstimated = System.nanoTime() - time;
        System.out.printf("Manually estimated: %d ns\n", timeEstimated);
        context.refreshTime();
    }

    private void _drawBezierRec(double x1, double y1,
                                double x2, double y2,
                                double x3, double y3,
                                double x4, double y4) {
        // определяем, насколько плоская представимая кривая, и если плоская -- рисуем её
        // Попытка аппроксимировать всю кривую одним отрезком//------------------
        double a = abs(x1 + x3 - x2 - x2);
        double b = abs(y1 + y3 - y2 - y2);
        double c = abs(x2 + x4 - x3 - x3);
        double d = abs(y2 + y4 - y3 - y3);
        if(a + b + c + d < m_distance_tolerance)
        {
            // рисуем линию (x1, y1) -> (x4, y4) алгоритмом Брезенхема
            //drawLine((int)x1, (int)x4, (int)y1, (int)y4);
            // вместо этого добавим точку (x23, y23)
            poly.add((int)(x2 + x3) / 2);
            poly.add((int)(y2 + y3) / 2);
            return;
        }

        // если добрались до этого момента, значит данная кривая всё ещё не может быть заменена на прямой отрезок
        // применяем алгоритм де Кастельжо для поиска точки разделения кривой
        double x12 = (x1 + x2) / 2;
        double x23 = (x2 + x3) / 2;
        double x34 = (x3 + x4) / 2;
        double x123 = (x12 + x23) / 2;
        double x234 = (x23 + x34) / 2;
        double x1234 = (x123 + x234) / 2;
        double y12 = (y1 + y2) / 2;
        double y23 = (y2 + y3) / 2;
        double y34 = (y3 + y4) / 2;
        double y123 = (y12 + y23) / 2;
        double y234 = (y23 + y34) / 2;
        double y1234 = (y123 + y234) / 2;
        // искомая точка: (x1234, y1234)
        // исходная кривая разбивается на две кривые Безье
        _drawBezierRec(x1, y1, x12, y12, x123, y123, x1234, y1234);
        _drawBezierRec(x1234, y1234, x234, y234, x34, y34, x4, y4);
    }

    private void drawPoly() {
        int lineCount = poly.size()/2 - 1;
        System.out.println(poly.size());
        for (int i = 0; i <lineCount; i++) {
            int x0 = poly.get(2*i);
            int y0 = poly.get(2*i + 1);
            int x1 = poly.get(2*i + 2);
            int y1 = poly.get(2*i + 3);
            drawLine(x0, x1, y0, y1);
        }
    }

    private void _drawBezier(double x1, double y1,
                             double ctrlx1, double ctrly1,
                             double ctrlx2, double ctrly2,
                             double x2, double y2) {
        poly.clear();
        poly.add((int)x1);
        poly.add((int)y1);
        _drawBezierRec(x1, y1, ctrlx1, ctrly1, ctrlx2, ctrly2, x2, y2);
        poly.add((int)x2);
        poly.add((int)y2);
        drawPoly();
        //repaint();
    }

    public void drawBezier(double x1, double y1,
                           double ctrlx1, double ctrly1,
                           double ctrlx2, double ctrly2,
                           double x2, double y2) {
        _drawBezier(x1, y1, ctrlx1, ctrly1, ctrlx2, ctrly2, x2, y2);
        repaint();
    }

    // реализация алгоритма Брезенхема (частный случай -- направление вправо вниз, угол < 45 градусов)
    // можно представить сдедующим образом:
    // По x надо заполнить dx пикселе, а по y надо заполнить dy пикселей
    // Соответственно это целочисленная арифметика с dx*dy количеством пикселей.
    // грубо говоря, работаем в отрезке [0, dx*dy], прибавляем по 1
    // при превышении dx -- добавляем или отнимаем у x единицу, сбрасываем ошибку dx
    // при превышении dy -- добавляем или отнимаем у y единицу, ссбрасываем ошибку dy
    public void drawLine(int x0, int x1, int y0, int y1) {
        int dx = abs(x1 - x0);
        int dy = abs(y1 - y0);
        final int signX = x0 < x1 ? 1 : -1;
        final int signY = y0 < y1 ? 1 : -1;
        int error = 0;
        if (dx > dy) { // передвижение ошибки с шагом dy более точно, дискретно движемся по OX
            int deltaerr = dy; // в непрерывном случае dx/dy
            while (x0 != x1 || y0 != y1) {
                try {
                    canvas.setRGB(x0, y0, blackRGB);
                } catch (Exception e) {
                    System.nanoTime();
                }
                error += deltaerr;
                if (2*error >= dx) {
                    y0 += signY; // error >= 0.5
                    error -= dx; // error -= dx/dy
                }
                x0 += signX;
            }
        } else { // передвижение ошибки с шагом dx более точно, шагаем на один по OY
            int deltaerr = dx;
            while (x0 != x1 || y0 != y1) {
                canvas.setRGB(x0, y0, blackRGB);
                error += deltaerr;
                if (2*error >= dy) {
                    x0 += signX; // error >= 0.5
                    error -= dy; // error -= dx/dy
                }
                y0 += signY;
            }
        }
    }
}
