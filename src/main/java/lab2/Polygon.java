package lab2;

import lab1.IntersectionResult;
import lab1.Line;
import lab1.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlad on 07.03.16.
 */
public class Polygon {
    private List<Point> vertices;
    private List<Point> sortedVertices;
    private double area = -1;


    public Polygon(){
    }

    public Polygon(List<Point> vertices){
        this.vertices = vertices;
        this.sortedVertices = vertices;
    }

    public void setVertices(ArrayList<Point> vertices) {
        this.vertices  = vertices;
        area = area(vertices);
    }

    public double getArea() {
        return this.area;
    }

    public boolean inPolygon(Point p) {

        Line l = new Line(p, new Point (Double.MIN_VALUE / 2, p.getY()));
        Point prev = vertices.get(vertices.size() - 1);
        Point prevIntersectionPoint = null;

        int countOfIntersectedEdges = 0;
        for (Point current : vertices) {
            Line edge = new Line(prev, current);
            IntersectionResult intersectionResult = l.segmentsIntersection(edge);
            if (intersectionResult.getImposed()){
                if (prevIntersectionPoint != null && prevIntersectionPoint.compareX(p) < 0) {
                    prevIntersectionPoint = null;
                } else {
                    countOfIntersectedEdges++;
                }
            } else {
                if (intersectionResult.getIntersect() && intersectionResult.getPoint().compareX(p) < 0) {
                    countOfIntersectedEdges++;
                    prevIntersectionPoint = intersectionResult.getPoint();
                }
            }
            prev = current;
        }

        return countOfIntersectedEdges % 2 == 1;
    }

    public double area(ArrayList<Point> vertices) {
        double area = 0;
        double xCur;
        double yCur;
        double xNext;
        double yNext;
        for (int i = 0; i < vertices.size() - 1; i++) {
            xCur = vertices.get(i).getX();
            yCur = vertices.get(i).getY();
            if (i != vertices.size() - 1) {
                xNext = vertices.get(i + 1).getX();
                yNext = vertices.get(i + 1).getY();
            } else {
                xNext = vertices.get(0).getX();
                yNext = vertices.get(0).getY();
            }

            area += Math.abs(xCur * yNext - xNext * yCur);
        }
        return area / 2;


//        ArrayList<Double> X = new ArrayList<>();
//        ArrayList<Double> Y = new ArrayList<>();
//        for (Point p : vertices) {
//            X.add(p.getX());
//            Y.add(p.getY());
//        }
//        X.add(vertices.get(0).getX());
//        Y.add(vertices.get(0).getY());
//        double s1 = 0, s2 = 0;
//        for(int i = 0; i < X.size() - 1; i++) {
//            s1 += X.get(i) * Y.get(i + 1);
//        }
//        for(int i = 0; i < X.size() - 1; i++) {
//            s2 += Y.get(i) * X.get(i + 1);
//        }
//        return Math.abs((s1 - s2) / 2.);
    }

}
