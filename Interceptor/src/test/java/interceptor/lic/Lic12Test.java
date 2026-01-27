package interceptor.lic;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import interceptor.model.Globals;
import interceptor.model.Parameters;
import interceptor.model.Point;

public class Lic12Test {
    @BeforeEach
    void setUp() {
        Globals.NUMPOINTS = 5;
        Globals.POINTS = new Point[Globals.NUMPOINTS];
        Parameters.LENGTH1 = 5;
        Parameters.LENGTH2 = 0.5;
        Parameters.K_PTS = 3;
    }

    /**
     * Tests invalid LENGTH1.
     * Contract: LENGTH1 is set to -1, which is not accepted (LENGTH1 must be non-negative).
     * Oracle: evaluate() returns IllegalStateException.
     */
    @Test
    void testInvalidLength1() {
        Parameters.LENGTH1 = -1;

        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(5, 0);

        assertThrows(IllegalStateException.class, () -> {Lic12.evaluate();});
    }

    /**
     * Tests invalid LENGTH2.
     * Contract: LENGTH2 is set to -1, which is not accepted (LENGTH2 must be non-negative).
     * Oracle: evaluate() returns IllegalStateException.
     */
    @Test
    void testInvalidLength2() {
        Parameters.LENGTH2 = -1;

        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(5, 0);

        assertThrows(IllegalStateException.class, () -> {Lic12.evaluate();});
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

        assertThrows(IllegalStateException.class, () -> {Lic12.evaluate();});
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

        assertThrows(IllegalStateException.class, () -> {Lic12.evaluate();});
    }

    /**
     * Tests larger point distance than LENGTH1 and LENGTH2 between consecutive intervening points.
     * Contract: Consecutive intervening points (0,6) and (6,0) are at a distance larger than LENGTH1. 
     * All points are at a distance larger than LENGTH2.
     * LENGTH1 is set to 5.
     * LENGTH2 is set to 0.5
     * K_PTS is set to 3.
     * Oracle: evaluate() returns false.
     */
    @Test
    void testEvaluateDistanceLargerThanLength1Only() {
        Globals.POINTS[0] = new Point(0, 6);
        Globals.POINTS[1] = new Point(1, 1);
        Globals.POINTS[2] = new Point(3, 0); 
        Globals.POINTS[3] = new Point(6, 0);
        Globals.POINTS[4] = new Point(3, 2);

        boolean result = Lic12.evaluate();

        assertFalse(result, "Expected a point distance to be larger than LENGTH1 and larger than LENGTH2");
    }

    /**
     * Tests smaller point distance than LENGTH1 and LENGTH2 between consecutive intervening points.
     * Contract: Consecutive intervening points (0,0) and (0.3,0.2) are at a distance smaller than LENGTH2. 
     * All points are at a distance smaller than LENGTH1.
     * LENGTH1 is set to 5.
     * LENGTH2 is set to 0.5
     * K_PTS is set to 3.
     * Oracle: evaluate() returns false.
     */
    @Test
    void testEvaluateDistanceSmallerThanLength2Only() {
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(1, 1);
        Globals.POINTS[2] = new Point(3, 3); 
        Globals.POINTS[3] = new Point(0.3, 0.2);
        Globals.POINTS[4] = new Point(4, 0);

        boolean result = Lic12.evaluate();

        assertFalse(result, "Expected point distance to be smaller than LENGTH1 and smaller than LENGTH2)");
    }

    /**
     * Tests larger point distance than LENGTH1 and equal to LENGTH2 between consecutive intervening points.
     * Contract: Consecutive intervening points (0,0) and (0.5,0) are at a distance equal to LENGTH2. 
     * Consecutive intervening points (0,6) and (6,0) are at a distance larger than LENGTH1.
     * LENGTH1 is set to 5.
     * LENGTH2 is set to 0.5
     * K_PTS is set to 3.
     * Oracle: evaluate() returns false.
     */
    @Test
    void testEvaluateDistanceEqualToLength2() {
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(0, 6);
        Globals.POINTS[2] = new Point(3, 3); 
        Globals.POINTS[3] = new Point(0.5, 0);
        Globals.POINTS[4] = new Point(6, 0);

        boolean result = Lic12.evaluate();

        assertFalse(result, "Expected point distance to be the same as LENGTH2");
    }

    /**
     * Tests larger point distance than LENGTH1 and larger than LENGTH2 
     * between non-consecutive intervening points.
     * Contract: Non-consecutive intervening points (0,0) and (0.3,0.3) are at a distance 
     * smaller than LENGTH2. Consecutive intervening points (0,6) and (6,0) are at a distance 
     * larger than LENGTH1.
     * LENGTH1 is set to 5.
     * LENGTH2 is set to 0.5
     * K_PTS is set to 3.
     * Oracle: evaluate() returns false.
     */
    @Test
    void testEvaluateNonConsecutiveDistanceLargerThanLength() {
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(6, 1);
        Globals.POINTS[2] = new Point(0.3, 0.3);
        Globals.POINTS[3] = new Point(3, 2);
        Globals.POINTS[4] = new Point(4, 0); 

        boolean result = Lic12.evaluate();

        assertFalse(result, "Expected point distance to be larger than LENGTH2");
    }

    /**
     * Tests larger point distance than LENGTH1 and smaller than LENGTH2 
     * between different consecutive intervening points.
     * Contract: Consecutive intervening points (1,1) and (1,1.3) are at a distance 
     * smaller than LENGTH2. Consecutive intervening points (0,6) and (6,0) are at a distance 
     * larger than LENGTH1.
     * LENGTH1 is set to 5.
     * LENGTH2 is set to 0.5
     * K_PTS is set to 3.
     * Oracle: evaluate() returns true.
     */
    @Test
    void testEvaluateDifferentDistanceLargerThanLength1andSmallerThanLength2() {
        Globals.POINTS[0] = new Point(0, 6);
        Globals.POINTS[1] = new Point(1, 1);
        Globals.POINTS[2] = new Point(3, 0); 
        Globals.POINTS[3] = new Point(6, 0);
        Globals.POINTS[4] = new Point(1, 1.3);

        boolean result = Lic12.evaluate();

        assertTrue(result, "Expected point distance to be larger than LENGTH1 and smaller than LENGTH2");
    }

    /**
     * Tests larger point distance than LENGTH1 and smaller than LENGTH2 
     * between the same consecutive intervening points.
     * Contract: Consecutive intervening points (0,6) and (6,0) are at a distance 
     * larger than LENGTH1 and smaller than LENGTH2.
     * LENGTH1 is set to 5.
     * LENGTH2 is set to (LENGTH1 + 5) = 10
     * K_PTS is set to 3.
     * Oracle: evaluate() returns true.
     */
    @Test
    void testEvaluateSameDistanceLargerThanLength1andSmallerThanLength2() {
        Parameters.LENGTH2 = Parameters.LENGTH1 + 5;
        
        Globals.POINTS[0] = new Point(0, 6);
        Globals.POINTS[1] = new Point(10, 10);
        Globals.POINTS[2] = new Point(30, 0); 
        Globals.POINTS[3] = new Point(6, 0);
        Globals.POINTS[4] = new Point(20, 25);

        boolean result = Lic12.evaluate();

        assertTrue(result, "Expected point distance to be larger than LENGTH1 and smaller than LENGTH2");
    }
}
