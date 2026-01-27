package interceptor.lic;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import interceptor.model.Globals;
import interceptor.model.Parameters;
import interceptor.model.Point;

public class Lic14Test {

    @BeforeEach
    public void setUp() {
        Globals.PARAMETERS = new Parameters();
        Globals.POINTS = new Point[100];
    }

    /**
     * Contract: NUMPOINTS < 5.
     * Oracle: evaluate() returns false.
     */
    @Test
    public void testInsufficientPointsFalse() {
        Globals.NUMPOINTS = 4;

        Globals.PARAMETERS.E_PTS = 1;
        Globals.PARAMETERS.F_PTS = 1;
        Globals.PARAMETERS.AREA1 = 1.0;
        Globals.PARAMETERS.AREA2 = 0.5;

        assertFalse(Lic14.evaluate());
    }

    /**
     * Contract: AREA2 < 0 (invalid by specification).
     * Oracle: evaluate() returns false.
     */
    @Test
    public void testArea2NegativeFalse() {
        Globals.NUMPOINTS = 5;

        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(1, 0);
        Globals.POINTS[2] = new Point(2, 0);
        Globals.POINTS[3] = new Point(3, 0);
        Globals.POINTS[4] = new Point(4, 2);

        Globals.PARAMETERS.E_PTS = 1;
        Globals.PARAMETERS.F_PTS = 1;
        Globals.PARAMETERS.AREA1 = 1.0;
        Globals.PARAMETERS.AREA2 = -0.1;

        assertFalse(Lic14.evaluate());
    }

    /**
     * Contract: Valid inputs, and there exists a triple with area > AREA1,
     * but there does not exist any triple with area < AREA2.
     * Oracle: evaluate() returns false because both conditions must be true.
     */
    @Test
    public void testOnlyGreaterThanArea1False() {
        Globals.NUMPOINTS = 5;

        // With E_PTS = 1 and F_PTS = 1, the only triple is (0,2,4)
        // Points (0,0), (2,0), (4,2) => area = 2.0
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(1, 0);
        Globals.POINTS[2] = new Point(2, 0);
        Globals.POINTS[3] = new Point(3, 0);
        Globals.POINTS[4] = new Point(4, 2);

        Globals.PARAMETERS.E_PTS = 1;
        Globals.PARAMETERS.F_PTS = 1;

        Globals.PARAMETERS.AREA1 = 1.0; // 2.0 > 1.0 => true for first condition
        Globals.PARAMETERS.AREA2 = 0.5; // 2.0 < 0.5 => false for second condition

        assertFalse(Lic14.evaluate());
    }

    /**
     * Contract: Valid inputs where BOTH conditions are true (possibly different triples).
     * NUMPOINTS = 6, E_PTS = 1, F_PTS = 1 gives two triples: (0,2,4) and (1,3,5).
     * - (0,2,4) has area 2.0 > AREA1 (AREA1 = 1.0)
     * - (1,3,5) has area 0.0 < AREA2 (AREA2 = 0.5)
     * Oracle: evaluate() returns true.
     */
    @Test
    public void testBothConditionsTrue() {
        Globals.NUMPOINTS = 6;

        // Triple (0,2,4): area = 2.0
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[2] = new Point(2, 0);
        Globals.POINTS[4] = new Point(4, 2);

        // Triple (1,3,5): collinear => area = 0.0
        Globals.POINTS[1] = new Point(10, 10);
        Globals.POINTS[3] = new Point(20, 20);
        Globals.POINTS[5] = new Point(30, 30);

        Globals.PARAMETERS.E_PTS = 1;
        Globals.PARAMETERS.F_PTS = 1;

        Globals.PARAMETERS.AREA1 = 1.0;
        Globals.PARAMETERS.AREA2 = 0.5;

        assertTrue(Lic14.evaluate());
    }
}
