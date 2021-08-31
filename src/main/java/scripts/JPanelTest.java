package scripts;

import lombok.Setter;
import lombok.SneakyThrows;
import objects.FindingPathBFS;
import objects.RoadMap;
import org.jfree.ui.Size2D;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

@Setter
public class JPanelTest extends JPanel {
    private RoadMap roadMap;
    private FindingPathBFS findingPathBFS;

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.YELLOW);
        findingPathBFS.draw(g);

        g.setColor(Color.BLACK);
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

    @SneakyThrows
    public static void main(String[] args) {
        Size2D screenSize = JPanelTest.getScreenSize();
        int heightOfSquare = 10;
        int numOfSquareSkip = 20;

        int numOfSquareRows = (int) (screenSize.height / heightOfSquare - numOfSquareSkip);
        int numOfSquareColumns = (int) (screenSize.width / heightOfSquare - numOfSquareSkip);

        Point topLeftPosition = new Point(heightOfSquare * numOfSquareSkip / 2, heightOfSquare * numOfSquareSkip / 2);
        RoadMap roadMap = new RoadMap(numOfSquareRows, numOfSquareColumns, heightOfSquare, topLeftPosition);

        FindingPathBFS findingPathBFS = new FindingPathBFS(roadMap,
                new Point(0, 0),
                new Point(numOfSquareColumns - 1, numOfSquareRows - 1));
        findingPathBFS.calculate();

        JFrame jFrame = new JFrame();
        JPanelTest jPanelTest = new JPanelTest();
        jPanelTest.setRoadMap(roadMap);
        jPanelTest.setFindingPathBFS(findingPathBFS);
        jFrame.add(jPanelTest);
        jFrame.setSize((int) screenSize.width, (int) screenSize.height);
        jFrame.setVisible(true);

        while (true) {
            jFrame.repaint();
            Thread.sleep(30);
        }
    }

}