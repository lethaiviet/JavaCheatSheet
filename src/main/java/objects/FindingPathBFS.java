package objects;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import utils.DrawUtils;

@Setter
@Getter
public class FindingPathBFS {
    private int count = 0;
    private RoadMap roadMap;
    private Point start;
    private Point end;
    private Map<Square, Square> exploredMap;
    private List<Square> reachedPath;

    public FindingPathBFS(RoadMap roadMap, Point start, Point end) {
        this.roadMap = roadMap;
        this.start = start;
        this.end = end;
        this.exploredMap = new HashMap<>();
    }

    private Square getSquareByIndex(Point idx) {
        return roadMap.getSquareByIndex(idx);
    }

    public void calculate() {
        exploreRoadMap();
        updateReachedPath();
    }

    private void updateReachedPath() {
        reachedPath = new ArrayList<>();
        Square current = getSquareByIndex(end);
        while (!current.equals(getSquareByIndex(start))) {
            reachedPath.add(current);
            current = exploredMap.get(current);
        }

        reachedPath.add(current);
        Collections.reverse(reachedPath);
    }

    private void exploreRoadMap() {
        exploredMap = new HashMap<>();
        Queue<Square> frontier = new LinkedList<>();
        frontier.add(getSquareByIndex(start));
        exploredMap.put(getSquareByIndex(start), null);

        while (!frontier.isEmpty()) {
            Square current = frontier.poll();
            for (Square next : roadMap.getNeighbouringSquares(current)) {
                if (exploredMap.containsKey(next)) continue;
                frontier.add(next);
                exploredMap.put(next, current);
            }
        }
    }

    @SneakyThrows
    public void draw(Graphics g) {
        Point start = null;
        int i = 0;
        for (Square square : reachedPath) {
            if (start == null) {
                start = square.getCenterPosition();
                continue;
            }

            if(i++ == count) break;
            Point end = square.getCenterPosition();
            square.fillColor(g);
            DrawUtils.drawLine(g, start, end);
            start = end;
        }

        if(count++ == reachedPath.size()) count = 0;
    }
}
