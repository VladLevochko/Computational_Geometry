package lab4;

import lab1.Line;
import lab1.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Created by vlad on 10.05.16.
 */
public class DisplayTest {
    private JPanel mainPanel;
    private JPanel board;

    public DisplayTest() {
        board.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                draw();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void draw() {
        Graphics g = board.getGraphics();
        Graphics2D graphics2D = (Graphics2D) g;

        ArrayList<Point> vertices = new ArrayList<>();

        vertices.add(new Point(274, 405));
        vertices.add(new Point(101, 399));
        vertices.add(new Point(55, 141));
        vertices.add(new Point(384, 72));
        vertices.add(new Point(250, 232));

        Triangulation triangulation = new Triangulation(25);
        ArrayList<Line> triangles = triangulation.triangulate(vertices, graphics2D);
        drawTriangles(triangles);
    }

    private void drawTriangles(ArrayList<Line> edgesList) {
        Graphics g = board.getGraphics();
        Graphics2D graphics2D = (Graphics2D) g;
        for (Line edge :
                edgesList) {
            Point a = edge.getP1();
            Point b = edge.getP2();
            graphics2D.drawLine((int) a.getX(), (int) a.getY(), (int) b.getX(), (int) b.getY());
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DisplayTest");
        frame.setContentPane(new DisplayTest().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
