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
    List<Point> vertices;
    Point point;
    boolean expectedResult;

    public PointInPolygonTest(Point[] pa, Point p, boolean expectedResult){
        this.vertices = new ArrayList(Arrays.asList(pa));
        this.point = p;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static List<Object[][]> data(){
        return Arrays.asList(new Object[][][]{
                //0
                {
                        {
                                new Point(1.25, 3.5),
                                new Point(1.25, 2.05),
                                new Point(2.51, 1.32),
                                new Point(3.77, 2.05),
                                new Point(3.77, 3.5),
                                new Point(2.51, 4.23)
                        },
                        {new Point(6, 2.5)}
                },

        });
    }

    @Test
    public void testCheck() throws Exception {
        assertEquals(PointInPolygon.check(vertices, point), expectedResult);
    }

}
