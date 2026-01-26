package interceptor.lic;

import interceptor.model.Point;
import interceptor.model.Globals;
import interceptor.model.Parameters;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Lic5Test {

    @Test
    public void testLic5Positive() {
        
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(2, 2);
        Globals.POINTS[2] = new Point(1, 5);
        Globals.POINTS[3] = new Point(3, 3);

        assertTrue(Lic5.evaluate());
    }

    @Test
    public void testLic5Positive2() {

        Globals.POINTS[0] = new Point(5, 0);
        Globals.POINTS[1] = new Point(4, 1);

        assertTrue(Lic5.evaluate());
    }

    @Test
    public void testLic5Negative() {

        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(1, 1);
        Globals.POINTS[2] = new Point(2, 2);
        Globals.POINTS[3] = new Point(3, 3);

        assertFalse(Lic5.evaluate());
    }
}
