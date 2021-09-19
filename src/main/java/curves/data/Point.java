package curves.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class Point {

    public double x;
    public double y;

    public int getXAsInt() {
        return (int) x;
    }

    public int getYAsInt() {
        return (int) y;
    }
}
