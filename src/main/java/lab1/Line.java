package lab1;

/**
 * Created by vlad on 23.02.16.
 */
public class Line {
    private double b = 0;
    private double k = 0;
    private boolean vertical = false;
    private boolean point = false;
    Point p1 = null;
    Point p2 = null;

    public Line(double x1, double y1, double x2, double y2){
        p1 = new Point(x1, y1);
        p2 = new Point(x2, y2);
        if (x1 != x2) {
            k = (y2 - y1) / (x2 - x1);
            b = (x2 * y1 - y2 * x1) / (x2 - x1);
        } else {
            if (p1.equals(p2))
                point = true;
            else
                vertical = true;
        }
    }

    public Line(Point p1, Point p2){
        this.p1 = p1;
        this.p2 = p2;
        if (p1.getX() != p2.getX()) {
            k = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
            b = (p2.getX() * p1.getY() - p2.getY() * p1.getX()) / (p2.getX() - p1.getX());
        } else {
            if (p1.equals(p2))
                point = true;
            else
                vertical = true;
        }
    }

    public double leftX(){
        return Math.min(p1.getX(), p2.getX());
    }

    public double rightX(){
        return Math.max(p1.getX(), p2.getX());
    }

    public double bottomY(){
        return Math.min(p1.getY(), p2.getY());
    }

    public double topY(){
        return Math.max(p1.getY(), p2.getY());
    }

    public double X1(){
        return p1.getX();
    }

    public double X2(){
        return p2.getX();
    }

    public double Y1(){
        return p1.getY();
    }

    public double Y2(){
        return p2.getY();
    }

    public double getK(){
        return k;
    }

    public double getB(){
        return b;
    }

    public Point getP1(){
        return p1;
    }

    public boolean isVertical(){
        return vertical;
    }

    public boolean isPoint() { return point; }

    public boolean inBoundingRect(Point p){
        return p.getX() >= leftX() && p.getX() <= rightX() && p.getY() >= bottomY() && p.getY() <= topY();
    }

    public IntersectionResult lineIntersection(Line l){
        IntersectionResult result = new IntersectionResult();

        if (isPoint() && l.isPoint()) {
            if (leftX() == l.leftX() && bottomY() == l.bottomY()) {
                result.setIntersect(true);
                result.setImposed(true);
                result.setPoint(p1);
                return result;
            } else {
                result.setIntersect(false);
                return result;
            }
        }
        if (isPoint()) {
            if (bottomY() == l.getK() * leftX() + l.getB()){
                result.setIntersect(true);
                result.setPoint(p1);
                return result;
            } else {
                result.setIntersect(false);
                return result;
            }
        }

        if (l.isPoint()) {
            if (l.bottomY() == getK() * l.leftX() + getB()) {
                result.setIntersect(true);
                result.setPoint(l.getP1());
                return result;
            } else {
                result.setIntersect(false);
                return result;
            }
        }

        if (isVertical() && l.isVertical()) {
            if (leftX() == l.leftX()) {
                result.setIntersect(true);
                result.setImposed(true);
                return result;
            } else {
                result.setIntersect(false);
                result.setParallel(true);
                return result;
            }
        }
        if (k == l.getK() && b == l.getB()){
            result.setIntersect(true);
            result.setImposed(true);
            return result;
        }
        if (k == l.getK() && !isVertical() && !l.isVertical()){
            result.setIntersect(false);
            result.setParallel(true);
            return result;
        }

        double x;
        double y;

        if (isVertical() || l.isVertical()){
            if (isVertical()) {
                x = leftX();
                y = l.getK() * x + l.getB();
            } else {
                x = l.leftX();
                y = getK() * x + getB();
            }
        } else {
            x = (l.getB() - b) / (k - l.getK());
            y = (b * l.getK() - l.getB() * k) / (l.getK() - k);
        }
        result.setIntersect(true);
        result.setPoint(new Point(x, y));
        return result;
    }

    public IntersectionResult segmentsIntersection(Line l){
        IntersectionResult intersectionResult = lineIntersection(l);

        if (!intersectionResult.getIntersect()) {
            return intersectionResult;
        } else {
            if (intersectionResult.getImposed()) {
                if (p1.inSegment(l) || p2.inSegment(l) || l.p1.inSegment(this) || l.p2.inSegment(this)) {
                    if (p1.equals(l.p1) || p1.equals(l.p2))
                        intersectionResult.setPoint(p1);
                    if (p2.equals(l.p1) || p2.equals(l.p2))
                        intersectionResult.setPoint(p2);
                    intersectionResult.setIntersect(true);
                    return intersectionResult;
                } else {
                    intersectionResult.setIntersect(false);
                    return intersectionResult;
                }
            }
            Point point = intersectionResult.getPoint();
            if (point.inSegment(this) && point.inSegment(l)) {
                return intersectionResult;
            } else {
                intersectionResult.setIntersect(false);
                intersectionResult.setPoint(null);
                return intersectionResult;
            }
        }

    }

    public double angleBetween(Line line){
        double a1 = X2() - X1();
        double a2 = Y2() - Y1();
        double b1 = line.X2() - line.X1();
        double b2 = line.Y2() - line.Y1();

        double angle = Math.atan2(b2, b1) - Math.atan2(a2, a1);
        if (angle < 0)
            angle += 2 * Math.PI;

        return Math.toDegrees(angle);
    }
}
