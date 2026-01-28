package interceptor.lic;

import interceptor.model.Globals;
import interceptor.model.Parameters;
import interceptor.model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Launch Interceptor Condition (LIC) 6.
 * LIC 6 checks if any point in a sequence of N_PTS lies further than
 * DIST from the line joining the start and end of that sequence.
 */
public class Lic6Test {

    @BeforeEach
    public void setup() {
        Globals.PARAMETERS = new Parameters();
    }

    /**
     * Test Case: Positive.
     * a point (0,1) is exactly 1.0 unit away from the line (0,0)-(1,0).
     * since DIST is 0.5, this should return true.
     */
    @Test
    public void testLic6Positive() {
        Globals.PARAMETERS.N_PTS = 3;
        Globals.PARAMETERS.DIST = 0.5;
        Globals.NUMPOINTS = 3;
        Globals.POINTS = new Point[]{
                new Point(0, 0),
                new Point(0, 1),
                new Point(1, 0)
        };
        assertTrue(Lic6.evaluate(), "Point is further than DIST from the line.");
    }

    /**
     * Test Case: Negative.
     * all points lie exactly on the line (0,0)-(2,0).
     * the distance is 0, which is not greater than DIST (0.5), so it returns false.
     */
    @Test
    public void testLic6Negative() {
        Globals.PARAMETERS.N_PTS = 3;
        Globals.PARAMETERS.DIST = 0.5;
        Globals.NUMPOINTS = 3;
        Globals.POINTS = new Point[]{
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0)
        };
        assertFalse(Lic6.evaluate(), "Points on the line should not satisfy the condition.");
    }

    /**
     * Test Case: Edge (Coinciding Endpoints).
     * when the start and end points of the n_pts sequence are the same,
     * distance is measured from the point to that single location.
     * (0,1) is 1.0 unit away from (0,0). 1.0 > 0.5, so it returns true.
     */
    @Test
    public void testLic6EdgeCoinciding() {
        Globals.PARAMETERS.N_PTS = 3;
        Globals.PARAMETERS.DIST = 0.5;
        Globals.NUMPOINTS = 3;
        Globals.POINTS = new Point[]{
                new Point(0, 0),
                new Point(0, 1),
                new Point(0, 0)
        };
        assertTrue(Lic6.evaluate(), "Distance to coinciding endpoints should satisfy condition.");
    }
}