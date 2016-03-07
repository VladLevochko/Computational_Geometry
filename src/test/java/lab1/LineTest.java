package lab1;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;

/**
 * Created by vlad on 25.02.16.
 */

@RunWith(value = Parameterized.class)
public class LineTest {

    private Line line1;
    private Line line2;
    private IntersectionResult expectedResult = new IntersectionResult();

    public LineTest(Line line1, Line line2, boolean intersects, Point point, boolean parallel, boolean imposed){
        this.line1 = line1;
        this.line2 = line2;
        this.expectedResult.setIntersects(intersects);
        this.expectedResult.setPoint(point);
        this.expectedResult.setParallel(parallel);
        this.expectedResult.setImposed(imposed);
    }

    @Parameters
    public static Iterable<Object[]> data(){
        return Arrays.asList(new Object[][]{
                //0
                {
                        new Line(new Point(1, 6), new Point(5, 4)),
                        new Line(new Point(1, 1), new Point(4, 4)),
                        true,
                        new Point(4.333333333333333, 4.333333333333333),
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
                        new Line(new Point(5, 1), new Point(5, 2)),
                        new Line(new Point(4, 1), new Point(4, 4)),
                        false,
                        null,
                        true,
                        false
                },
                //5
                {
                        new Line(new Point(1, 1), new Point(1, 1)),
                        new Line(new Point(4, 1), new Point(4, 4)),
                        false,
                        null,
                        false,
                        false
                },
                //6
                {
                        new Line(new Point(5, 1), new Point(5, 3)),
                        new Line(new Point(5, 1), new Point(5, 1)),
                        false,
                        null,
                        false,
                        false
                },
                //7
                {
                        new Line(new Point(1, 2), new Point(5, 2)),
                        new Line(new Point(1, 1), new Point(5, 1)),
                        false,
                        null,
                        true,
                        false
                },
                //8
                {
                        new Line(new Point(1, 2), new Point(5, 2)),
                        new Line(new Point(2, 2), new Point(3, 2)),
                        true,
                        null,
                        false,
                        true
                },
                //9
                {
                        new Line(new Point(1.25, 3.5), new Point(1.25, 2.05)),
                        new Line(new Point(2.5, 2.4), new Point(3, 2.4)),
                        true,
                        new Point(1.25, 2.3999999999999986),
                        false,
                        false
                },

        });
    }

    @Test
    public void testLineIntersects() throws Exception {
        IntersectionResult currentResult = line1.lineIntersects(line2);

        assertEquals(currentResult, expectedResult);
    }

}