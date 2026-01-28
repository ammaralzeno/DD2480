package interceptor.lic;
import interceptor.model.Point;
import interceptor.model.Globals;

/**
 * LIC 4: There exists at least one set of Q_PTS consecutive data points that lie in 
 * more than QUADS quadrants. Where there is ambiguity as to which quadrant 
 * contains a given point, priority of decision will be by quadrant number, 
 * i.e., I, II, III, IV. For example, the data point (0,0) is in quadrant I, 
 * the point (-l,0) is in quadrant II, the point (0,-l) is in quadrant III, 
 * the point (0,1) is in quadrant I and the point (1,0) is in quadrant I.
 * (2 ≤ Q_PTS ≤ NUMPOINTS), (1 ≤ QUADS ≤ 3)
 */

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