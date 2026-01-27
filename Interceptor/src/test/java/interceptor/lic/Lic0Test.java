package interceptor.lic;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import interceptor.model.Globals;
import interceptor.model.Parameters;
import interceptor.model.Point;

class Lic0Test {

    @BeforeEach
    void setUp() {
        Globals.NUMPOINTS = 3;
        Globals.POINTS = new Point[Globals.NUMPOINTS];
        Parameters.LENGTH1 = 5;
    }

    /**
     * Tests invalid LENGTH1.
     * Contract: LENGTH1 is set to -1, which is not accepted (LENGTH1 must be non-negative).
     * Oracle: evaluate() returns IllegalStateException.
     */
    @Test
    void testInvalidLength() {
        Parameters.LENGTH1 = -1;

        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(5, 0);

        //assertFalse(result, "Expected condition to be met (distance > LENGTH1)");
        assertThrows(IllegalStateException.class, () -> {Lic0.evaluate();});
    }

    /**
     * Tests larger point distance than LENGTH1.
     * Contract: Consecutive points (6,0) and (0,6) are at a distance larger than LENGTH1.
     * LENGTH1 is set to 5.
     * Oracle: evaluate() returns true.
     */
    @Test
    void testEvaluateDistanceLargerThanLength() {
        Globals.POINTS[0] = new Point(3, 0); 
        Globals.POINTS[1] = new Point(6, 0);
        Globals.POINTS[2] = new Point(0, 6);

        boolean result = Lic0.evaluate();

        assertTrue(result, "Expected point distance to be larger than LENGTH1)");
    }

    /**
     * Tests smaller point distance than LENGTH1.
     * Contract: Consecutive points (0,0), (1,1) and (3,3) are at a distance smaller than LENGTH1.
     * LENGTH1 is set to 5.
     * Oracle: evaluate() returns false.
     */
    @Test
    void testEvaluateDistanceSmallerThanLength() {
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(1, 1);
        Globals.POINTS[2] = new Point(3, 3); 

        boolean result = Lic0.evaluate();

        assertFalse(result, "Expected point distance to be smaller than LENGTH1)");
    }

    /**
     * Tests point distance equal to LENGTH1.
     * Contract: Consecutive points (0,0), (5,0) and (3,3) are at most at a distance
     * equal to LENGTH1.
     * LENGTH1 is set to 5.
     * Oracle: evaluate() returns false.
     */
    @Test
    void testEvaluateDistanceEqualToLength() {
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(5, 0);
        Globals.POINTS[2] = new Point(3, 3); 

        boolean result = Lic0.evaluate();

        assertFalse(result, "Expected point distance to be the same as LENGTH1)");
    }

    /**
     * Tests larger point distance than LENGTH1 between non-consecutive points.
     * Contract: Non-consecutive points (0,0) and (6,1) are at a distance larger than LENGTH1,
     * but distance between consecutive points (0,0), (1,1) and (6,1) is smaller than LENGTH1.
     * LENGTH1 is set to 5.
     * Oracle: evaluate() returns false.
     */
    @Test
    void testEvaluateNonConsecutiveDistanceLargerThanLength() {
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(1, 1);
        Globals.POINTS[2] = new Point(6, 1); 

        boolean result = Lic0.evaluate();

        assertFalse(result, "Expected point distance to be smaller than LENGTH1)");
    }
    
}
