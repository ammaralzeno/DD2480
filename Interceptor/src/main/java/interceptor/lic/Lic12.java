package interceptor.lic;

import interceptor.model.Globals;
import interceptor.model.Parameters;
import interceptor.model.Point;

public class Lic12 {
    private Lic12() {}

    public static boolean evaluate() throws IllegalStateException {

        if (Parameters.LENGTH2 < 0) {
            throw new IllegalStateException("LENGTH2 must be non-negative");
        }

        boolean result = false;

        if (!Lic7.evaluate()) return result;

        for (int i = 0; i + Parameters.K_PTS < Globals.NUMPOINTS; i++) {
            if (dist(Globals.POINTS[i], Globals.POINTS[i+Parameters.K_PTS]) < Parameters.LENGTH2) {
                result = true;
                break;
            }
        }
        return result;
    }

    private static double dist(Point point1, Point point2) {
        double diffx = point2.x - point1.x;
        double diffy = point2.y - point1.y;
        return Math.sqrt(Math.pow(diffx, 2) + Math.pow(diffy, 2));
    }
}
