package interceptor.lic;
import interceptor.model.Globals;

/**
 * LIC 5: There exists at least one set of two consecutive data points, 
 * (X[i],Y[i]) and (X[j],Y[j]), such that X[j] - X[i] < 0. 
 * (where i = j-1)
 */

public final class Lic5 {

    private Lic5() {}

    public static boolean evaluate() {
        
        if (Globals.POINTS == null || Globals.NUMPOINTS < 2) {
            return false;
        }

        for (int i = 0; i < Globals.NUMPOINTS - 1; i++) {
            if(Globals.POINTS[i+1].x - Globals.POINTS[i].x < 0) {
                return true;
            }
        }
        return false;
    }
}
