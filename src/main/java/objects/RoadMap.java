package objects;

import enums.Direction;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoadMap {
    int heightOfSquare;
    Point topLeftPosition;
    int numOfRows;
    int numOfColumns;
    List<List<Square>> squares;

    public RoadMap(int numOfRows, int numOfColumns, int heightOfSquare, Point topLeftPosition) {
        this.topLeftPosition = topLeftPosition;
        this.heightOfSquare = heightOfSquare;
        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
        initRoadMap();
    }

    private void initRoadMap() {
        squares = IntStream.range(0, numOfRows)
                .mapToObj(this::getSquareRow)
                .collect(Collectors.toList());
    }

    private List<Square> getSquareRow(int indY) {
        return IntStream.range(0, numOfColumns)
                .mapToObj(indX -> new Square(new Point(indX, indY), heightOfSquare, this.topLeftPosition))
                .collect(Collectors.toList());
    }

    public void draw(Graphics g) {
        for (int indX = 0; indX < this.numOfRows; indX++) {
            for (int indY = 0; indY < this.numOfColumns; indY++) {
                squares.get(indX).get(indY).draw(g);
//                squares.get(indX).get(indY).draw(g, String.format("(%s, %s)", indX, indY));
            }
        }
    }

    public Square getSquareByIndex(Point idx) {
        return squares.get(idx.y).get(idx.x);
    }

    private boolean isIndexValid(Point idx) {
        return idx.x >= 0
                && idx.y >= 0
                && idx.x < numOfColumns
                && idx.y < numOfRows;
    }

    private List<Point> getNeighbouringIndex(Square square) {
        return Arrays.stream(Direction.values())
                .map(square::getIdxByDirection)
                .filter(this::isIndexValid)
                .collect(Collectors.toList());
    }

    public List<Square> getNeighbouringSquares(Square square) {
        List<Square> squares = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            Point idx = square.getIdxByDirection(direction);

            if (!isIndexValid(idx)) continue;
            Square neighbor = getSquareByIndex(idx);

            if (!square.isPassing(neighbor, direction)) continue;
            squares.add(neighbor);
        }
        return squares;
    }
}
