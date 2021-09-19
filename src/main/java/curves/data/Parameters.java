package curves.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.awt.*;

@Getter
@ToString
@AllArgsConstructor
public class Parameters {

    double minX;
    double maxX;
    Dimension shift;
    int numberOfDots;

}
