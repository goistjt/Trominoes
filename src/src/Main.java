package src;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main {

    private static final Dimension SIZE = new Dimension(800, 800);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Welcome to Trominoes. Please enter a value 0 <= k <= 7 for the size of the grid = 2^k: ");
        int k = 0;
        try {
            k = scanner.nextInt();
            if (k < 0 || k > 7) {
                throw new Exception();
            }
        } catch (Exception e) {
            new Exception("The value entered must be an integer between 0, 7 (inclusive)").printStackTrace();
            return;
        }
        System.out.print("Which algorithm would you like to use (L-Shape or Quadrants)? (L/Q): ");
        String algo = scanner.next();
        JFrame frame = new JFrame();
        frame.setSize(SIZE);
        switch (algo) {
            case "Q":
                frame.setTitle("Quadrant Recursion");
                frame.add(new QuadrantRenderer(Math.pow(2, k)));
                break;
            case "L":
                frame.setTitle("L-Shape Recursion");
                frame.add(new LRenderer(Math.pow(2, k)));
                break;
            default:
                System.out.println("Please provide a valid option next time.");
                return;
        }
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
