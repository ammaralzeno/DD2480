package interceptor.lic;
import interceptor.model.Point;
import interceptor.model.Globals;
import interceptor.model.Parameters;

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
