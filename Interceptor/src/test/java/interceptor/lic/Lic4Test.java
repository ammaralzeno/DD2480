package interceptor.lic;

import interceptor.model.Point;
import interceptor.model.Globals;
import interceptor.model.Parameters;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class Lic4Test {

    @BeforeEach
    public void setUp() {
        Globals.PARAMETERS = new Parameters();
        Globals.POINTS = new Point[100];
    }

    /**
     * Tests case where all conditions are met.
     * 
     * Contract: There exists a set of Q_PTS consecutive data points that lie in more than QUADS quadrants.
     * 1 ≤ QUADS ≤ 3
     * 2 ≤ Q_PTS ≤ NUMPOINTS
     * 
     * Oracle: evaluate() returns true.
     * 
     */
    @Test
    public void testLic4Positive() {

        Globals.NUMPOINTS = 4;
        
        Globals.POINTS[0] = new Point(1, 1);
        Globals.POINTS[1] = new Point(-1, 1);
        Globals.POINTS[2] = new Point(0, -1);
        Globals.POINTS[3] = new Point(1, -1);

        Globals.PARAMETERS.Q_PTS = 3;
        Globals.PARAMETERS.QUADS = 2;
         
        assertTrue(Lic4.evaluate());
    }

    /**
     * Tests insufficient number of distinct quadrants.
     * 
     * Contract: There exists no set of Q_PTS consecutive data points that lie in more than QUADS quadrants.
     * 
     * Oracle: evaluate() returns false.
     * 
     */
    @Test
    public void testLic4Negative() {

        Globals.NUMPOINTS = 4;

        Globals.POINTS[0] = new Point(1, 1);
        Globals.POINTS[1] = new Point(-1, 1);
        Globals.POINTS[2] = new Point(2, 2);
        Globals.POINTS[3] = new Point(-2, 3);

        Globals.PARAMETERS.Q_PTS = 3;
        Globals.PARAMETERS.QUADS = 2;
        
        assertFalse(Lic4.evaluate());
    }

}
