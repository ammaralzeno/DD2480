package interceptor.unlock;
import interceptor.model.Connector;

public class Pum {
    private boolean[][] pum;

    public Pum(boolean[] cmv, Connector[][] lcm) {
        int size = cmv.length;
        pum = new boolean[size][size];

        // Form the Preliminary Unlocking Matrix (PUM) using CMV and LCM as specified
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if (lcm[i][j] == Connector.NOTUSED) {
                    // If LCM[i,j] is NOTUSED, then PUM[i,j] is set to true
                    pum[i][j] = true;
                } else if (lcm[i][j] == Connector.ANDD) {
                    // If LCM[i,j] is ANDD, PUM[i,j] is true only if CMV[i] AND CMV[j] is true
                    pum[i][j] = cmv[i] && cmv[j];
                } else { // ORR
                    // If LCM[i,j] is ORR, PUM[i,j] is true if CMV[i] OR CMV[j] is true
                    pum[i][j] = cmv[i] || cmv[j];
                }
            }
        }
    }

    public boolean get(int i, int j) {
        return pum[i][j];
    }
}