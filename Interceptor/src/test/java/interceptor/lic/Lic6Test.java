package interceptor.lic;

import interceptor.model.Globals;
import interceptor.model.Parameters;
import interceptor.model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Lic6Test {

    @Test
    public void testLic6Positive() {
        Globals.PARAMETERS = new Parameters();
        Globals.PARAMETERS.N_PTS = 3;
        Globals.PARAMETERS.DIST = 0.5;
        Globals.NUMPOINTS = 3;
        Globals.POINTS = new Point[]{
                new Point(0, 0),
                new Point(0, 1), // 1 unit away from line y=0
                new Point(1, 0)
        };
        assertTrue(Lic6.evaluate());
    }
}
