package objects;

import java.awt.*;
import java.util.List;
import java.util.function.IntFunction;
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
}
