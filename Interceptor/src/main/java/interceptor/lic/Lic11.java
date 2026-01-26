package interceptor.lic;
import interceptor.model.Point;
import interceptor.model.Globals;
import interceptor.model.Parameters;

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
