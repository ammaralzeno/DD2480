package interceptor.lic;

import interceptor.model.Globals;
import interceptor.model.Point;

/**
 * Implementation of LIC 9.
 * checks if there exists a set of three points separated by exactly C_PTS and D_PTS
 * consecutive intervening points, respectively, that form an angle s.t.
 * angle < (PI - EPSILON) or angle > (PI + EPSILON).
 */
public final class Lic9 {
    private Lic9() {}

    /**
     * evaluates LIC 9 based on the current Global parameters and points.
     * @return true if the condition is met, false otherwise.
     */
    public static boolean evaluate() {
        // Condition: NUMPOINTS must be at least 5
        if (Globals.NUMPOINTS < 5) return false;

        int cPts = Globals.PARAMETERS.C_PTS;
        int dPts = Globals.PARAMETERS.D_PTS;

        // Loop through points to find triplets with required gaps
        for (int i = 0; i < Globals.NUMPOINTS - cPts - dPts - 2; i++) {
            Point p1 = Globals.POINTS[i];
            Point p2 = Globals.POINTS[i + cPts + 1]; // The vertex
            Point p3 = Globals.POINTS[i + cPts + dPts + 2];

            // If the vertex coincides with either endpoint, the angle is undefined
            if (isSame(p1, p2) || isSame(p3, p2)) continue;

            double angle = calculateAngle(p1, p2, p3);

            // Check if angle is outside the EPSILON range around PI
            if (angle < (Math.PI - Globals.PARAMETERS.EPSILON) ||
                    angle > (Math.PI + Globals.PARAMETERS.EPSILON)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSame(Point a, Point b) {
        return Math.abs(a.x - b.x) < 1e-9 && Math.abs(a.y - b.y) < 1e-9;
    }

    private static double calculateAngle(Point p1, Point p2, Point p3) {
        double v1x = p1.x - p2.x;
        double v1y = p1.y - p2.y;
        double v2x = p3.x - p2.x;
        double v2y = p3.y - p2.y;
        double angle = Math.atan2(v2y, v2x) - Math.atan2(v1y, v1x);
        // Normalize angle to be positive
        if (angle < 0) angle += 2 * Math.PI;
        return angle;
    }
}