package lab1;

/**
 * Created by vlad on 27.02.16.
 */

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;

@RunWith(value = Parameterized.class)
public class AngleTest {

    private Line l1 = null;
    private Line l2 = null;
    private double angle = 0;
    private double delta = Math.exp(-24);

    public AngleTest(Line l1, Line l2, double angle){
        this.l1 = l1;
        this.l2 = l2;
        this.angle = angle;
    }

    @Parameters
    public static Iterable<Object[]> data(){
        return Arrays.asList(new Object[][]{
                //0
                {
                        new Line(new Point(1, 1), new Point(4, 3)),
                        new Line(new Point(1, 1), new Point(4, 1)),
                        326.3099324740202
                },
                //1
                {
                        new Line(new Point(1, 2), new Point(3, 3)),
                        new Line(new Point(1, 1), new Point(4, 3)),
                        7.125016348901797
                },
                //2
                {
                        new Line(new Point(1, 1), new Point(4, 3)),
                        new Line(new Point(1, 2), new Point(3, 3)),
                        352.87498365109815
                },
                //3
                {
                        new Line(new Point(1, 2), new Point(3, 3)),
                        new Line(new Point(1, 2), new Point(4, 2)),
                        333.434948822922
                },
                //4 vp
                {
                        new Line(new Point(1, 1), new Point(1, 3)),
                        new Line(new Point(2, 1), new Point(2, 3)),
                        0
                },
                //5 hp
                {
                        new Line(new Point(1, 2), new Point(3, 2)),
                        new Line(new Point(1, 3), new Point(3, 3)),
                        0
                },
                //6 parallel
                {
                        new Line(new Point(1, 1), new Point(3, 3)),
                        new Line(new Point(2, 1), new Point(4, 3)),
                        0
                },
                //7 imposed
                {
                        new Line(new Point(1, 1), new Point(3, 3)),
                        new Line(new Point(1, 1), new Point(3, 3)),
                        0
                },
                //8
                {
                        new Line(new Point(0, 0), new Point(10000000, 0)),
                        new Line(new Point(0, 0), new Point(10000000, 1)),
                        5.729577951308213E-6
                },
                //9
                {
                        new Line(new Point(0, 0), new Point(-5, 0.00002)),
                        new Line(new Point(0, 0), new Point(5, 0)),
                        180.00022918311802
                },
                //10
                {
                        new Line(new Point(0, 0), new Point(2, 0)),
                        new Line(new Point(0, 0), new Point(0, 2)),
                        90
                },
                //11
                {
                        new Line(new Point(0, 0), new Point(0, 2)),
                        new Line(new Point(0, 0), new Point(2, 0)),
                        270
                },
                //12
                {
                        new Line(new Point(0, 0), new Point(0, 2)),
                        new Line(new Point(0, 0), new Point(0, -2)),
                        180
                },
                //13
                {
                        new Line(new Point(0, 0), new Point(0, -2)),
                        new Line(new Point(0, 0), new Point(0, 2)),
                        180
                },
                //14
                {
                        new Line(new Point(0, 0), new Point(2, 0)),
                        new Line(new Point(0, 0), new Point(-2, 1)),
                        153.43494882292202
                },
                //15
                {
                        new Line(new Point(0, 0), new Point(-2, 1)),
                        new Line(new Point(0, 0), new Point(2, 0)),
                        206.56505117707798
                },
        });
    }


    @Test
    public void testAngleBetween() throws Exception{
        double expectedAngle = l1.angleBetween(l2);
        assertEquals(expectedAngle, angle, delta);
    }

}
