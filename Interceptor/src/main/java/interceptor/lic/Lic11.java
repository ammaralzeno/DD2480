package interceptor.lic;
import interceptor.model.Globals;

/**
 * LIC 11: There exists at least one set of two data points, (X[i],Y[i]) and (X[j],Y[j]), 
 * separated by exactly G_PTS consecutive intervening points, such that X[j] - X[i] < 0. 
 * (where i < j)
 * The condition is not met when NUMPOINTS < 3.
 * 1 ≤ G_PTS ≤ NUMPOINTS − 2
 */

public final class Lic11 {

    private Lic11() {}

    public static boolean evaluate() {
        if(Globals.POINTS == null || Globals.NUMPOINTS < 3 || Globals.PARAMETERS.G_PTS < 1 || Globals.PARAMETERS.G_PTS > Globals.NUMPOINTS-2) {
            return false;
        }

        int diff = Globals.PARAMETERS.G_PTS + 1;

        for(int i = 0; i < Globals.NUMPOINTS - diff; i++) {
            if(Globals.POINTS[i + diff].x - Globals.POINTS[i].x < 0) {
                return true;
            }
        }

        return false;
    }
}
