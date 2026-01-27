package interceptor.lic;

import interceptor.model.Globals;
import interceptor.model.Parameters;
import interceptor.model.Point;

/**
 * LIC 7: There exists at least one set of two data points separated by exactly K_PTS 
 * consecutive intervening points that are a distance greater than the length, 
 * LENGTH1, apart. The condition is not met when NUMPOINTS < 3.
 */
public class Lic7 {

    private Lic7() {}

    public static boolean evaluate() throws IllegalStateException {
        if (Parameters.LENGTH1 < 0) {
            throw new IllegalStateException("LENGTH1 must be non-negative");
        }

        if (Parameters.K_PTS < 1) {
            throw new IllegalStateException("K_PTS must be greater than or equal to 1");
        }

        if (Parameters.K_PTS > (Globals.NUMPOINTS - 2)) {
            throw new IllegalStateException("K_PTS must be smaller than or equal to (NUMPOINTS - 2)");
        }

        boolean result = false;

        if (Globals.NUMPOINTS < 3) return result;

        for (int i = 0; i + Parameters.K_PTS < Globals.NUMPOINTS - 1; i++) {
            if (dist(Globals.POINTS[i], Globals.POINTS[i+Parameters.K_PTS]) > Parameters.LENGTH1) {
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
