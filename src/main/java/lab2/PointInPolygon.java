package lab2;

import lab1.IntersectionResult;
import lab1.Line;
import lab1.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlad on 28.02.16.
 */
public class PointInPolygon {

    public static boolean check(List<Point> vertices, Point point){
        int count = 0;
        Line infLine = new Line(point.getX(), point.getY(), point.getX() + 1, point.getY());

        Point prev = vertices.get(vertices.size() - 1);
        for(Point p : vertices){
            if (prev != p){
                Line edge = new Line(prev, p);
                IntersectionResult ir = infLine.lineIntersects(edge);

                if (ir.getIntersects() && ir.getPoint().inYProection(edge) && point.compareX(ir.getPoint()) == 1)
                    count++;


                System.out.println(ir.getIntersects());
                if(ir.getIntersects()) {
                    System.out.println(ir.getPoint().inYProection(edge));
                    System.out.println(p.compareX(ir.getPoint()));
                }
                System.out.println();


            }
            prev = p;
        }

        if (count % 2 != 0)
            return true;
        else
            return false;
    }

 public static void main(String args[]){
        List<Point> vertices = new ArrayList<>();
        vertices.add(new Point(1.25, 3.5));
        vertices.add(new Point(1.25, 2.05));
        vertices.add(new Point(2.51, 1.32));
        vertices.add(new Point(3.77, 2.05));
        vertices.add(new Point(3.77, 3.5));
        vertices.add(new Point(2.51, 4.23));
        Point point = new Point(2.5, 2.4);
        PointInPolygon pip = new PointInPolygon();
        System.out.println(pip.check(vertices, point));
    }
}
