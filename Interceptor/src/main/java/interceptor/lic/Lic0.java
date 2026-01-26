package interceptor.lic;

import interceptor.model.Globals; 
import interceptor.model.Parameters;
import interceptor.model.Point;

public final class Lic0 {

    private Lic0() {}

    public static boolean evaluate() throws IllegalStateException {
        if (Parameters.LENGTH1 < 0) {
            throw new IllegalStateException("LENGTH1 must be non-negative");
        }

        boolean result = false;
        for (int i = 0; i < Globals.NUMPOINTS - 1; i++) {
            if (dist(Globals.POINTS[i], Globals.POINTS[i+1]) > Parameters.LENGTH1) {
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
