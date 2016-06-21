package src;

import javax.swing.*;
import java.awt.*;

/**
 * Created by goistjt on 6/20/2016.
 */
public class QuadrantRenderer extends JComponent {
    private int sq_width;
    private int sq_height;
    /**
     * [0]: x
     * [1]: y
     * Yay for stupid Java graphics and this being the easiest representation.
     */
    private int[] init_deficiency;
    private int n;

    public QuadrantRenderer(int n, int[] init_deficiency) {
        this.n = n;
        this.init_deficiency = init_deficiency;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.sq_height = this.sq_width = this.getWidth() / n;
        Graphics2D graphics = (Graphics2D) g;

        drawInitGrid(graphics);
        drawTrominoes(graphics, init_deficiency, n);
    }

    private void drawTrominoes(Graphics2D graphics, int[] deficiency, int n) {
        int q = determineQuadrant(deficiency, n);
        int[] oppositeInnerCorner;
        switch (q) {
            case 1:
                // Have to offset 1 left for some reason, yay graphics
                oppositeInnerCorner = new int[]{(n / 2) - 1, (n / 2)};
                graphics.fillRect(oppositeInnerCorner[0] * sq_width, oppositeInnerCorner[1] * sq_height, sq_width,
                        sq_height);
                // Add 1 to x to go right
                graphics.fillRect((oppositeInnerCorner[0] + 1) * sq_width, oppositeInnerCorner[1] * sq_height, sq_width,
                        sq_height);
                // Subtract 1 from y to go up
                graphics.fillRect(oppositeInnerCorner[0] * sq_width, (oppositeInnerCorner[1] - 1) * sq_height, sq_width,
                        sq_height);
                break;
            case 2:
                // Have to offset 1 left for some reason, yay graphics
                oppositeInnerCorner = new int[]{(n / 2), (n / 2)};
                graphics.fillRect(oppositeInnerCorner[0] * sq_width, oppositeInnerCorner[1] * sq_height, sq_width,
                        sq_height);
                // Sub 1 to x to go Left
                graphics.fillRect((oppositeInnerCorner[0] - 1) * sq_width, oppositeInnerCorner[1] * sq_height, sq_width,
                        sq_height);
                // Subtract 1 from y to go up
                graphics.fillRect(oppositeInnerCorner[0] * sq_width, (oppositeInnerCorner[1] - 1) * sq_height, sq_width,
                        sq_height);
                break;
            case 3:
                oppositeInnerCorner = new int[]{(n / 2), (n / 2)-1};
                graphics.fillRect(oppositeInnerCorner[0] * sq_width, oppositeInnerCorner[1] * sq_height, sq_width,
                        sq_height);
                // Sub 1 to x to go Left
                graphics.fillRect((oppositeInnerCorner[0] - 1) * sq_width, oppositeInnerCorner[1] * sq_height, sq_width,
                        sq_height);
                // Add 1 from y to go down
                graphics.fillRect(oppositeInnerCorner[0] * sq_width, (oppositeInnerCorner[1] + 1) * sq_height, sq_width,
                        sq_height);
                break;
            case 4:
                oppositeInnerCorner = new int[]{(n / 2)-1, (n / 2)-1};
                graphics.fillRect(oppositeInnerCorner[0] * sq_width, oppositeInnerCorner[1] * sq_height, sq_width,
                        sq_height);
                // Add 1 to x to go right
                graphics.fillRect((oppositeInnerCorner[0] + 1) * sq_width, oppositeInnerCorner[1] * sq_height, sq_width,
                        sq_height);
                // Add 1 from y to go down
                graphics.fillRect(oppositeInnerCorner[0] * sq_width, (oppositeInnerCorner[1] + 1) * sq_height, sq_width,
                        sq_height);
                break;
        }
        System.out.println(q);
    }

    private int determineQuadrant(int[] deficiency, int n) {
        if (deficiency[1] < n / 2) {
            if (deficiency[0] < n / 2) {
                return 2;
            } else {
                return 1;
            }
        } else {
            if (deficiency[0] < n / 2) {
                return 3;
            } else {
                return 4;
            }
        }
    }

    private void drawInitGrid(Graphics2D graphics) {
        graphics.setColor(Color.BLACK);
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (init_deficiency[0] == x && init_deficiency[1] == y) {
                    graphics.fillRect(x * sq_width, y * sq_height, sq_width, sq_height);
                } else {
                    graphics.drawRect(x * sq_width, y * sq_height, sq_width, sq_height);
                }
            }
        }
    }
}
