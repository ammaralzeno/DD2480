package interceptor.lic;

import interceptor.model.Point;
import interceptor.model.Globals;
import interceptor.model.Parameters;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Lic4Test {

    @Test
    public void testLic4Positive() {
        // Points in quadrants I, II, III, IV 
        
        Globals.POINTS[0] = new Point(1, 1);
        Globals.POINTS[1] = new Point(-1, 1);
        Globals.POINTS[2] = new Point(0, -1);
        Globals.POINTS[3] = new Point(1, -1);

        Globals.PARAMETERS.Q_PTS = 3;
        Globals.PARAMETERS.QUADS = 2;
         
        assertTrue(Lic4.evaluate());
    }

    @Test
    public void testLic4Negative() {
        // All points in quadrants I and II only
        Globals.POINTS[0] = new Point(1, 1);
        Globals.POINTS[1] = new Point(-1, 1);
        Globals.POINTS[2] = new Point(2, 2);
        Globals.POINTS[3] = new Point(-2, 3);

        Globals.PARAMETERS.Q_PTS = 3;
        Globals.PARAMETERS.QUADS = 2;
        
        assertFalse(Lic4.evaluate());
    }

}
