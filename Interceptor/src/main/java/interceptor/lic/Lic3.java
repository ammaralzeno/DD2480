package interceptor.lic;

import interceptor.model.Globals;
import interceptor.model.Point;

public final class Lic3 {

    private Lic3() {}

    /*
     * LIC 3:
     * Returns true if there exists at least one set of three consecutive
     * data points that form a triangle with an area greater than AREA1.
     * (0 ≤ AREA1)
     */
    public static boolean evaluate() {
        if (Globals.NUMPOINTS < 3) {
            return false;
        }

        double area1 = Globals.PARAMETERS.AREA1;
        if (area1 < 0) {
            return false; 
        }

        // Check each set of three consecutive points
        for (int i = 0; i <= Globals.NUMPOINTS - 3; i++) {
            Point p1 = Globals.POINTS[i];
            Point p2 = Globals.POINTS[i + 1];
            Point p3 = Globals.POINTS[i + 2];

            double area = triangleArea(p1, p2, p3);

            if (area > area1) {
                return true;
            }
        }

        return false;
    }

    private static double triangleArea(Point a, Point b, Point c) {
        return Math.abs(
                a.x * (b.y - c.y) +
                b.x * (c.y - a.y) +
                c.x * (a.y - b.y)
        ) / 2.0;
    }
}
