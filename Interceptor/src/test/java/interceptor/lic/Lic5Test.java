package interceptor.lic;

import interceptor.model.Point;
import interceptor.model.Globals;
import interceptor.model.Parameters;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class Lic5Test {

    @BeforeEach
    public void setUp() {
        Globals.PARAMETERS = new Parameters();
        Globals.POINTS = new Point[100];
    }

    /**
     * Tests case where all conditions are met.
     * 
     * Contract: There exists a pair of consecutive data points (X[i],Y[i]) and (X[j],Y[j])
     * X[j] - X[i] < 0
     * i = j-1
     * 
     * Oracle: evaluate() returns true.
     * 
     */
    @Test
    public void testLic5Positive() {
        
        Globals.NUMPOINTS = 4;

        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(2, 2);
        Globals.POINTS[2] = new Point(1, 5);
        Globals.POINTS[3] = new Point(3, 3);

        assertTrue(Lic5.evaluate());
    }

    /**
     * Tests case where there exists no consecutive pair of points which meet the condition.
     * 
     * Contract: X[j] - X[i] > 0 for all consecutive pairs.
     * 
     * Oracle: evaluate() returns false.
     * 
     */
    @Test
    public void testLic5Negative() {

        Globals.NUMPOINTS = 4;

        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(1, 1);
        Globals.POINTS[2] = new Point(2, 2);
        Globals.POINTS[3] = new Point(3, 3);

        assertFalse(Lic5.evaluate());
    }
}
