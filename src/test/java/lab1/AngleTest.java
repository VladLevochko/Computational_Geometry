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
    private double delta = 0.001;

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
                        33.69
                },
                //1
                {
                        new Line(new Point(1, 2), new Point(3, 3)),
                        new Line(new Point(1, 1), new Point(4, 3)),
                        7.1250
                },
                //2
                {
                        new Line(new Point(1, 1), new Point(4, 3)),
                        new Line(new Point(1, 2), new Point(3, 3)),
                        7.1250
                },
                //3
                {
                        new Line(new Point(1, 2), new Point(3, 3)),
                        new Line(new Point(1, 2), new Point(4, 2)),
                        26.5650
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
                        new Line(new Point(0, 0), new Point(0, 5)),
                        new Line(new Point(0, 0), new Point(0.00002, 40)),
                        0.0001
                },
                {
                        new Line(new Point(0, 0), new Point(-5, 0.00002)),
                        new Line(new Point(0, 0), new Point(5, 0)),
                        179.99977
                }

        });
    }


    @Test
    public void testAngleBetween() throws Exception{
        double expectedAngle = l1.angleBetween(l2);
        assertEquals(expectedAngle, angle, delta);
    }

}
