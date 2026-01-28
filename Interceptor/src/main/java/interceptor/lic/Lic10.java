package interceptor.lic;

import interceptor.model.Globals;
import interceptor.model.Point;


/**
 * LIC 10:
 * There exists at least one set of three data points separated by exactly E_PTS and F_PTS
 * consecutive intervening points, respectively, that are the vertices of a triangle with
 * area greater than AREA1.
 * 
 * The condition is not met when NUMPOINTS < 5.
 * 1 ≤ E_PTS, 1 ≤ F_PTS
 * E_PTS + F_PTS ≤ NUMPOINTS − 3
*/

public final class Lic10 {

    private Lic10() {}

    public static boolean evaluate() {
        int numPoints = Globals.NUMPOINTS;
        int ePts = Globals.PARAMETERS.E_PTS;
        int fPts = Globals.PARAMETERS.F_PTS;
        double area1 = Globals.PARAMETERS.AREA1;

        if (numPoints < 5) {
            return false;
        }
        if (ePts < 1 || fPts < 1) {
            return false;
        }
        if (ePts + fPts > numPoints - 3) {
            return false;
        }

        int stepE = ePts + 1;
        int stepF = fPts + 1;
        int totalStep = stepE + stepF;

        for (int i = 0; i + totalStep < numPoints; i++) {
            int j = i + stepE;
            int k = j + stepF;

            Point p1 = Globals.POINTS[i];
            Point p2 = Globals.POINTS[j];
            Point p3 = Globals.POINTS[k];

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
