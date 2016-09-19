package lab4;

import lab1.IntersectionResult;
import lab1.Line;
import lab1.Point;
import lab2.Polygon;
import lab3.ConvexHull;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by vlad on 25.04.16.
 *
 * lab with lots of bugs...
 */
public class Triangulation {

    private double patternSize = 2;
    private double patternSideLength = 5;
    private double patternAngle = 75;
    private ArrayList<Point> polygon;
    private Graphics2D g2d = null;

    public Triangulation(int length) {
        patternSideLength = length;
        patternSize = 2 * patternSideLength * patternSideLength * (1 - Math.cos(Math.PI / 3));
    }

    public ArrayList<Line> triangulate(ArrayList<Point> pointsList, Graphics2D g2d) {

        this.g2d = g2d;
        this.polygon = setCounterclockwiseOrder(pointsList);



        ArrayList<Line> trianglesEdges = new ArrayList<>();

        Polygon polygonObject = new Polygon();

        int i = 0;
        while (polygon.size() >= 3) {
            System.out.print(++i + ",");
            double polygonArea = polygonObject.area(polygon);
            System.out.println(polygonArea);

            //get cur point and his number in list
            Point curPoint = getVertexWithMinimalInternalAngle(polygon);
            int curPointPosition = polygon.indexOf(curPoint);

            //get next and previous points in list
            Point prevPoint, nextPoint;
            if (curPointPosition == 0)
                prevPoint = polygon.get(polygon.size() - 1);
            else
                prevPoint = polygon.get(curPointPosition - 1);
            if (curPointPosition == polygon.size() - 1)
                nextPoint = polygon.get(0);
            else
                nextPoint = polygon.get(curPointPosition + 1);

            //get edges from current point
            Line prevEdge = new Line(curPoint, prevPoint);
            Line nextEdge = new Line(curPoint, nextPoint);

            double angle = nextEdge.angleBetween(prevEdge);
            if (angle > patternAngle) {
                //define new angles size
                //define new points (3+)
                //delete cur point from list
                //add new points to list

                double newAngle = angle / 2;

                //define new points
                Point pointOnPrevEdge = getPointOnLine(patternSideLength, prevEdge);
                Point pointOnNextEdge = getPointOnLine(patternSideLength, nextEdge);
                Point pointBetween = getPointOnBisect(curPoint, pointOnNextEdge, newAngle);


//                if (pointBetween == null)
//                    addOneTriangle(curPointPosition, curPoint, pointOnPrevEdge, pointOnNextEdge, trianglesEdges);
//                else
                    addTwoTrirangles(curPointPosition, curPoint, pointOnPrevEdge, pointOnNextEdge, pointBetween, trianglesEdges);
            } else {
                //define new points
                Point pointOnPrevEdge = getPointOnLine(patternSideLength, prevEdge);
                Point pointOnNextEdge = getPointOnLine(patternSideLength, nextEdge);
                addOneTriangle(curPointPosition, curPoint, pointOnPrevEdge, pointOnNextEdge, trianglesEdges);
            }
        }

        return trianglesEdges;
    }

    public void addOneTriangle(int curPointPosition, Point curPoint, Point pointOnPrevEdge, Point pointOnNextEdge, ArrayList<Line> trianglesEdges) {
        //add edges of triangles to answer
        trianglesEdges.add(new Line(curPoint, pointOnPrevEdge));
        trianglesEdges.add(new Line(curPoint, pointOnNextEdge));
        //remove current point and add new points to polygon
        polygon.remove(curPoint);
        if (polygon.indexOf(pointOnNextEdge) == -1)
            polygon.add(curPointPosition, pointOnNextEdge);
        if (polygon.indexOf(pointOnPrevEdge) == -1)
            polygon.add(curPointPosition, pointOnPrevEdge);
    }

    public void addTwoTrirangles(int curPointPosition, Point curPoint, Point pointOnPrevEdge, Point pointOnNextEdge, Point pointBetween, ArrayList<Line> trianglesEdges) {
        //add edges of triangles to answer
        trianglesEdges.add(new Line(curPoint, pointOnPrevEdge));
        trianglesEdges.add(new Line(curPoint, pointOnNextEdge));
        trianglesEdges.add(new Line(curPoint, pointBetween));

        //display edges

//        g2d.setColor(Color.BLUE);
//        g2d.drawLine((int) curPoint.getX(), (int) curPoint.getY(), (int) pointOnPrevEdge.getX(), (int) pointOnPrevEdge.getY());
//        g2d.setColor(Color.GREEN);
//        g2d.drawLine((int) curPoint.getX(), (int) curPoint.getY(), (int) pointOnNextEdge.getX(), (int) pointOnNextEdge.getY());
//        g2d.setColor(Color.RED);
//        g2d.drawLine((int) curPoint.getX(), (int) curPoint.getY(), (int) pointBetween.getX(), (int) pointBetween.getY());
//                if (!(new Polygon(temp)).inPolygon(pointBetween)) {
//                    g2d.setColor(Color.YELLOW);
//                    g2d.drawLine((int) curPoint.getX(), (int) curPoint.getY(), (int) pointBetween.getX(), (int) pointBetween.getY());
//                    g2d.setColor(Color.RED);
//                    g2d.drawLine((int) pointBetween.getX(), (int) pointBetween.getY(), (int) pointBetween.getX(), (int) pointBetween.getY());
//                    System.out.println(temp);
//                    return null;
//                }

        polygon.remove(curPoint);
        if (polygon.indexOf(pointOnNextEdge) == -1)
            polygon.add(curPointPosition, pointOnNextEdge);
        if (polygon.indexOf(pointBetween) == -1)
            polygon.add(curPointPosition, pointBetween);
        if (polygon.indexOf(pointOnPrevEdge) == -1)
            polygon.add(curPointPosition, pointOnPrevEdge);
    }

