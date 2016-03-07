package lab1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by vlad on 26.02.16.
 */

@RunWith(value = Parameterized.class)
public class LineSegmentTest {
    private Line line1;
    private Line line2;
    private IntersectionResult expectedResult = new IntersectionResult();

    public LineSegmentTest(Line line1, Line line2, boolean intersects, Point point, boolean parallel, boolean imposed){
        this.line1 = line1;
        this.line2 = line2;
        this.expectedResult.setIntersect(intersects);
        this.expectedResult.setPoint(point);
        this.expectedResult.setParallel(parallel);
        this.expectedResult.setImposed(imposed);
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> data(){
        return Arrays.asList(new Object[][]{
                //0
                {
                        new Line(new Point(1, 6), new Point(5, 4)),
                        new Line(new Point(1, 1), new Point(4, 4)),
                        false,
                        null,
                        false,
                        false
                },
                //1
                {
                        new Line(new Point(1, 1), new Point(5, 4)),
                        new Line(new Point(4, 1), new Point(2, 4)),
                        true,
                        new Point(3, 2.5),
                        false,
                        false
                },
                //2
                {
                        new Line(new Point(2, 1), new Point(5, 4)),
                        new Line(new Point(1, 1), new Point(4, 4)),
                        false,
                        null,
                        true,
                        false
                },
                //3
                {
                        new Line(new Point(1, 1), new Point(4, 4)),
                        new Line(new Point(4, 1), new Point(4, 4)),
                        true,
                        new Point(4, 4),
                        false,
                        false
                },
                //4
                {
                        new Line(new Point(5, 1), new Point(5, 3)),
                        new Line(new Point(4, 1), new Point(4, 4)),
                        false,
                        null,
                        true,
                        false
                },
                //5
                {
                        new Line(new Point(5, 1), new Point(5, 3)),
                        new Line(new Point(5, 1), new Point(5, 3)),
                        true,
                        new Point(5, 3),
                        false,
                        true
                },
                //6
                {
                        new Line(new Point(0, 0), new Point(0, 5)),
                        new Line(new Point(0, 0), new Point(0.00002, 40)),
                        true,
                        new Point(0, 0),
                        false,
                        false
                },
                //7 1-102-2
                {
                        new Line(new Point(0, 0), new Point(2, 0)),
                        new Line(new Point(3, 0), new Point(4, 0)),
                        false,
                        null,
                        false,
                        true
                },
                //8 1-12-2
                {
                        new Line(new Point(0, 0), new Point(3, 0)),
                        new Line(new Point(3, 0), new Point(4, 0)),
                        true,
                        new Point(3, 0),
                        false,
                        true
                },
                //9 1-2-1-2
                {
                        new Line(new Point(0, 0), new Point(3, 0)),
                        new Line(new Point(2, 0), new Point(4, 0)),
                        true,
                        null,
                        false,
                        true
                },
                //10 1-2-2-1
                {
                        new Line(new Point(0, 0), new Point(5, 0)),
                        new Line(new Point(3, 0), new Point(4, 0)),
                        true,
                        null,
                        false,
                        true
                },
                //11 2-1-1-2
                {
                        new Line(new Point(0, 0), new Point(3, 0)),
                        new Line(new Point(-3, 0), new Point(4, 0)),
                        true,
                        null,
                        false,
                        true
                },
                //12 2-1-2-1
                {
                        new Line(new Point(0, 0), new Point(3, 0)),
                        new Line(new Point(-3, 0), new Point(2, 0)),
                        true,
                        null,
                        false,
                        true
                },
                //13 2-201-1
                {
                        new Line(new Point(0, 0), new Point(3, 0)),
                        new Line(new Point(-3, 0), new Point(-4, 0)),
                        false,
                        null,
                        false,
                        true
                },

        });
    }

    @Test
    public void testLineSegmentIntersects() throws Exception {
        IntersectionResult currentResult = line1.segmentIntersection(line2);

        assertEquals(currentResult, expectedResult);
    }
}
