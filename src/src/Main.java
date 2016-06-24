package src;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main {

    private static final Dimension SIZE = new Dimension(1020, 1020);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Welcome to Trominoes. Please enter a value 1 <= k <= 7 for the size of the grid = 2^k: ");
        int k = 0;
        try {
            k = scanner.nextInt();
            if (k < 1 || k > 7) {
                throw new Exception();
            }
        } catch (Exception e) {
            new Exception("The value entered must be an integer between 1, 7 (inclusive)").printStackTrace();
            return;
        }
        int n = (int) Math.pow(2,k);
        int[] init_deficiency = {0, 0};
        try {
            System.out.print("Which row would you like the deficiency in (0-indexed)?: ");
            init_deficiency[1] = scanner.nextInt();
            System.out.print("Which column would you like the deficiency in (0-indexed)?: ");
            init_deficiency[0] = scanner.nextInt();
            if (init_deficiency[0] < 0 || init_deficiency[0] > n - 1 || init_deficiency[1] < 0 || init_deficiency[1] > n - 1) {
                throw new Exception();
            }
        } catch (Exception e) {
            new Exception("The row and column must be integer within [0, (2^k)-1]").printStackTrace();
            return;
        }
        System.out.print("Which algorithm would you like to use (L-Shape or Quadrants)? (L/Q): ");
        String algo = scanner.next();
        JFrame frame = new JFrame();
        frame.setSize(SIZE);
        switch (algo) {
            case "Q":
                frame.setTitle("Quadrant Recursion");
                frame.add(new QuadrantRenderer(n, init_deficiency));
                break;
            case "L":
                frame.setTitle("L-Shape Recursion");
                frame.add(new LRenderer(n, init_deficiency));
                break;
            default:
                System.out.println("Please provide a valid option next time.");
                return;
        }
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
