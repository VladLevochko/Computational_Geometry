package lab1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by vlad on 23.02.16.
 */
public class LineIntersection {

    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        double x1 = in.nextDouble();
        double y1 = in.nextDouble();
        double x2 = in.nextDouble();
        double y2 = in.nextDouble();
        double x3 = in.nextDouble();
        double y3 = in.nextDouble();
        double x4 = in.nextDouble();
        double y4 = in.nextDouble();

//        Line line1 = new Line(new Point(1, 1), new Point(4, 3));
//        Line line2 = new Line(new Point(1, 1), new Point(4, 1));

        Line line1 = new Line(new Point(x1, y1), new Point(x2, y2));
        Line line2 = new Line(new Point(x3, y3), new Point(x4, y4));

        IntersectionResult ir = line1.lineIntersects(line2);

        if (true);
        System.out.println();
        System.out.println(line1.angleBetween(line2));
    }
}
