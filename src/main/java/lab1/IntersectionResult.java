package lab1;

/**
 * Created by vlad on 24.02.16.
 */
public class IntersectionResult {
    private boolean isIntersect = false;
    private boolean isParallel = false;
    private boolean isImposed = false;
    private Point point = null;

    @Override
    public String toString() {
        return "IntersectionResult{" +
                "\n isIntersect=" + isIntersect +
                ", \n isParallel=" + isParallel +
                ", \n isImposed=" + isImposed +
                ", \n point=" + point +
                "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntersectionResult that = (IntersectionResult) o;

        if (isIntersect != that.getIntersect()) return false;
        if (isParallel != that.getParallel()) return false;
        if (isImposed != that.getImposed()) return false;
        return point != null ? point.equals(that.getPoint()) : that.getPoint() == null;

    }

    @Override
    public int hashCode() {
        int result = (isIntersect ? 1 : 0);
        result = 31 * result + (isParallel ? 1 : 0);
        result = 31 * result + (isImposed ? 1 : 0);
        result = 31 * result + (point != null ? point.hashCode() : 0);
        return result;
    }

    public IntersectionResult(){
    }

    public IntersectionResult(boolean intersects, Point point){
        this.isIntersect = intersects;
        this.point = point;
    }

    public void setIntersect(boolean intersect){
        this.isIntersect = intersect;
    }

    public void setParallel(boolean parallel){
        this.isParallel = parallel;
    }

    public void setImposed(boolean imposed){
        this.isImposed = imposed;
    }

    public void setPoint(Point point){
        this.point = point;
    }

    public boolean getIntersect(){
        return isIntersect;
    }

    public boolean getParallel(){
        return isParallel;
    }

    public boolean getImposed(){
        return isImposed;
    }

    public Point getPoint(){
        return point;
    }
}
