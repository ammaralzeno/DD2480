package interceptor.lic;

import interceptor.model.Globals;
import interceptor.model.Parameters;
import interceptor.model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Lic10Test {

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
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(1, 0);
        Globals.POINTS[2] = new Point(2, 0);
        Globals.POINTS[3] = new Point(3, 0);

        Globals.PARAMETERS.E_PTS = 1;
        Globals.PARAMETERS.F_PTS = 1;
        Globals.PARAMETERS.AREA1 = 0.0;

        assertFalse(Lic10.evaluate());
    }

    /**
     * Contract: NUMPOINTS = 5, E_PTS = 1, F_PTS = 1.
     * Triple indices (0,2,4) form a triangle with area > AREA1.
     * Oracle: evaluate() returns true.
     */
    @Test
    public void testValidTripleAreaGreaterThanArea1True() {
        Globals.NUMPOINTS = 5;

        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(1, 0);
        Globals.POINTS[2] = new Point(2, 0);
        Globals.POINTS[3] = new Point(3, 0);
        Globals.POINTS[4] = new Point(4, 2); // forms triangle

        Globals.PARAMETERS.E_PTS = 1;
        Globals.PARAMETERS.F_PTS = 1;
        Globals.PARAMETERS.AREA1 = 1.0;

        assertTrue(Lic10.evaluate());
    }

    /**
     * Contract: Valid inputs, but triangle area equals AREA1.
     * Oracle: evaluate() returns false (strictly greater required).
     */
    @Test
    public void testBoundaryAreaEqualToArea1False() {
        Globals.NUMPOINTS = 5;

        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(1, 0);
        Globals.POINTS[2] = new Point(2, 0);
        Globals.POINTS[3] = new Point(3, 0);
        Globals.POINTS[4] = new Point(4, 2); // area = 2.0

        Globals.PARAMETERS.E_PTS = 1;
        Globals.PARAMETERS.F_PTS = 1;
        Globals.PARAMETERS.AREA1 = 2.0;

        assertFalse(Lic10.evaluate());
    }

    /**
     * Contract: Valid inputs, but all valid triples produce area <= AREA1.
     * Oracle: evaluate() returns false.
     */
    @Test
    public void testNoTriplePassesFalse() {
        Globals.NUMPOINTS = 6;

        // All points collinear => area = 0
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(1, 0);
        Globals.POINTS[2] = new Point(2, 0);
        Globals.POINTS[3] = new Point(3, 0);
        Globals.POINTS[4] = new Point(4, 0);
        Globals.POINTS[5] = new Point(5, 0);

        Globals.PARAMETERS.E_PTS = 1;
        Globals.PARAMETERS.F_PTS = 2; // valid: 1 + 2 <= 6 - 3
        Globals.PARAMETERS.AREA1 = 0.1;

        assertFalse(Lic10.evaluate());
    }
}
