package graphics.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class Point {

    public double x;
    public double y;

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public Point revertY() {
        return new Point(x, -y);
    }

}