    public Point getPointOnLine(double length, Line line) {

        if (line.getLength() < length)
            return line.getP2();

        double k = line.getK();
        double b = line.getB();
        double sL = length * length;
        double xC = line.getP1().getX();
        double yC = line.getP1().getY();

        //x^2 * A + x * B + C = 0
        double A = 1 + k * k;
        double B = 2. * k * (b - yC) - 2. * xC;
        double C = xC * xC + (b - yC) * (b - yC) - sL;

        //get discriminant
        double D = B * B - 4 * A * C;

        //get x roots
        double x1 = (-B - Math.sqrt(D)) / (2 * A);
        double x2 = (-B + Math.sqrt(D)) / (2 * A);

        double y1 = k * x1 + b;
        double y2 = k * x2 + b;

        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);

        //define distance from p1 and p2 to second point of segment
        double l1 = new Line(p1, line.getP2()).getLength();
        double l2 = new Line(p2, line.getP2()).getLength();


        if (l1 < l2) {
            return p1;
        } else {
            return p2;
        }
    }

    public Point getPointOnBisect(Point centerPoint, Point startPoint, double angle) {

        Point point = getPointRotation(centerPoint, startPoint, angle);

        Point calculatedPoint = getPointOnLine(Math.abs(1.2 - 1.4 * patternSideLength), new Line(centerPoint, point));
        Point intersectionPoint = intersectionWithPolygon(new Line(calculatedPoint, centerPoint));

        return calculatedPoint;


//        if (intersectionPoint == null) {
//            return calculatedPoint;
//        } else {
//            return null;
//        }
    }

    public Point getPointRotation(Point centerPoint, Point startPoint, double angle) {
        //coordinates of start point relative center point
        Point nStartPoint = new Point(startPoint.getX() - centerPoint.getX(), startPoint.getY() - centerPoint.getY());
        //polar angle of result point
        double nPointPolarAngle = nStartPoint.getAlpha() + angle;
        //coordinates of result point
        double relativeX = new Line(centerPoint, startPoint).getLength() * Math.cos(Math.toRadians(nPointPolarAngle));
        double relativeY = new Line(centerPoint, startPoint).getLength() * Math.sin(Math.toRadians(nPointPolarAngle));

        return new Point(relativeX + centerPoint.getX(), relativeY + centerPoint.getY());
    }

    public Point intersectionWithPolygon(Line line) {

        double distance = Integer.MAX_VALUE;
        IntersectionResult intersectionResult = null;
        Point intersectionPoint = null;
        Point prevPoint = polygon.get(polygon.size() - 1);
        for (int i = 0; i < polygon.size(); i++) {
            Line edge = new Line(prevPoint, polygon.get(i));
            intersectionResult = line.segmentsIntersection(edge);
            if (intersectionResult.getPoint() != null) {
                if (new Line(line.getP1(), intersectionResult.getPoint()).getLength() < distance) {
                    distance = new Line(line.getP1(), intersectionResult.getPoint()).getLength();
                    intersectionPoint = intersectionResult.getPoint();
                }
            }
        }

        return intersectionPoint;
    }

    public Point getVertexWithMinimalInternalAngle(ArrayList<Point> pointList) {
        Point prev = pointList.get(pointList.size() - 1);
        Point next = pointList.get(1);
        Point ans = pointList.get(0);
        double minimalAngle = 360;
        for (int i = 0; i < pointList.size(); i++) {
            Point cur = pointList.get(i);
            Line a = new Line(cur, prev);
            Line b = new Line(cur, next);
            double angleBetween = b.angleBetween(a);
            if (angleBetween < minimalAngle) {
                ans = cur;
                minimalAngle = angleBetween;
            }

            //
            prev = cur;
            if (i == pointList.size() - 2)
                next = pointList.get(0);
            else
                if (i < pointList.size() - 1)
                    next = pointList.get(i + 2);
        }

        return ans;
    }

    public ArrayList<Point> setCounterclockwiseOrder(ArrayList<Point> list) {
        //just make right input of points :)
        //in clockwise order...
        //i know, you confused, but just do it

        return list;
    }

    private static Point getLexicographicallyMinimal(ArrayList<Point> pointSet) {
        if (pointSet.isEmpty())
            return null;

        Point min = pointSet.get(0);
        for (Point cur : pointSet) {
            if (cur.getY() < min.getY() || cur.getY() == min.getY()  && cur.getX() < min.getX())
                min = cur;
        }

        return min;
    }

    public static void main(String args[]) {
//        Triangulation triangulation = new Triangulation(5);
//        ArrayList<Point> polygon = new ArrayList<>();
//        polygon.add(new Point(0, 2));
//        polygon.add(new Point(3, 20));
//        polygon.add(new Point(17, 23));
//        polygon.add(new Point(23, 16));
//        polygon.add(new Point(15, 4));
//        ArrayList<Line> res = triangulation.triangulate(polygon);
//        for (Line l : res) {
//            System.out.println(l);
//        }
    }
}
