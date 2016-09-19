package lab4;

import lab1.Point;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by vlad on 30.04.16.
 */
public class TriangulationTest {

    private Triangulation triangulation;
    private ArrayList<Point> polygon;

    public TriangulationTest() {
        triangulation = new Triangulation(10);
        polygon = new ArrayList<>();
        polygon.add(new Point(398, 199));
        polygon.add(new Point(43, 65));
        polygon.add(new Point(31, 415));
    }

    @Test
    public void testTriangulate(){
        //triangulation.triangulate(polygon);
    }
}
