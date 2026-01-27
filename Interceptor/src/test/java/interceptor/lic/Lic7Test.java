package interceptor.lic;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import interceptor.model.Globals;
import interceptor.model.Parameters;
import interceptor.model.Point;

class Lic7Test {
    @BeforeEach
    void setUp() {
        Globals.NUMPOINTS = 5;
        Globals.POINTS = new Point[Globals.NUMPOINTS];
        Parameters.LENGTH1 = 5;
        Parameters.K_PTS = 3;
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

        assertThrows(IllegalStateException.class, () -> {Lic7.evaluate();});
    }

    /**
     * Tests invalid K_PTS.
     * Contract: K_PTS is set to 0, which is less than the minimum required value.
     * Oracle: evaluate() returns IllegalStateException.
     */
    @Test
    void testInvalidKpts() {
        Parameters.K_PTS = 0;

        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(5, 0);

        assertThrows(IllegalStateException.class, () -> {Lic7.evaluate();});
    }

    /**
     * Tests too large K_PTS.
     * Contract: K_PTS is set to (NUMPOINTS-1), which is more than the maximum required value (NUMPOINTS-2).
     * Oracle: evaluate() returns IllegalStateException.
     */
    @Test
    void testTooLargeKpts() {
        Parameters.K_PTS = Globals.NUMPOINTS - 1;

        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(5, 0);

        assertThrows(IllegalStateException.class, () -> {Lic7.evaluate();});
    }

    /**
     * Tests larger point distance than LENGTH1 between consecutive intervening points.
     * Contract: Consecutive intervening points (0,6) and (6,0) are at a distance larger than LENGTH1.
     * LENGTH1 is set to 5.
     * K_PTS is set to 3.
     * Oracle: evaluate() returns true.
     */
    @Test
    void testEvaluateDistanceLargerThanLength() {
        Globals.POINTS[0] = new Point(0, 6);
        Globals.POINTS[1] = new Point(1, 1);
        Globals.POINTS[2] = new Point(3, 0); 
        Globals.POINTS[3] = new Point(6, 0);
        Globals.POINTS[4] = new Point(3, 2);

        boolean result = Lic7.evaluate();

        assertTrue(result, "Expected point distance to be larger than LENGTH1");
    }

    /**
     * Tests smaller point distance than LENGTH1 between consecutive intervening points.
     * Contract: Consecutive intervening points (0,0), (3,2) and (1,1), (4,0) respectively 
     * are at a distance smaller than LENGTH1.
     * LENGTH1 is set to 5.
     * K_PTS is set to 3.
     * Oracle: evaluate() returns false.
     */
    @Test
    void testEvaluateDistanceSmallerThanLength() {
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(1, 1);
        Globals.POINTS[2] = new Point(3, 3); 
        Globals.POINTS[3] = new Point(3, 2);
        Globals.POINTS[4] = new Point(4, 0);

        boolean result = Lic7.evaluate();

        assertFalse(result, "Expected point distance to be smaller than LENGTH1");
    }

    /**
     * Tests point distance equal to LENGTH1 between consecutive intervening points.
     * Contract: Consecutive intervening points (0,0), (3,0) and (2,1), (5,0) respectively 
     * are at most at a distance equal to LENGTH1.
     * LENGTH1 is set to 5.
     * K_PTS is set to 3.
     * Oracle: evaluate() returns false.
     */
    @Test
    void testEvaluateDistanceEqualToLength() {
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(2, 1);
        Globals.POINTS[2] = new Point(3, 3); 
        Globals.POINTS[3] = new Point(3, 0);
        Globals.POINTS[4] = new Point(5, 0);

        boolean result = Lic7.evaluate();

        assertFalse(result, "Expected point distance to be the same as LENGTH1");
    }

    /**
     * Tests larger point distance than LENGTH1 between non-consecutive intervening points.
     * Contract: Non-consecutive intervening points (0,0) and (6,1) are at a distance larger than LENGTH1. 
     * Remaining consecutive intervening points are at a smaller distance than LENGTH1.
     * LENGTH1 is set to 5.
     * K_PTS is set to 3.
     * Oracle: evaluate() returns false.
     */
    @Test
    void testEvaluateNonConsecutiveDistanceLargerThanLength() {
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(6, 1);
        Globals.POINTS[2] = new Point(5, 3);
        Globals.POINTS[3] = new Point(3, 2);
        Globals.POINTS[4] = new Point(4, 0); 

        boolean result = Lic7.evaluate();

        assertFalse(result, "Expected point distance to be smaller than LENGTH1");
    }

}
