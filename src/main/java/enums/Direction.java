package enums;

import java.awt.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Direction {
    TOP(new Point(0, -1)),
    BOTTOM(new Point(0, 1)),
    LEFT(new Point(-1, 0)),
    RIGHT(new Point(1, 0));

    private final Point directorVec;
}
