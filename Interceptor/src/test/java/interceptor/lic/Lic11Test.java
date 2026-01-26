package interceptor.lic;

import interceptor.model.Point;
import interceptor.model.Globals;
import interceptor.model.Parameters;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Lic11Test {

    @Test 
    public void testLic11Positive() {
        
        Globals.POINTS[0] = new Point(3, 3);
        Globals.POINTS[1] = new Point(2, 2);
        Globals.POINTS[2] = new Point(1, 5);
        Globals.POINTS[3] = new Point(0, 0);

        Globals.PARAMETERS.G_PTS = 2;

        assertTrue(Lic11.evaluate());
    }

    @Test
    public void testLic11Negative() {

        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(2, 2);
        Globals.POINTS[2] = new Point(1, 5);
        Globals.POINTS[3] = new Point(3, 3);

        Globals.PARAMETERS.G_PTS = 2;

        assertFalse(Lic11.evaluate());
    }

}
