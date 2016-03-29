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


    public Polygon(){
    }

    public Polygon(List<Point> vertices){
        this.vertices = vertices;
        this.sortedVertices = vertices;
    }

    public void setVertices(ArrayList<Point> vertices) {
        this.vertices  = vertices;
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

}
