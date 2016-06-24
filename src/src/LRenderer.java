package src;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;

public class LRenderer extends JComponent {
    private int n;
    private int[] init_deficiency;
    private int sq_width;
    private int sq_height;
    private int order;

    LRenderer(int n, int[] init_deficiency) {
        this.n = n;
        this.init_deficiency = init_deficiency;
        this.order = 1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.sq_height = this.sq_width = this.getWidth() / n;
        Graphics2D graphics = (Graphics2D) g;

        drawInitGrid(graphics);
        drawQuadrant(graphics, this.init_deficiency, 0, new int[]{0, 0}, this.n, Color.PINK);
    }

    private void drawQuadrant(Graphics2D graphics, int[] deficiency, int step, int[] topCorner, int n, Color c) {
        int quadrant = determineQuadrant(deficiency, n / (int) Math.pow(2, step));
        if ((n / (int) Math.pow(2, step) <= 2)) {
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
        } else {
            switch (quadrant) {
                case 1:
                    drawTrominoes(graphics, 1, n, step, topCorner, Color.PINK);
                    drawQuadrant(graphics, new int[]{deficiency[0] - n/2, deficiency[1]}, step + 1, new int[]{n / 2, 0}, n, Color.CYAN);
                    break;
                case 2:
                    drawTrominoes(graphics, 2, n, step, topCorner, Color.PINK);
                    drawQuadrant(graphics, deficiency, step + 1, topCorner, n, Color.CYAN);
                    break;
                case 3:
                    drawTrominoes(graphics, 3, n, step, topCorner, Color.PINK);
                    drawQuadrant(graphics, new int[]{deficiency[0], deficiency[1] - n/2}, step + 1, new int[]{0, n / 2}, n, Color.CYAN);
                    break;
                case 4:
                    drawTrominoes(graphics, 4, n, step, topCorner, Color.PINK);
                    drawQuadrant(graphics, new int[]{deficiency[0] - n/2, deficiency[1] - n/2}, step + 1, new int[]{n / 2, n / 2}, n, Color.CYAN);
                    break;
            }
        }
    }

    private void drawTrominoes(Graphics2D graphics, int deficiency, int n, int step, int[] topCorner, Color c) {
        System.out.println(n / (int) Math.pow(2, step));
        if ((n / (int) Math.pow(2, step)) <= 2) {
            graphics.setColor(c);
            graphics.setFont(new Font("TimesRoman", Font.PLAIN, 40));
            switch (deficiency) {
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
        switch (deficiency) {
            case 1:
                drawTrominoes(graphics, 4, n, step + 1, topCorner, Color.BLUE);
                drawTrominoes(graphics, 1, n, step + 1, new int[]{0, (int) Math.pow(2, step)}, Color.GREEN);
                drawTrominoes(graphics, 2, n, step + 1, new int[]{n / (int) Math.pow(2, step), n / (int) Math.pow(2, step)}, Color.ORANGE);
                drawTrominoes(graphics, 1, n, step + 1, new int[]{n / (int) Math.pow(4, step), n / (int) Math.pow(4, step)}, Color.RED);
                break;
            case 2:
                drawTrominoes(graphics, 3, n, step + 1, new int[]{n / (int) Math.pow(2, step), 0}, Color.BLUE);
                drawTrominoes(graphics, 1, n, step + 1, new int[]{0, n / (int) Math.pow(2, step)}, Color.GREEN);
                drawTrominoes(graphics, 2, n, step + 1, new int[]{n / (int) Math.pow(2, step), n / (int) Math.pow(2, step)}, Color.ORANGE);
                drawTrominoes(graphics, 2, n, step + 1, new int[]{n / (int) Math.pow(4, step), n / (int) Math.pow(4, step)}, Color.RED);
                break;
            case 3:
                drawTrominoes(graphics, 4, n, step + 1, topCorner, Color.BLUE);
                drawTrominoes(graphics, 3, n, step + 1, new int[]{n / (int) Math.pow(2, step), 0}, Color.GREEN);
                drawTrominoes(graphics, 2, n, step + 1, new int[]{n / (int) Math.pow(2, step), n / (int) Math.pow(2, step)}, Color.ORANGE);
                drawTrominoes(graphics, 3, n, step + 1, new int[]{n / (int) Math.pow(4, step), n / (int) Math.pow(4, step)}, Color.RED);
                break;
            case 4:
                drawTrominoes(graphics, 4, n, step + 1, topCorner, Color.BLUE);
                drawTrominoes(graphics, 1, n, step + 1, new int[]{0, n / (int) Math.pow(2, step)}, Color.GREEN);
                drawTrominoes(graphics, 3, n, step + 1, new int[]{n / (int) Math.pow(2, step), 0}, Color.ORANGE);
                drawTrominoes(graphics, 4, n, step + 1, new int[]{n / (int) Math.pow(4, step), n / (int) Math.pow(4, step)}, Color.RED);
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