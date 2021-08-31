package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VertexType {
    TOP_LEFT(Direction.TOP, Direction.LEFT),
    TOP_RIGHT(Direction.TOP, Direction.RIGHT),
    BOTTOM_LEFT(Direction.BOTTOM, Direction.LEFT),
    BOTTOM_RIGHT(Direction.BOTTOM, Direction.RIGHT);

    private final Direction direction1;
    private final Direction direction2;

    public boolean hasDirection(Direction direction) {
        return direction1 == direction || direction2 == direction;
    }
}
