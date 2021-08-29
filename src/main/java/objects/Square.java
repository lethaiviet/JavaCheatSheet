package objects;

import enums.Direction;
import enums.VertexType;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.List;
import utils.Common;
import utils.DrawUtils;

@Setter
@Getter
public class Square {
    private int height;
    private Point index;
    private Point vectorTranspose;
    private Map<Direction, Boolean> mapBorders;
    private Map<VertexType, Point> mapVertices;

    public Square(Point index, int height, Point vectorTranspose) {
        this.height = height;
        this.index = index;
        this.vectorTranspose = vectorTranspose;

        initMapVertices();
        initMapBorderRandom();
    }

    private Point getVertexPosition(VertexType vertexType) {
        int dy = vertexType.getDirection1() == Direction.BOTTOM ? height : 0;
        int dx = vertexType.getDirection2() == Direction.RIGHT ? height : 0;

        Point vertexPosition = new Point(index.x * height, index.y * height);
        vertexPosition.translate(vectorTranspose.x, vectorTranspose.y);
        vertexPosition.translate(dx, dy);
        return vertexPosition;
    }

    private List<Point> getVerticesByDirection(Direction direction) {
        return mapVertices.entrySet().stream()
                .filter(x -> x.getKey().hasDirection(direction))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    private void initMapBorderRandom() {
        mapBorders = Arrays.stream(Direction.values())
                .collect(Collectors.toMap(x -> x, x -> Common.getRandomBoolean(25)));
    }

    private void initMapVertices() {
        mapVertices = Arrays.stream(VertexType.values())
                .collect(Collectors.toMap(x -> x, this::getVertexPosition));
    }

    public void draw(Graphics g) {
        for (Direction direction : Direction.values()) {
            if (!mapBorders.getOrDefault(direction, false)) continue;

            List<Point> vertices = getVerticesByDirection(direction);
            DrawUtils.drawLine(g, vertices.get(0), vertices.get(1));
        }
    }

    public void draw(Graphics g, String str) {
        draw(g);
        Point topLeftPosition = new Point(mapVertices.get(VertexType.TOP_LEFT));
        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        g.drawString(str, topLeftPosition.x + height / 4, topLeftPosition.y + height / 2);
    }
}
