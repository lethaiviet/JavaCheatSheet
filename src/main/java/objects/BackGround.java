package objects;

import java.awt.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jfree.ui.Size2D;
import utils.DrawUtils;

@Setter
@Getter
@AllArgsConstructor
public class BackGround {
    private Size2D size;
    private Color color;

    public void draw(Graphics g) {
        DrawUtils.fillColor(g, new Point(0, 0), size);
    }
}
