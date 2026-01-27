package interceptor.lic;

import interceptor.model.Globals;
import interceptor.model.Parameters;
import interceptor.model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Lic8Test {

    @BeforeEach
    void setUp() {
        Globals.PARAMETERS = new Parameters();
    }

    /**
     * Tests insufficient points.
     * Contract: NUMPOINTS is 4, which is less than the minimum 5 required points.
     * Oracle: evaluate() returns false.
     */
    @Test
    void testInsufficientPoints() {
        Globals.NUMPOINTS = 4;
        Globals.POINTS = new Point[]{
            new Point(0, 0), new Point(1, 1), new Point(2, 2), new Point(3, 3)
        };
        Globals.PARAMETERS.A_PTS = 1;
        Globals.PARAMETERS.B_PTS = 1;
        Globals.PARAMETERS.RADIUS1 = 10.0;

        assertFalse(Lic8.evaluate());
    }

    /**
     * Tests a positive case where the condition is met.
     * Contract: Points at indices 0, 2, 4 (with A_PTS=1, B_PTS=1) form a 3-4-5 right triangle.
     * The MEC radius is 2.5. RADIUS1 is set to 2.0.
     * Oracle: evaluate() returns true because 2.5 > 2.0.
     */
    @Test
    void testConditionMet() {
        Globals.NUMPOINTS = 5;
        Globals.POINTS = new Point[]{
            new Point(0, 0),
            new Point(0, 0),
            new Point(3, 0),
            new Point(0, 0),
            new Point(0, 4)
        };
        Globals.PARAMETERS.A_PTS = 1;
        Globals.PARAMETERS.B_PTS = 1;
        Globals.PARAMETERS.RADIUS1 = 2.0;

        assertTrue(Lic8.evaluate());
    }

    /**
     * Tests a negative case where the condition is not met.
     * Contract: Points at indices 0, 2, 4 form a small triangle with MEC radius approx 0.70.
     * RADIUS1 is set to 1.0.
     * Oracle: evaluate() returns false because 0.70 <= 1.0.
     */
    @Test
    void testConditionNotMet() {
        Globals.NUMPOINTS = 5;
        Globals.POINTS = new Point[]{
            new Point(0, 0),
            new Point(100, 100),
            new Point(1, 0),
            new Point(100, 100),
            new Point(0, 1)
        };
        Globals.PARAMETERS.A_PTS = 1;
        Globals.PARAMETERS.B_PTS = 1;
        Globals.PARAMETERS.RADIUS1 = 1.0;

        assertFalse(Lic8.evaluate());
    }

    /**
     * Tests the strictness of the gap logic.
     * Contract: Ensures that the algorithm strictly checks points separated by A_PTS and B_PTS,
     * and ignores other points even if they form valid triangles.
     * The set {0, 2, 4} forms a small triangle, while intervening points
     * might form large triangles with the checked points.
     * Oracle: evaluate() returns false.
     */
    @Test
    void testGapLogicStrictness() {
        Globals.NUMPOINTS = 5;
        Globals.POINTS = new Point[]{
            new Point(0, 0),
            new Point(100, 0),
            new Point(1, 0),
            new Point(0, 100),
            new Point(0, 1)
        };
        
        Globals.PARAMETERS.A_PTS = 1;
        Globals.PARAMETERS.B_PTS = 1;
        Globals.PARAMETERS.RADIUS1 = 1.0; 

        assertFalse(Lic8.evaluate());
    }
    
    /**
     * Tests invalid parameters where the gaps are too large for the number of points.
     * Contract: A_PTS + B_PTS > NUMPOINTS - 3.
     * Oracle: evaluate() returns false.
     */
    @Test
    void testInvalidParameters() {
        Globals.NUMPOINTS = 5;
        Globals.POINTS = new Point[]{
             new Point(0,0), new Point(0,0), new Point(0,0), new Point(0,0), new Point(0,0)
        };
        Globals.PARAMETERS.A_PTS = 2;
        Globals.PARAMETERS.B_PTS = 1; 
        
        assertFalse(Lic8.evaluate());
    }
}
