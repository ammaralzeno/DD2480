package interceptor.lic;

import interceptor.model.Globals;
import interceptor.model.Parameters;
import interceptor.model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Launch Interceptor Condition (LIC) 2.
 * LIC 2 checks for an angle between three consecutive points that is
 * outside the range of [PI - EPSILON, PI + EPSILON].
 */
public class Lic2Test {

    @BeforeEach
    public void setup() {

        Globals.PARAMETERS = new Parameters();


        Globals.POINTS = new Point[100];
        Globals.NUMPOINTS = 0;
    }

    /**
     * Tests a valid case where three points form a 90-degree angle (PI/2).
     * With EPSILON = 0.5, the valid range is [PI-0.5, PI+0.5].
     * PI/2 (~1.57) is outside this range, so it should return true.
     */
    @Test
    public void testLic2Positive() {
        Globals.PARAMETERS.EPSILON = 0.5;
        Globals.NUMPOINTS = 3;
        Globals.POINTS[0] = new Point(1, 0);
        Globals.POINTS[1] = new Point(0, 0);
        Globals.POINTS[2] = new Point(0, 1);

        assertTrue(Lic2.evaluate(), "Angle of PI/2 should satisfy condition when EPSILON is 0.5");
    }
    /**
     * Tests the edge case where the vertex coincides with one of the
     * other points. According to requirements, the angle is undefined
     * and the condition should not be met.
     */
    @Test
    public void testLic2NegativeUndefined() {
        Globals.PARAMETERS.EPSILON = 0.1;
        Globals.NUMPOINTS = 3;
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(0, 0);
        Globals.POINTS[2] = new Point(0, 1);

        assertFalse(Lic2.evaluate(), "Undefined angle should not satisfy LIC 2");
    }
    /**
     * Tests a case where points form a straight line (PI radians).
     * PI is exactly at the center of the excluded range, so it
     * should return false.
     */
    @Test
    public void testLic2NegativeInRange() {
        Globals.PARAMETERS.EPSILON = 0.1;
        Globals.NUMPOINTS = 3;
        Globals.POINTS[0] = new Point(1, 0);
        Globals.POINTS[1] = new Point(0, 0);
        Globals.POINTS[2] = new Point(-1, 0);

        assertFalse(Lic2.evaluate(), "A straight line should not satisfy LIC 2");
    }
}