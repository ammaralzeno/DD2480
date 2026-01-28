package interceptor;

import interceptor.model.Connector;
import interceptor.model.Globals;
import interceptor.model.Parameters;
import interceptor.model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the main Decide function.
 * Verifies the integration of LICs, PUM, FUV, and final LAUNCH decision.
 */
public class DecideTest {

    @BeforeEach
    public void setUp() {
        Globals.PARAMETERS = new Parameters();
        Globals.POINTS = new Point[100];
        Globals.LCM = new Connector[15][15];
        Globals.PUV = new boolean[15];
        Globals.CMV = new boolean[15];
        Globals.PUM = new boolean[15][15];
        Globals.FUV = new boolean[15];
        Globals.LAUNCH = false;

        for (int i = 0; i < 100; i++) {
            Globals.POINTS[i] = new Point(0, 0);
        }
        Globals.NUMPOINTS = 10;
        
        for (int i = 0; i < 15; i++) {
            Arrays.fill(Globals.LCM[i], Connector.NOTUSED);
        }

        Globals.PARAMETERS.N_PTS = 3;
        Globals.PARAMETERS.Q_PTS = 2;
        Globals.PARAMETERS.QUADS = 1;
        Globals.PARAMETERS.K_PTS = 1;
        Globals.PARAMETERS.A_PTS = 1;
        Globals.PARAMETERS.B_PTS = 1;
        Globals.PARAMETERS.C_PTS = 1;
        Globals.PARAMETERS.D_PTS = 1;
        Globals.PARAMETERS.E_PTS = 1;
        Globals.PARAMETERS.F_PTS = 1;
        Globals.PARAMETERS.G_PTS = 1;
    }

    /**
     * Test Case 1: Positive Case (YES)
     * Contract:
     * - PUV is all TRUE (all LICs matter).
     * - LCM is all NOTUSED (Conditions are ignored in PUM calculation, so PUM is all true).
     * - Oracle: LAUNCH should be TRUE because PUM is all true, so FUV is all true.
     */
    @Test
    public void testDecideYes() {
        Arrays.fill(Globals.PUV, true);

        for (int i = 0; i < 15; i++) {
            Arrays.fill(Globals.LCM[i], Connector.NOTUSED);
        }

        Globals.PARAMETERS.LENGTH1 = 1.0;
        Globals.PARAMETERS.RADIUS1 = 1.0;

        Decide.decide();

        assertTrue(Globals.LAUNCH, "LAUNCH should be YES when LCM is all NOTUSED");
    }

    /**
     * Test Case 2: Negative Case (NO)
     * Contract:
     * - PUV[0] is TRUE (LIC 0 matters).
     * - LCM[0][1] is ANDD.
     * - LIC 0 is TRUE (Distance > LENGTH1).
     * - LIC 1 is FALSE (Radius > RADIUS1).
     * - Oracle: LAUNCH should be FALSE.
     *   PUM[0][1] = LIC0 && LIC1 = TRUE && FALSE = FALSE.
     *   Since PUV[0] is TRUE, FUV[0] checks row 0 of PUM. PUM[0][1] is FALSE.
     *   So FUV[0] is FALSE. Launch condition requires all FUV to be TRUE.
     */
    @Test
    public void testDecideNo() {
        Globals.PUV[0] = true;
        Globals.LCM[0][1] = Connector.ANDD;
        Globals.LCM[1][0] = Connector.ANDD;

        Globals.NUMPOINTS = 3;
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(0, 0);
        Globals.POINTS[2] = new Point(5, 0);

        Globals.PARAMETERS.LENGTH1 = 4.0; 
        Globals.PARAMETERS.RADIUS1 = 3.0;

        Decide.decide();

        assertTrue(Globals.CMV[0], "LIC0 should be TRUE");
        assertFalse(Globals.CMV[1], "LIC1 should be FALSE");
        assertFalse(Globals.PUM[0][1], "PUM[0][1] should be FALSE (T && F)");
        assertFalse(Globals.FUV[0], "FUV[0] should be FALSE because PUM row 0 has a false");
        assertFalse(Globals.LAUNCH, "LAUNCH should be NO");
    }

    /**
     * Test Case 3: Edge Case / Filtered (YES)
     * Contract:
     * - Same conditions as Test Case 2 (Logic fails for LIC 0/1 combination).
     * - BUT PUV[0] is FALSE (LIC 0 does not hold back launch).
     * - All other PUV elements are FALSE.
     * - Oracle: LAUNCH should be TRUE.
     *   Even though PUM[0][1] is FALSE, FUV[0] is TRUE because PUV[0] is FALSE.
     *   Since all PUV are FALSE, all FUV are TRUE.
     */
    @Test
    public void testDecideFiltered() {
        Arrays.fill(Globals.PUV, false);

        Globals.LCM[0][1] = Connector.ANDD;
        Globals.LCM[1][0] = Connector.ANDD;

        Globals.NUMPOINTS = 3;
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(0, 0);
        Globals.POINTS[2] = new Point(5, 0);
        Globals.PARAMETERS.LENGTH1 = 4.0;
        Globals.PARAMETERS.RADIUS1 = 3.0;

        Decide.decide();

        assertFalse(Globals.PUM[0][1], "PUM[0][1] should be FALSE");
        assertTrue(Globals.FUV[0], "FUV[0] should be TRUE because PUV[0] is FALSE");
        assertTrue(Globals.LAUNCH, "LAUNCH should be YES because all FUV are TRUE");
    }
}
