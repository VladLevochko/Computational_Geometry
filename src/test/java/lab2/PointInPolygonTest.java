package lab2;

import lab1.Point;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vlad on 29.02.16.
 */

@RunWith(value = Parameterized.class)
public class PointInPolygonTest {
    private Point point;
    private boolean expectedResult;
    private Polygon polygon = new Polygon();

    private static ArrayList<Point> hexagon1 = new ArrayList<Point>(){{
        add(new Point(1.25, 3.5));
        add(new Point(1.25, 2.05));
        add(new Point(2.51, 1.32));
        add(new Point(3.77, 2.05));
        add(new Point(3.77, 3.5));
        add(new Point(2.51, 4.23));
    }};

    private static ArrayList<Point> hexagon2 = new ArrayList<Point>(){{
        add(new Point(1, 4.73));
        add(new Point(0.5, 3.87));
        add(new Point(1, 3));
        add(new Point(2, 3));
        add(new Point(2.5, 3.87));
        add(new Point(2, 4.73));
    }};

    private static ArrayList<Point> P = new ArrayList<Point>(){{
        add(new Point(7.5, 4.5));
        add(new Point(6.5, 4.5));
        add(new Point(6.5, 3));
        add(new Point(6, 3));
        add(new Point(6, 5));
        add(new Point(8, 5));
        add(new Point(8, 3));
        add(new Point(7.5, 3));
    }};



    public PointInPolygonTest(ArrayList<Point> vertices, Point p, boolean expectedResult){
        this.polygon.setVertices(vertices);
        this.point = p;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static List<Object[]> data(){
        return Arrays.asList(new Object[][]{

                //0
                {
                        hexagon1, new Point(6, 2.5), false
                },
                //1
                {
                        hexagon1, new Point(2, 2.5), true
                },
                //2
                {
                        hexagon2, new Point(3, 3), false
                },
                //3
                {
                        hexagon2, new Point(2, 4), true
                },
                //4
                {
                        P, new Point(9, 3), false
                },
                //5
                {
                        P, new Point(7.8, 4), true
                },
                //6
                {
                        P, new Point(7.8, 3), true
                },
                //7
                {
                        P, new Point(8, 4), true
                },
                //8
                {
                        P, new Point(7, 4.5), true
                },
        });
    }

    @Test
    public void testInPolygon() throws Exception {
        assertEquals(polygon.inPolygon(point), expectedResult);
    }

}
