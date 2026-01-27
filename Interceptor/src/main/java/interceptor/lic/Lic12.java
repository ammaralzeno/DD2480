package interceptor.lic;

import interceptor.model.Globals;
import interceptor.model.Parameters;
import interceptor.model.Point;

/**
 * LIC 12: There exists at least one set of two data points, separated by exactly K PTS 
 * consecutive intervening points, which are a distance greater than the length, 
 * LENGTH1, apart. In addition, there exists at least one set of two data points 
 * (which can be the same or different from the two data points just mentioned), 
 * separated by exactly K PTS consecutive intervening points, that are at a distance 
 * less than the length, LENGTH2, apart. Both parts must be true for the LIC to be true. 
 * The condition is not met when NUMPOINTS < 3.
 */
public class Lic12 {
    private Lic12() {}

    public static boolean evaluate() throws IllegalStateException {

        if (Parameters.LENGTH2 < 0) {
            throw new IllegalStateException("LENGTH2 must be non-negative");
        }

        boolean result = false;

        if (!Lic7.evaluate()) return result;

        for (int i = 0; i + Parameters.K_PTS < Globals.NUMPOINTS - 1; i++) {
            if (dist(Globals.POINTS[i], Globals.POINTS[i+Parameters.K_PTS]) < Parameters.LENGTH2) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Calculate the distance between two planar points.
     * @param point1
     * @param point2
     * @return distance
     */
    private static double dist(Point point1, Point point2) {
        double diffx = point2.x - point1.x;
        double diffy = point2.y - point1.y;
        return Math.sqrt(Math.pow(diffx, 2) + Math.pow(diffy, 2));
    }
}
