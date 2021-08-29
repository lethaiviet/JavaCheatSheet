package utils;

import java.awt.*;
import java.util.Random;
import org.jfree.ui.Size2D;

public class Common {
    public static Size2D getScreenSize() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        return new Size2D(gd.getDisplayMode().getWidth(), gd.getDisplayMode().getHeight());
    }

    public static boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    public static boolean getRandomBoolean(int rateTrue) {
        Random random = new Random();
        return random.nextInt(100) < rateTrue;
    }
}
