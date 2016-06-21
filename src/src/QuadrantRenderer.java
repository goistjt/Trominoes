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
