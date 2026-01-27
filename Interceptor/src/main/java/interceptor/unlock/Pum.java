package interceptor.unlock;
import interceptor.model.Connector;

/**
 * PUM: Preliminary Unlocking Matrix
 * 
 * Creates the PUM based on CMV and LCM
 * 
 * Each element PUM[i][j] is computed according to the value of LCM[i][j]:
 *  - NOTUSED --> PUM[i][j] = true
 *  - ANDD    --> PUM[i][j] = CMV[i] AND CMV[j]
 *  - ORR     --> PUM[i][j] = CMV[i] OR CMV[j]
 *
 * The PUM is a square boolean matrix of size CMV.length × CMV.length.
 * 
 */
public class Pum {
    private boolean[][] pum;

    public Pum(boolean[] cmv, Connector[][] lcm) {
        int size = cmv.length;
        pum = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if (lcm[i][j] == Connector.NOTUSED) {
                    pum[i][j] = true;
                } else if (lcm[i][j] == Connector.ANDD) {
                    pum[i][j] = cmv[i] && cmv[j];
                } else { 
                    pum[i][j] = cmv[i] || cmv[j];
                }
            }
        }
    }

    public boolean get(int i, int j) {
        return pum[i][j];
    }
}