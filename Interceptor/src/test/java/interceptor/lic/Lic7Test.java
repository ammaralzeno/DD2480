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

    @Test
    void testInvalidLength() {
        Parameters.LENGTH1 = -1;

        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(5, 0);

        assertThrows(IllegalStateException.class, () -> {Lic7.evaluate();});
    }

    @Test
    void testInvalidKpts() {
        Parameters.K_PTS = 0;

        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(5, 0);

        assertThrows(IllegalStateException.class, () -> {Lic7.evaluate();});
    }

    @Test
    void testTooLargeKpts() {
        Parameters.K_PTS = Globals.NUMPOINTS - 1;

        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(5, 0);

        assertThrows(IllegalStateException.class, () -> {Lic7.evaluate();});
    }

    @Test
    void testEvaluateDistanceLargerThanLength() {
        Globals.POINTS[0] = new Point(0, 6);
        Globals.POINTS[1] = new Point(1, 1);
        Globals.POINTS[2] = new Point(3, 0); 
        Globals.POINTS[3] = new Point(6, 0);
        Globals.POINTS[4] = new Point(3, 2);

        boolean result = Lic7.evaluate();

        assertTrue(result, "Expected point distance to be larger than LENGTH1)");
    }

    @Test
    void testEvaluateDistanceSmallerThanLength() {
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(1, 1);
        Globals.POINTS[2] = new Point(3, 3); 
        Globals.POINTS[3] = new Point(3, 2);
        Globals.POINTS[4] = new Point(4, 0);

        boolean result = Lic7.evaluate();

        assertFalse(result, "Expected point distance to be smaller than LENGTH1)");
    }

    @Test
    void testEvaluateDistanceEqualToLength() {
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(2, 1);
        Globals.POINTS[2] = new Point(3, 3); 
        Globals.POINTS[3] = new Point(3, 2);
        Globals.POINTS[4] = new Point(5, 0);

        boolean result = Lic7.evaluate();

        assertFalse(result, "Expected point distance to be the same as LENGTH1)");
    }

    @Test
    void testEvaluateNonConsecutiveDistanceLargerThanLength() {
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(6, 1);
        Globals.POINTS[2] = new Point(5, 3);
        Globals.POINTS[3] = new Point(3, 2);
        Globals.POINTS[4] = new Point(4, 0); 

        boolean result = Lic7.evaluate();

        assertFalse(result, "Expected point distance to be smaller than LENGTH1)");
    }

}
