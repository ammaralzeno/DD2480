package interceptor.lic;
import interceptor.model.Point;
import interceptor.model.Globals;
import interceptor.model.Parameters;

public final class Lic4 {

    private Lic4() {}

    public static boolean evaluate() {
        if(Globals.POINTS == null || Globals.PARAMETERS.QUADS < 1 || Globals.PARAMETERS.QUADS > 3 || Globals.PARAMETERS.Q_PTS < 2 || Globals.PARAMETERS.Q_PTS > Globals.NUMPOINTS) {
            return false;
        }

        for(int begin = 0; begin <= Globals.NUMPOINTS - Globals.PARAMETERS.Q_PTS; begin++) {
            boolean[] inclQuads = {false, false, false, false};

            for(int offset = 0; offset < Globals.PARAMETERS.Q_PTS; offset++) {
                Point coordinate = Globals.POINTS[begin+offset];

                if(coordinate.x >= 0 && coordinate.y >= 0) {
                    inclQuads[0] = true;
                } else if(coordinate.x < 0 && coordinate.y >= 0) {
                    inclQuads[1] = true;
                } else if(coordinate.x <= 0 && coordinate.y < 0) {
                    inclQuads[2] = true;
                } else {
                    inclQuads[3] = true;
                }
            }

            int numOfQuads = 0;
            for(int i = 0; i < inclQuads.length; i++) {
                if(inclQuads[i]) {
                    numOfQuads++;
                }
            }

            if (numOfQuads > Globals.PARAMETERS.QUADS) {
                return true;
            }
        }
        return false;
    }
}