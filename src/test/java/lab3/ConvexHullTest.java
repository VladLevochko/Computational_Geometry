package lab3;

import lab1.Point;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by vlad on 05.04.16.
 */

@RunWith(value = Parameterized.class)
public class ConvexHullTest {
    private ArrayList<Point> pointSet;
    private ArrayList<Point> expectedConvexHull;

    private static ArrayList<Point> in1 = new ArrayList<Point>(){{
        add(new Point(1, 1));
        add(new Point(2, 1));
        add(new Point(3, 2));
        add(new Point(2, 3));
        add(new Point(1, 3));
        add(new Point(0, 2));
        add(new Point(2, 2));
    }};
    private static ArrayList<Point> out1 = new ArrayList<Point>(){{
        add(new Point(1, 1));
        add(new Point(2, 1));
        add(new Point(3, 2));
        add(new Point(2, 3));
        add(new Point(1, 3));
        add(new Point(0, 2));
    }};


    public ConvexHullTest(ArrayList<Point> pointSet, ArrayList<Point> expectedConvexHull) {
        this.pointSet = pointSet;
        this.expectedConvexHull = expectedConvexHull;
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{
                //0
                {
                        in1,
                        out1
                }
        });
    }

    @Test
    public void testJarvis() throws Exception {
        assertEquals(expectedConvexHull, ConvexHull.jarvis(pointSet));
    }
}
