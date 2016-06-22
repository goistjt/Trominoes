package src;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/**
 * Created by goistjt on 6/20/2016.
 */
public class QuadrantRenderer extends JComponent {
    private int sq_width;
    private int sq_height;
    private int trom_num = 1;
    /**
     * [0]: x
     * [1]: y
     * Yay for stupid Java graphics and this being the easiest representation.
     */
    private int[] init_deficiency;
    private int n;

    QuadrantRenderer(int n, int[] init_deficiency) {
        this.n = n;
        this.init_deficiency = init_deficiency;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.sq_height = this.sq_width = (this.getWidth()-30) / n;
        Graphics2D graphics = (Graphics2D) g;

        drawInitGrid(graphics);
        drawTrominoes(graphics, init_deficiency, n, 0, 0);
    }

    private void drawTrominoes(Graphics2D graphics, int[] deficiency, int n, int offset_x, int offset_y) {
        int q = determineQuadrant(new int[]{deficiency[0] - offset_x, deficiency[1] - offset_y}, n);
        int[] oppositeInnerCorner;
        int[] m1, m2, m3;
        switch (q) {
            case 1:
                // Have to offset 1 left for some reason, yay graphics
                oppositeInnerCorner = new int[]{(n / 2) - 1, (n / 2)};
                // Q3
                m1 = new int[]{oppositeInnerCorner[0] + offset_x, oppositeInnerCorner[1] + offset_y};
                graphics.fillRect(m1[0] * sq_width, m1[1] * sq_height, sq_width,
                        sq_height);
                graphics.setColor(Color.WHITE);
                graphics.drawString(""+(trom_num++), m1[0] * sq_width, m1[1] * sq_height);
                graphics.setColor(Color.BLACK);
                // Add 1 to x to go right
                // Q4
                m2 = new int[]{oppositeInnerCorner[0] + 1 + offset_x, oppositeInnerCorner[1] + offset_y};
                graphics.fillRect(m2[0] * sq_width, m2[1] * sq_height, sq_width,
                        sq_height);
                // Subtract 1 from y to go up
                // Q2
                m3 = new int[]{oppositeInnerCorner[0] + offset_x, oppositeInnerCorner[1] - 1 + offset_y};
                graphics.fillRect(m3[0] * sq_width, m3[1] * sq_height, sq_width,
                        sq_height);
                if (n != 2) {
                    drawTrominoes(graphics, deficiency, n / 2, offset_x + n / 2, offset_y);
                    drawTrominoes(graphics, m1, n / 2, offset_x, offset_y + n / 2);
                    drawTrominoes(graphics, m2, n / 2, offset_x + n / 2, offset_y + n / 2);
                    drawTrominoes(graphics, m3, n / 2, offset_x, offset_y);
                }
                break;
            case 2:
                // Have to offset 1 left for some reason, yay graphics
                oppositeInnerCorner = new int[]{(n / 2), (n / 2)};
                // Q4
                m1 = new int[]{oppositeInnerCorner[0] + offset_x, oppositeInnerCorner[1] + offset_y};
                graphics.fillRect(m1[0] * sq_width, m1[1] * sq_height, sq_width,
                        sq_height);
                graphics.setColor(Color.WHITE);
                graphics.drawString(""+(trom_num++), m1[0] * sq_width, m1[1] * sq_height);
                graphics.setColor(Color.BLACK);
                // Sub 1 to x to go Left
                // Q3
                m2 = new int[]{oppositeInnerCorner[0] - 1 + offset_x, oppositeInnerCorner[1] + offset_y};
                graphics.fillRect(m2[0] * sq_width, m2[1] * sq_height, sq_width,
                        sq_height);
                // Subtract 1 from y to go up
                // Q1
                m3 = new int[]{oppositeInnerCorner[0] + offset_x, oppositeInnerCorner[1] - 1 + offset_y};
                graphics.fillRect(m3[0] * sq_width, m3[1] * sq_height, sq_width,
                        sq_height);
                if (n != 2) {
                    drawTrominoes(graphics, deficiency, n / 2, offset_x, offset_y);
                    drawTrominoes(graphics, m1, n / 2, offset_x + n / 2, offset_y + n / 2);
                    drawTrominoes(graphics, m2, n / 2, offset_x, offset_y + n / 2);
                    drawTrominoes(graphics, m3, n / 2, offset_x + n / 2, offset_y);
                }
                break;
            case 3:
                oppositeInnerCorner = new int[]{(n / 2), (n / 2) - 1};
                // Q1
                m1 = new int[]{oppositeInnerCorner[0] + offset_x, oppositeInnerCorner[1] + offset_y};
                graphics.fillRect(m1[0] * sq_width, m1[1] * sq_height, sq_width,
                        sq_height);
                graphics.setColor(Color.WHITE);
                graphics.drawString(""+(trom_num++), m1[0] * sq_width, m1[1] * sq_height);
                graphics.setColor(Color.BLACK);
                // Sub 1 to x to go Left
                // Q2
                m2 = new int[]{oppositeInnerCorner[0] - 1 + offset_x, oppositeInnerCorner[1] + offset_y};
                graphics.fillRect(m2[0] * sq_width, m2[1] * sq_height, sq_width,
                        sq_height);
                // Add 1 from y to go down
                // Q4
                m3 = new int[]{oppositeInnerCorner[0] + offset_x, oppositeInnerCorner[1] + 1 + offset_y};
                graphics.fillRect(m3[0] * sq_width, m3[1] * sq_height, sq_width,
                        sq_height);
                if (n != 2) {
                    drawTrominoes(graphics, deficiency, n / 2, offset_x, offset_y + n / 2);
                    drawTrominoes(graphics, m1, n / 2, offset_x + n / 2, offset_y);
                    drawTrominoes(graphics, m2, n / 2, offset_x, offset_y);
                    drawTrominoes(graphics, m3, n / 2, offset_x + n / 2, offset_y + n / 2);
                }
                break;
            case 4:
                oppositeInnerCorner = new int[]{(n / 2) - 1, (n / 2) - 1};
                // Q2
                m1 = new int[]{oppositeInnerCorner[0] + offset_x, oppositeInnerCorner[1] + offset_y};
                graphics.fillRect(m1[0] * sq_width, m1[1] * sq_height, sq_width,
                        sq_height);
                graphics.setColor(Color.WHITE);
                graphics.drawString(""+(trom_num++), m1[0] * sq_width, m1[1] * sq_height);
                graphics.setColor(Color.BLACK);
                // Add 1 to x to go right
                // Q1
                m2 = new int[]{oppositeInnerCorner[0] + 1 + offset_x, oppositeInnerCorner[1] + offset_y};
                graphics.fillRect(m2[0] * sq_width, m2[1] * sq_height, sq_width,
                        sq_height);
                // Add 1 from y to go down
                // Q3
                m3 = new int[]{oppositeInnerCorner[0] + offset_x, oppositeInnerCorner[1] + 1 + offset_y};
                graphics.fillRect(m3[0] * sq_width, m3[1] * sq_height, sq_width,
                        sq_height);
                if (n != 2) {
                    drawTrominoes(graphics, deficiency, n / 2, offset_x + n / 2, offset_y + n / 2);
                    drawTrominoes(graphics, m1, n / 2, offset_x, offset_y);
                    drawTrominoes(graphics, m2, n / 2, offset_x + n / 2, offset_y);
                    drawTrominoes(graphics, m3, n / 2, offset_x, offset_y + n / 2);
                }
                break;
        }
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
