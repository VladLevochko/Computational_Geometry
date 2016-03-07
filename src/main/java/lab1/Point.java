package lab1;

/**
 * Created by vlad on 23.02.16.
 */
public class Point {
    private double x = 0;
    private double y = 0;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (Double.compare(point.x, x) != 0) return false;
        return Double.compare(point.y, y) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public boolean inYProection(Line segment) {
        return /*x >= segment.leftX() && x <= segment.rightX() &&*/ y >= segment.bottomY() && y <= segment.topY();
    }

    public boolean inSegment(Line segment) {
        return x >= segment.leftX() && x <= segment.rightX() && y >= segment.bottomY() && y <= segment.topY();
    }

    public int compareX(Point p){
        if (x == p.getX())
            return 0;
        if (x < p.getX())
            return -1;
        else
            return 1;
    }
}
