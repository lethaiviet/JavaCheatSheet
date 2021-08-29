package scripts;

import lombok.Setter;
import objects.RoadMap;
import org.jfree.ui.Size2D;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

@Setter
public class JPanelTest extends JPanel {
    private RoadMap roadMap;
    @Override
    public void paintComponent(Graphics g) {
//        Size2D screenSize = getScreenSize();
//        int radius = 40;
//        int midScreenWidth = (int) screenSize.width / 2;
//        Point center1 = new Point(midScreenWidth, 40);
//        Point center2 = new Point(midScreenWidth, 100);
//

//        drawCircle(g, center1, radius);
//        drawCircle(g, center2, radius);
//        drawLine(g, center1, center2);

        roadMap.draw(g);
    }

    private void drawCircle(Graphics g, Point center, int radius) {
        g.drawOval(center.x - radius / 2, center.y - radius / 2, radius, radius);
    }

    private void drawLine(Graphics g, Point start, Point end) {
        g.drawLine(start.x, start.y, end.x, end.y);
    }

    private static Size2D getScreenSize() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        return new Size2D(gd.getDisplayMode().getWidth(), gd.getDisplayMode().getHeight());
    }

    public static void main(String[] args) {
        Size2D screenSize = JPanelTest.getScreenSize();
        int heightOfSquare = 10;
        int numOfSquareSkip = 20;

        int numOfSquareRows = (int) (screenSize.height / heightOfSquare - numOfSquareSkip);
        int numOfSquareColumns = (int) (screenSize.width / heightOfSquare - numOfSquareSkip);

        Point topLeftPosition = new Point(heightOfSquare * numOfSquareSkip / 2, heightOfSquare * numOfSquareSkip / 2);
        RoadMap roadMap = new RoadMap(numOfSquareRows, numOfSquareColumns, heightOfSquare, topLeftPosition);

        JFrame jFrame = new JFrame();
        JPanelTest jPanelTest = new JPanelTest();
        jPanelTest.setRoadMap(roadMap);
        jFrame.add(jPanelTest);
        jFrame.setSize((int) screenSize.width, (int) screenSize.height);
        jFrame.setVisible(true);
    }

}