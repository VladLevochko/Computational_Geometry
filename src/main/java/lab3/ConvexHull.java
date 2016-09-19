package lab3;

import lab1.Line;
import lab1.Point;

import java.util.ArrayList;

/**
 * Created by vlad on 05.04.16.
 */
public class ConvexHull {

    public static ArrayList<Point> jarvis(ArrayList<Point> pointSet) {
        ArrayList<Point> convexHull = new ArrayList<>();

        Point current = getLexicographicallyMinimal(pointSet);

        convexHull.add(current);
        while (true) {
            Line a, b;
            Point last = convexHull.get(convexHull.size() - 1);
            if (convexHull.size() == 1) {
                a = new Line(new Point(Integer.MAX_VALUE, convexHull.get(0).getY()), last);
            } else {
                a = new Line(convexHull.get(convexHull.size() - 2), last);
            }

            double angle = 360;
            double curLength = Integer.MAX_VALUE;
            Point nextPoint = convexHull.get(0);
            for (Point p : pointSet) {
                b = new Line(last, p);
                double curAngle = a.angleBetween(b);
                if (curAngle == angle && b.getLength() < curLength) {
                    nextPoint = p;
                }
                if (curAngle < angle && p != last && convexHull.indexOf(p) == -1 || curAngle < angle && p == convexHull.get(0) && convexHull.size() > 1) {
                    angle = curAngle;
                    nextPoint = p;
                }
            }

            if (nextPoint == convexHull.get(0)) {
                break;
            }
            convexHull.add(nextPoint);
        }

        return convexHull;
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
}
