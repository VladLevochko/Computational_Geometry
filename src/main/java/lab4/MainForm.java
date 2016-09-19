package lab4;

import lab1.Line;
import lab1.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Created by vlad on 16.04.16.
 */
public class MainForm {

    private JPanel viewPanel;
    private JPanel board;
    private JButton startRecordingButton;
    private boolean recording = false;
    private ArrayList<Point> pointList = new ArrayList<>();
    private Point lastPoint = null;
    private Point firstPoint = null;
    private final int delta = 8;

    private Triangulation triangulation = null;

    public MainForm () {

        triangulation = new Triangulation(50);

        startRecordingButton.addActionListener(action -> {
            if (!recording) {
                recording = true;
                board.updateUI();
                startRecordingButton.setText("end recording");
                lastPoint = null;
                firstPoint = null;
            } else {
                stopRecording();
            }
        });

        board.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (recording) {
                    if (lastPoint != null) {
                        Graphics g = board.getGraphics();
                        Graphics2D graphics2D = (Graphics2D) g;

                        if (Math.abs(e.getX() - firstPoint.getX()) < delta &&
                            Math.abs(e.getY() - firstPoint.getY()) < delta) {
                            graphics2D.drawLine((int) lastPoint.getX(), (int) lastPoint.getY(),
                                                (int) firstPoint.getX(), (int) firstPoint.getY());
                            stopRecording();
                        } else {
                            graphics2D.drawLine((int) lastPoint.getX(), (int) lastPoint.getY(), e.getX(), e.getY());
                        }
                    }
                    addPoint(new Point(e.getX(), e.getY()));
                }
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

    private void addPoint(Point p) {
        pointList.add(p);
        if (firstPoint == null)
            firstPoint = p;
        lastPoint = p;
    }

    private void stopRecording() {
        for (Point p :
                pointList) {
            System.out.print(p.toString());
        }
        recording = false;
        startRecordingButton.setText("start recording");

        Graphics g = board.getGraphics();
        Graphics2D graphics2D = (Graphics2D) g;

        ArrayList<Line> triangles = triangulation.triangulate(pointList, graphics2D);
        pointList.clear();
        lastPoint = null;
        firstPoint = null;

        System.out.println(triangles);

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
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(new MainForm().viewPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        frame.pack();
        frame.setVisible(true);
    }
}
