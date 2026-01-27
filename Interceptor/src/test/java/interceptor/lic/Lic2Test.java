package interceptor.lic;

import interceptor.model.Globals;
import interceptor.model.Parameters;
import interceptor.model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Lic2Test {

    @BeforeEach
    public void setup() {

        Globals.PARAMETERS = new Parameters();


        Globals.POINTS = new Point[100];
        Globals.NUMPOINTS = 0;
    }

    @Test
    public void testLic2Positive() {
        Globals.PARAMETERS.EPSILON = 0.5;
        Globals.NUMPOINTS = 3;
        Globals.POINTS[0] = new Point(1, 0);
        Globals.POINTS[1] = new Point(0, 0);
        Globals.POINTS[2] = new Point(0, 1);

        assertTrue(Lic2.evaluate(), "Angle of PI/2 should satisfy condition when EPSILON is 0.5");
    }

    @Test
    public void testLic2NegativeUndefined() {
        Globals.PARAMETERS.EPSILON = 0.1;
        Globals.NUMPOINTS = 3;
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(0, 0);
        Globals.POINTS[2] = new Point(0, 1);

        assertFalse(Lic2.evaluate(), "Undefined angle should not satisfy LIC 2");
    }

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