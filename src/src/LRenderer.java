package src;

import javax.swing.*;
import java.awt.*;

public class LRenderer extends JComponent {
    private int n;
    private int[] init_deficiency;
    private int[] topLeft;
    private int sq_width;
    private int sq_height;
    private int order;

    LRenderer(int n, int[] init_deficiency) {
        this.n = n;
        this.init_deficiency = init_deficiency;
        this.topLeft = new int[]{0, 0};
        this.order = 1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.sq_height = this.sq_width = this.getWidth() / n;
        Graphics2D graphics = (Graphics2D) g;

        drawInitGrid(graphics);
        drawTrominoes(graphics, this.init_deficiency, this.n, 0, this.topLeft, Color.PINK);
    }

    private void drawTrominoes(Graphics2D graphics, int[] deficiency, int n, int step, int[] topCorner, Color c) {
        int quadrant = determineQuadrant(deficiency, n);
        if ((n / Math.pow(2, step)) == 2) {
            graphics.setColor(c);
            graphics.setFont(new Font("TimesRoman", Font.PLAIN, 40));
            switch (quadrant) {
                case 1:
                    graphics.fillRect(topCorner[0] * sq_width, topCorner[1] * sq_height, sq_width,
                            sq_height);
                    // Add 1 to y to go down
                    graphics.fillRect(topCorner[0] * sq_width, (topCorner[1] + 1) * sq_height, sq_width,
                            sq_height);
                    // add 1 to x to go right & 1 to y to go down
                    graphics.fillRect((topCorner[0] + 1) * sq_width, (topCorner[1] + 1) * sq_height, sq_width,
                            sq_height);
                    graphics.setColor(Color.BLACK);
                    graphics.drawString(this.order + "", topCorner[0] * sq_width + sq_width / 2, (topCorner[1] + 1) * sq_height + sq_height / 2);
                    this.order++;
                    break;
                case 2:
                    //add 1 to x to go right
                    graphics.fillRect((topCorner[0] + 1) * sq_width, topCorner[1] * sq_height, sq_width,
                            sq_height);
                    // add 1 tp x to go right and 1 to y to go down
                    graphics.fillRect((topCorner[0] + 1) * sq_width, (topCorner[1] + 1) * sq_height, sq_width,
                            sq_height);
                    // add 1 to y to go down
                    graphics.fillRect(topCorner[0] * sq_width, (topCorner[1] + 1) * sq_height, sq_width,
                            sq_height);
                    graphics.setColor(Color.BLACK);
                    graphics.drawString(this.order + "", (topCorner[0] + 1) * sq_width + sq_width / 2, (topCorner[1] + 1) * sq_height + sq_height / 2);
                    this.order++;
                    break;
                case 3:
                    // fill in top left
                    graphics.fillRect(topCorner[0] * sq_width, topCorner[1] * sq_height, sq_width,
                            sq_height);
                    // add 1 to x to go right
                    graphics.fillRect((topCorner[0] + 1) * sq_width, topCorner[1] * sq_height, sq_width,
                            sq_height);
                    // Add 1 to x to go right and 1 to y to go down
                    graphics.fillRect((topCorner[0] + 1) * sq_width, (topCorner[1] + 1) * sq_height, sq_width,
                            sq_height);
                    graphics.setColor(Color.BLACK);
                    graphics.drawString(this.order + "", (topCorner[0] + 1) * sq_width + sq_width / 2, topCorner[1] * sq_height + sq_height / 2);
                    this.order++;
                    break;
                case 4:
                    // fill in top left
                    graphics.fillRect(topCorner[0] * sq_width, topCorner[1] * sq_height, sq_width,
                            sq_height);
                    // Add 1 to x to go right
                    graphics.fillRect((topCorner[0] + 1) * sq_width, topCorner[1] * sq_height, sq_width,
                            sq_height);
                    // Add 1 to y to go down
                    graphics.fillRect(topCorner[0] * sq_width, (topCorner[1] + 1) * sq_height, sq_width,
                            sq_height);
                    graphics.setColor(Color.BLACK);
                    graphics.drawString(this.order + "", topCorner[0] * sq_width + sq_width / 2, topCorner[1] * sq_height + sq_height / 2);
                    this.order++;
                    break;
            }
            return;
        }
        int[] bottomLeft;
        int[] bottomRight;
        int[] middle;
        int[] topLeft;
        int[] topRight;
        step++;
        switch (quadrant) {
            case 1:
                bottomLeft = new int[]{0, n / (int) Math.pow(2, step)};
                bottomRight = new int[]{n / (int) Math.pow(2, step), n / (int) Math.pow(2, step)};
                middle = new int[]{n / (int) Math.pow(4, step), n / (int) Math.pow(4, step)};
                topLeft = topCorner;

//                drawTrominoes(graphics, 4, n, step, topLeft, Color.BLUE);
//                drawTrominoes(graphics, 1, n, step, bottomLeft, Color.GREEN);
//                drawTrominoes(graphics, 2, n, step, bottomRight, Color.ORANGE);
//                drawTrominoes(graphics, 1, n, step, middle, Color.RED);
                break;
            case 2:
                bottomLeft = new int[]{0, n / (int) Math.pow(2, step)};
                bottomRight = new int[]{n / (int) Math.pow(2, step), n / (int) Math.pow(2, step)};
                middle = new int[]{n / (int) Math.pow(4, step), n / (int) Math.pow(4, step)};
                topRight = new int[]{n / (int) Math.pow(2, step), 0};

//                drawTrominoes(graphics, 3, n, step, topRight, Color.BLUE);
//                drawTrominoes(graphics, 1, n, step, bottomLeft, Color.GREEN);
//                drawTrominoes(graphics, 2, n, step, bottomRight, Color.ORANGE);
//                drawTrominoes(graphics, 2, n, step, middle, Color.RED);
                break;
            case 3:
                bottomRight = new int[]{n / (int) Math.pow(2, step), n / (int) Math.pow(2, step)};
                middle = new int[]{n / (int) Math.pow(4, step), n / (int) Math.pow(4, step)};
                topLeft = topCorner;
                topRight = new int[]{n / (int) Math.pow(2, step), 0};

//                drawTrominoes(graphics, 4, n, step, topLeft, Color.BLUE);
//                drawTrominoes(graphics, 3, n, step, topRight, Color.GREEN);
//                drawTrominoes(graphics, 2, n, step, bottomRight, Color.ORANGE);
//                drawTrominoes(graphics, 3, n, step, middle, Color.RED);
                break;
            case 4:
                bottomLeft = new int[]{0, n / (int) Math.pow(2, step)};
                middle = new int[]{n / (int) Math.pow(4, step), n / (int) Math.pow(4, step)};
                topLeft = topCorner;
                topRight = new int[]{n / (int) Math.pow(2, step), 0};

//                drawTrominoes(graphics, 4, n, step, topLeft, Color.BLUE);
//                drawTrominoes(graphics, 1, n, step, bottomLeft, Color.GREEN);
//                drawTrominoes(graphics, 3, n, step, topRight, Color.ORANGE);
//                drawTrominoes(graphics, 4, n, step, middle, Color.RED);
                break;
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
}