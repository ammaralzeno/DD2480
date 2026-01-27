package interceptor.lic;

import interceptor.model.Point;
import interceptor.model.Globals;
import interceptor.model.Parameters;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class Lic11Test {

    @BeforeEach
    public void setUp() {
        Globals.PARAMETERS = new Parameters();
        Globals.POINTS = new Point[100];
    }

    /**
     * Tests case where all conditions are met.
     * 
     * Contract: There exists a pair of points (X[i],Y[i]) and (X[j],Y[j])
     * (X[i],Y[i]) and (X[j],Y[j]) are separated by exactly G_PTS consecutive intervening points
     * X[j] - X[i] < 0
     * i < j
     * NUMPOINTS < 3
     * 1 ≤ G_PTS ≤ NUMPOINTS − 2
     * 
     * Oracle: evaluate() returns true.
     * 
     */
    @Test
    public void testLic11Positive() {

        Globals.NUMPOINTS = 4;
        
        Globals.POINTS[0] = new Point(3, 3);
        Globals.POINTS[1] = new Point(2, 2);
        Globals.POINTS[2] = new Point(1, 5);
        Globals.POINTS[3] = new Point(0, 0);

        Globals.PARAMETERS.G_PTS = 2;

        assertTrue(Lic11.evaluate());
    }

    /**
     * Tests case where there exists no pair of points fulfill the conditions.
     * 
     * Contract: X[j] - X[i] ≥ 0 for all pairs separated by G_PTS consecutive intervening points.
     * 
     * Oracle: evaluate() returns false.
     * 
     */
    @Test
    public void testLic11Negative() {

        Globals.NUMPOINTS = 4;

        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(2, 2);
        Globals.POINTS[2] = new Point(1, 5);
        Globals.POINTS[3] = new Point(3, 3);

        Globals.PARAMETERS.G_PTS = 2;

        assertFalse(Lic11.evaluate());
    }

}
