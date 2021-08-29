package utils;

import java.awt.*;

public class DrawUtils {
    public static void drawCircle(Graphics g, Point center, int radius) {
        g.drawOval(center.x - radius / 2, center.y - radius / 2, radius, radius);
    }

    public static void drawLine(Graphics g, Point start, Point end) {
        g.drawLine(start.x, start.y, end.x, end.y);
    }
}
