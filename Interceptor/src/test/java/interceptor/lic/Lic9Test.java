package interceptor.lic;

import interceptor.model.Globals;
import interceptor.model.Parameters;
import interceptor.model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for LIC 9.
 * tests triplets of points separated by C_PTS and D_PTS.
 */
public class Lic9Test {

    @BeforeEach
    public void setup() {
        Globals.PARAMETERS = new Parameters();
    }

    /**
     * Test Case: True.
     * triplet forms a 90-degree angle (PI/2), which is outside
     * the range of PI +/- epsilon (where epsilon is 0.1).
     */
    @Test
    public void testLic9TrueCondition() {
        Globals.NUMPOINTS = 5;
        Globals.PARAMETERS.C_PTS = 1;
        Globals.PARAMETERS.D_PTS = 1;
        Globals.PARAMETERS.EPSILON = 0.1;

        Globals.POINTS = new Point[]{
                new Point(1, 0),
                new Point(0, 0),
                new Point(0, 0),
                new Point(0, 0),
                new Point(0, 1)
        };

        assertTrue(Lic9.evaluate(), "a 90-degree angle should satisfy the condition.");
    }

    /**
     * Test Case: False.
     * all points are on a line, forming a 180-degree angle (PI).
     * this is within PI +/- epsilon, so it should be false.
     */
    @Test
    public void testLic9FalseDueToStraightLine() {
        Globals.NUMPOINTS = 5;
        Globals.PARAMETERS.C_PTS = 1;
        Globals.PARAMETERS.D_PTS = 1;
        Globals.PARAMETERS.EPSILON = 0.1;

        Globals.POINTS = new Point[]{
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(3, 0),
                new Point(4, 0)
        };

        assertFalse(Lic9.evaluate(), "a straight line (angle PI) should not satisfy the condition.");
    }

    /**
     * Test Case: False.
     * NUMPOINTS is less than 5, which should automatically return false.
     */
    @Test
    public void testLic9FalseDueToInsufficientPoints() {
        Globals.NUMPOINTS = 4;
        assertFalse(Lic9.evaluate(), "should return false if NUMPOINTS < 5.");
    }
}