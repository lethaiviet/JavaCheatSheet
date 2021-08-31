package scripts;

import lombok.Setter;
import lombok.SneakyThrows;
import objects.BackGround;
import objects.FindingPathBFS;
import objects.RoadMap;
import org.jfree.ui.Size2D;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import utils.Common;

@Setter
public class JPanelTest extends JPanel {
    private RoadMap roadMap;
    private FindingPathBFS findingPathBFS;
    private BackGround backGround;

    @Override
    public void paintComponent(Graphics g) {
        backGround.draw(g);

        g.setColor(Color.YELLOW);
        findingPathBFS.draw(g);

        g.setColor(Color.WHITE);
        roadMap.draw(g);
    }

    @SneakyThrows
    public static void main(String[] args) {
        Size2D screenSize = Common.getScreenSize();
        int heightOfSquare = 15;
        int numOfSquareSkip = 20;

        int numOfSquareRows = (int) (screenSize.height / heightOfSquare - numOfSquareSkip);
        int numOfSquareColumns = (int) (screenSize.width / heightOfSquare - numOfSquareSkip);

        Point topLeftPosition = new Point(heightOfSquare * numOfSquareSkip / 2, heightOfSquare * numOfSquareSkip / 2);
        RoadMap roadMap = new RoadMap(numOfSquareRows, numOfSquareColumns, heightOfSquare, topLeftPosition);

        FindingPathBFS findingPathBFS = new FindingPathBFS(roadMap,
                new Point(0, 0),
                new Point(numOfSquareColumns - 1, numOfSquareRows - 1));
        findingPathBFS.calculate();

        BackGround backGround = new BackGround(screenSize, Color.BLACK);

        JFrame jFrame = new JFrame();
        JPanelTest jPanelTest = new JPanelTest();
        jPanelTest.setRoadMap(roadMap);
        jPanelTest.setFindingPathBFS(findingPathBFS);
        jPanelTest.setBackGround(backGround);
        jFrame.add(jPanelTest);
        jFrame.setSize((int) screenSize.width, (int) screenSize.height);
        jFrame.setVisible(true);

        while (true) {
            jFrame.repaint();
            Thread.sleep(30);
        }
    }

}