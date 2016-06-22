package src;

import javax.swing.*;
import java.awt.*;

public class TrominoRenderer extends JComponent {
    private int sq_height;
    private int sq_width;

    private void drawTromino(Graphics2D graphics, int n, int[] center, int quadrant) {
        this.sq_height = this.sq_width = this.getWidth() / n;

        switch (quadrant) {
            case 1:
                graphics.fillRect(center[0] * sq_width, center[1] * sq_height, sq_width,
                        sq_height);
                // Add 1 to x to go right
                graphics.fillRect((center[0] + 1) * sq_width, center[1] * sq_height, sq_width,
                        sq_height);
                // Subtract 1 from y to go up
                graphics.fillRect(center[0] * sq_width, (center[1] - 1) * sq_height, sq_width,
                        sq_height);
                break;
            case 2:
                graphics.fillRect(center[0] * sq_width, center[1] * sq_height, sq_width,
                        sq_height);
                // Sub 1 to x to go Left
                graphics.fillRect((center[0] - 1) * sq_width, center[1] * sq_height, sq_width,
                        sq_height);
                // Subtract 1 from y to go up
                graphics.fillRect(center[0] * sq_width, (center[1] - 1) * sq_height, sq_width,
                        sq_height);
                break;
            case 3:
                graphics.fillRect(center[0] * sq_width, center[1] * sq_height, sq_width,
                        sq_height);
                // Sub 1 to x to go Left
                graphics.fillRect((center[0] - 1) * sq_width, center[1] * sq_height, sq_width,
                        sq_height);
                // Add 1 from y to go down
                graphics.fillRect(center[0] * sq_width, (center[1] + 1) * sq_height, sq_width,
                        sq_height);
                break;
            case 4:
                graphics.fillRect(center[0] * sq_width, center[1] * sq_height, sq_width,
                        sq_height);
                // Add 1 to x to go center
                graphics.fillRect((center[0] + 1) * sq_width, center[1] * sq_height, sq_width,
                        sq_height);
                // Add 1 from y to go down
                graphics.fillRect(center[0] * sq_width, (center[1] + 1) * sq_height, sq_width,
                        sq_height);
                break;
        }
        System.out.println(quadrant);
    }
}
