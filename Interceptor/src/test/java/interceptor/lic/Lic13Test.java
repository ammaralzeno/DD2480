package interceptor.lic;

import interceptor.model.Globals;
import interceptor.model.Parameters;
import interceptor.model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Lic13Test {

    @BeforeEach
    void setUp() {
        Globals.PARAMETERS = new Parameters();
        Globals.POINTS = new Point[100];
        Globals.NUMPOINTS = 0;
    }

    /**
     * Tests insufficient points.
     * Contract: NUMPOINTS < 5.
     * Oracle: evaluate() returns false.
     */
    @Test
    void testInsufficientPoints() {
        Globals.NUMPOINTS = 4;
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(1, 1);
        Globals.POINTS[2] = new Point(2, 2);
        Globals.POINTS[3] = new Point(3, 3);
        
        Globals.PARAMETERS.A_PTS = 1;
        Globals.PARAMETERS.B_PTS = 1;
        Globals.PARAMETERS.RADIUS1 = 1.0;
        Globals.PARAMETERS.RADIUS2 = 10.0;

        assertFalse(Lic13.evaluate());
    }

    /**
     * Tests case where both conditions are met by the same set of points.
     * Contract: Points form a triangle with MEC radius R.
     * RADIUS1 < R <= RADIUS2.
     * This satisfies (R > RADIUS1) AND (R <= RADIUS2).
     * Oracle: evaluate() returns true.
     */
    @Test
    void testBothConditionsMetSameSet() {
        Globals.NUMPOINTS = 5;
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(0, 0);
        Globals.POINTS[2] = new Point(3, 0);
        Globals.POINTS[3] = new Point(0, 0);
        Globals.POINTS[4] = new Point(0, 4);

        Globals.PARAMETERS.A_PTS = 1;
        Globals.PARAMETERS.B_PTS = 1;
        Globals.PARAMETERS.RADIUS1 = 2.0;
        Globals.PARAMETERS.RADIUS2 = 3.0;

        assertTrue(Lic13.evaluate());
    }

    /**
     * Tests case where both conditions are met by differnt sets of points.
     * Contract:
     * Set 1: Large triangle (Radius > RADIUS1).
     * Set 2: Small triangle (Radius <= RADIUS2).
     * Oracle: evaluate() returns true.
     */
    @Test
    void testBothConditionsMetDifferentSets() {
        Globals.NUMPOINTS = 6;
        Globals.PARAMETERS.A_PTS = 1;
        Globals.PARAMETERS.B_PTS = 1;
        Globals.PARAMETERS.RADIUS1 = 2.0; 
        Globals.PARAMETERS.RADIUS2 = 1.0;

        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(0, 0);
        Globals.POINTS[2] = new Point(4, 0);
        Globals.POINTS[3] = new Point(0, 0);
        Globals.POINTS[4] = new Point(0, 3);
        
        Globals.POINTS[1] = new Point(0, 0);
        Globals.POINTS[3] = new Point(1, 0);
        Globals.POINTS[5] = new Point(0, 1);

        assertTrue(Lic13.evaluate());
    }

    /**
     * Tests case where only Condition 1 is met (all sets are large).
     * Contract: All MEC Radii > RADIUS2 (and > RADIUS1).
     * Cond2 (Radius <= RADIUS2) is never met.
     * Oracle: evaluate() returns false.
     */
    @Test
    void testOnlyCondition1Met() {
        Globals.NUMPOINTS = 5;
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(0, 0);
        Globals.POINTS[2] = new Point(3, 0);
        Globals.POINTS[3] = new Point(0, 0);
        Globals.POINTS[4] = new Point(0, 4);

        Globals.PARAMETERS.A_PTS = 1;
        Globals.PARAMETERS.B_PTS = 1;
        Globals.PARAMETERS.RADIUS1 = 1.0;
        Globals.PARAMETERS.RADIUS2 = 1.0;

        assertFalse(Lic13.evaluate());
    }

    /**
     * Tests case where only Condition 2 is met (all sets are small).
     * Contract: All MEC Radii <= RADIUS1 (and <= RADIUS2).
     * Cond1 (Radius > RADIUS1) is never met.
     * Oracle: evaluate() returns false.
     */
    @Test
    void testOnlyCondition2Met() {
        Globals.NUMPOINTS = 5;
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(0, 0);
        Globals.POINTS[2] = new Point(1, 0);
        Globals.POINTS[3] = new Point(0, 0);
        Globals.POINTS[4] = new Point(0, 1);

        Globals.PARAMETERS.A_PTS = 1;
        Globals.PARAMETERS.B_PTS = 1;
        Globals.PARAMETERS.RADIUS1 = 2.0;
        Globals.PARAMETERS.RADIUS2 = 2.0;

        assertFalse(Lic13.evaluate());
    }
}
