package lab3;

import lab1.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by vlad on 05.04.16.
 */
public class Tester extends JPanel{
    private JTextField pointNumberField;
    private JButton OKButton;
    private JPanel board;
    private JPanel mainPanel;
    private JLabel executingTimeLabel;
    private int pointNumber;
    private ConvexHull convexHull;
    private ArrayList<Point> pointSet;

    private final int xRange = 500;
    private final int yRange = 500;

    public Tester() {
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pointNumber = Integer.parseInt(pointNumberField.getText());

                pointSet = generatePoints(pointNumber);
                drawPoints(pointSet);

                long start = System.currentTimeMillis();
                ArrayList<Point> ch = convexHull.jarvis(pointSet);
                long finish = System.currentTimeMillis();
                executingTimeLabel.setText(Long.toString(finish - start) + "ms");

                drawConvexHull(ch);
            }
        });
    }

    private ArrayList<Point> generatePoints(int number) {
        ArrayList<Point> pointSet = new ArrayList<>();
        Random randomGenerator = new Random();
        for (int i = 0; i < number; i++) {
            pointSet.add(new Point(randomGenerator.nextInt(xRange), randomGenerator.nextInt(yRange)));
        }

        return pointSet;
    }

    private void drawPoints(ArrayList<Point> pointSet) {
        Graphics g = board.getGraphics();
        g.clearRect(0, 0, 500, 500);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setPaint(Color.red);

        for (Point point : pointSet) {
            graphics2D.drawOval((int) point.getX() - 1, (int) point.getY() - 1, 2, 2);
        }
    }

    private void drawConvexHull(ArrayList<Point> convexHull) {
        Graphics g = board.getGraphics();
        Graphics2D graphics2D = (Graphics2D) g;

        Point prev = convexHull.get(convexHull.size() - 1);
        for (Point cur : convexHull) {
            graphics2D.drawLine((int) prev.getX(), (int) prev.getY(), (int) cur.getX(), (int) cur.getY());
            prev = cur;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tester");
        frame.setContentPane(new Tester().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
    }
}
