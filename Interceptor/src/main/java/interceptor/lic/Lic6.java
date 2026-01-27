package interceptor.lic;

import interceptor.model.Globals;
import interceptor.model.Point;

public final class Lic6 {
    private Lic6() {}

    public static boolean evaluate() {
        if (Globals.NUMPOINTS < 3) return false;

        int nPts = Globals.PARAMETERS.N_PTS;
        double distThreshold = Globals.PARAMETERS.DIST;

        for (int i = 0; i <= Globals.NUMPOINTS - nPts; i++) {
            Point start = Globals.POINTS[i];
            Point end = Globals.POINTS[i + nPts - 1];

            for (int j = i + 1; j < i + nPts - 1; j++) {
                Point p = Globals.POINTS[j];
                double distance;

                if (isSame(start, end)) {
                    distance = Math.sqrt(Math.pow(p.x - start.x, 2) + Math.pow(p.y - start.y, 2));
                } else {
                    double num = Math.abs((end.y - start.y) * p.x - (end.x - start.x) * p.y + end.x * start.y - end.y * start.x);
                    double den = Math.sqrt(Math.pow(end.y - start.y, 2) + Math.pow(end.x - start.x, 2));
                    distance = num / den;
                }

                if (distance > distThreshold) return true;
            }
        }
        return false;
    }

    private static boolean isSame(Point a, Point b) {
        return Math.abs(a.x - b.x) < 0.000001 && Math.abs(a.y - b.y) < 0.000001;
    }
}