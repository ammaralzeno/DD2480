package interceptor.lic;

import interceptor.model.Globals;
import interceptor.model.Point;

public final class Lic14 {

    private Lic14() {}

    /*
     * LIC 14:
     * There exists at least one set of three data points, separated by exactly E_PTS and F_PTS
     * consecutive intervening points, respectively, that are the vertices of a triangle with
     * area greater than AREA1.
     *
     * In addition, there exists at least one (possibly different) set of three data points,
     * separated by exactly E_PTS and F_PTS consecutive intervening points, respectively, that
     * are the vertices of a triangle with area less than AREA2.
     *
     * Both conditions must be true for the LIC to be satisfied.
     * The condition is not met when NUMPOINTS < 5.
     * 0 ≤ AREA2
     */
    public static boolean evaluate() {
        int numPoints = Globals.NUMPOINTS;
        int ePts = Globals.PARAMETERS.E_PTS;
        int fPts = Globals.PARAMETERS.F_PTS;
        double area1 = Globals.PARAMETERS.AREA1;
        double area2 = Globals.PARAMETERS.AREA2;

        if (numPoints < 5) {
            return false;
        }
        if (ePts < 1 || fPts < 1) {
            return false;
        }
        if (area2 < 0) {
            return false;
        }
        if (ePts + fPts > numPoints - 3) {
            return false;
        }

        int stepE = ePts + 1;
        int stepF = fPts + 1;
        int totalStep = stepE + stepF;

        boolean foundGreaterThanArea1 = false;
        boolean foundLessThanArea2 = false;

        for (int i = 0; i + totalStep < numPoints; i++) {
            int j = i + stepE;
            int k = j + stepF;

            Point p1 = Globals.POINTS[i];
            Point p2 = Globals.POINTS[j];
            Point p3 = Globals.POINTS[k];

            double area = triangleArea(p1, p2, p3);

            if (area > area1) {
                foundGreaterThanArea1 = true;
            }
            if (area < area2) {
                foundLessThanArea2 = true;
            }

            if (foundGreaterThanArea1 && foundLessThanArea2) {
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
