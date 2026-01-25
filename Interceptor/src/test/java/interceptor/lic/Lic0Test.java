package interceptor.lic;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import interceptor.model.Globals;
import interceptor.model.Parameters;
import /*Interceptor.src.main.java.*/interceptor.model.Point;

public class Lic0Test {

    @BeforeEach
    void setUp() {
        Globals.NUMPOINTS = 3;
        Globals.POINTS = new Point[Globals.NUMPOINTS];
    }

    @Test
    void testEvaluateConditionMet() {
        // Set points so that distance exceeds Parameters.LENGTH1
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(5, 0);
        Globals.POINTS[2] = new Point(0, 0);

        Parameters.LENGTH1 = 3; // any distance less than 5 triggers condition

        boolean result = Lic0.evaluate();

        assertTrue(result, "Expected condition to be met (distance > LENGTH1)");
    }
    
}
