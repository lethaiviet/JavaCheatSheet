package utils;

import enums.VertexType;
import java.awt.*;
import org.jfree.ui.Size2D;

public class DrawUtils {
    public static void drawCircle(Graphics g, Point center, int radius) {
        g.drawOval(center.x - radius / 2, center.y - radius / 2, radius, radius);
    }

    public static void drawLine(Graphics g, Point start, Point end) {
        g.drawLine(start.x, start.y, end.x, end.y);
    }

    public static void fillColor(Graphics g, Point topLeftPos, Size2D size2D) {
        g.fillRect(topLeftPos.x, topLeftPos.y, (int) size2D.width, (int) size2D.height);
    }
}
