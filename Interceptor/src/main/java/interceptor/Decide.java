package interceptor;

import interceptor.model.Globals;
import interceptor.unlock.Pum;
import interceptor.unlock.Fuv;
import interceptor.lic.*;

/**
 * Decide: Decides if the interceptor should launch based on the current state of the system.
 * 
 * 1. Computes the CMV based on the current state of the system.
 * 2. Computes the PUM based on the CMV and LCM.
 * 3. Computes the FUV based on the PUM and PUV.
 * 4. Sets the LAUNCH based on the FUV.
 * 5. Outputs the result.
 */
public final class Decide {

    private Decide() {}

    public static void decide() {
        Globals.CMV = new boolean[15];
        Globals.CMV[0] = Lic0.evaluate();
        Globals.CMV[1] = Lic1.evaluate();
        Globals.CMV[2] = Lic2.evaluate();
        Globals.CMV[3] = Lic3.evaluate();
        Globals.CMV[4] = Lic4.evaluate();
        Globals.CMV[5] = Lic5.evaluate();
        Globals.CMV[6] = Lic6.evaluate();
        Globals.CMV[7] = Lic7.evaluate();
        Globals.CMV[8] = Lic8.evaluate();
        Globals.CMV[9] = Lic9.evaluate();
        Globals.CMV[10] = Lic10.evaluate();
        Globals.CMV[11] = Lic11.evaluate();
        Globals.CMV[12] = Lic12.evaluate();
        Globals.CMV[13] = Lic13.evaluate();
        Globals.CMV[14] = Lic14.evaluate();

        Pum pumCalculator = new Pum(Globals.CMV, Globals.LCM);
        Globals.PUM = new boolean[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                Globals.PUM[i][j] = pumCalculator.get(i, j);
            }
        }

        Fuv fuvCalculator = new Fuv(Globals.PUM, Globals.PUV);
        Globals.FUV = new boolean[15];
        boolean allTrue = true;
        for (int i = 0; i < 15; i++) {
            Globals.FUV[i] = fuvCalculator.get(i);
            if (!Globals.FUV[i]) {
                allTrue = false;
            }
        }

        Globals.LAUNCH = allTrue;

        if (Globals.LAUNCH) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
