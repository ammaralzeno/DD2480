package interceptor.lic;

import interceptor.model.Globals;
import interceptor.model.Parameters;
import interceptor.model.Point;

public final class Lic2 {

    private Lic2() {}

    public static boolean evaluate() {
        if (Globals.NUMPOINTS < 3) {
            return false;
        }

        for (int i = 0; i < Globals.NUMPOINTS - 2; i++) {
            Point p1 = Globals.POINTS[i];
            Point p2 = Globals.POINTS[i + 1];
            Point p3 = Globals.POINTS[i + 2];

            if (isSamePoint(p1, p2) || isSamePoint(p3, p2)) {
                continue;
            }

            double angle = calculateAngle(p1, p2, p3);

            if (angle < (Math.PI - Globals.PARAMETERS.EPSILON) || angle > (Math.PI + Globals.PARAMETERS.EPSILON)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isSamePoint(Point a, Point b) {
        return Math.abs(a.x - b.x) < 0.000001 && Math.abs(a.y - b.y) < 0.000001;
    }

    private static double calculateAngle(Point p1, Point p2, Point p3) {
        double v1x = p1.x - p2.x;
        double v1y = p1.y - p2.y;


        double v2x = p3.x - p2.x;
        double v2y = p3.y - p2.y;

        double angle = Math.atan2(v2y, v2x) - Math.atan2(v1y, v1x);
        if (angle < 0) {
            angle += 2 * Math.PI;
        }

        return angle;
    }
}