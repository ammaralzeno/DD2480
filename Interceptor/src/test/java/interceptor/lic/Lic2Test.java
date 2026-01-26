package interceptor.lic;

import interceptor.model.Globals;
import interceptor.model.Parameters;
import interceptor.model.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Lic2Test {

    @Test
    public void testLic2Positive() {
        // Setup: A right angle (PI/2). PI/2 is < (PI - 0.5)
        Globals.PARAMETERS.EPSILON = 0.5;
        Globals.NUMPOINTS = 3;
        Globals.POINTS = new Point[]{
                new Point(1, 0),
                new Point(0, 0), // Vertex
                new Point(0, 1)
        };

        assertTrue(Lic2.evaluate(), "Angle of PI/2 should satisfy condition when EPSILON is 0.5");
    }

    @Test
    public void testLic2NegativeUndefined() {
        // Setup: Point coincides with vertex
        Globals.PARAMETERS.EPSILON = 0.1;
        Globals.NUMPOINTS = 3;
        Globals.POINTS = new Point[]{
                new Point(0, 0),
                new Point(0, 0), // Vertex coincides with P1
                new Point(0, 1)
        };

        assertFalse(Lic2.evaluate(), "Undefined angle should not satisfy LIC 2");
    }
}