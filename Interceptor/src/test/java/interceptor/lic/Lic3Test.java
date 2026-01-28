package interceptor.lic;

import interceptor.model.Globals;
import interceptor.model.Parameters;
import interceptor.model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Lic3Test {

    @BeforeEach
    public void setUp() {
        Globals.PARAMETERS = new Parameters();
        Globals.POINTS = new Point[100];
    }

    /**
     * Contract: NUMPOINTS < 3.
     * Oracle: evaluate() returns false.
     */
    @Test
    public void testInsufficientPointsFalse() {
        Globals.NUMPOINTS = 2;
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(1, 1);
        Globals.PARAMETERS.AREA1 = 0.0;

        assertFalse(Lic3.evaluate());
    }

    /**
     * Contract: Three consecutive points form a triangle with area 1.0, AREA1 = 0.5.
     * Oracle: evaluate() returns true because 1.0 > 0.5.
     */
    @Test
    public void testAreaGreaterThanArea1True() {
        Globals.NUMPOINTS = 3;
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(2, 0);
        Globals.POINTS[2] = new Point(0, 1); // area = 1.0
        Globals.PARAMETERS.AREA1 = 0.5;

        assertTrue(Lic3.evaluate());
    }

    /**
     * Contract: Triangle area is exactly 1.0, AREA1 = 1.0.
     * Oracle: evaluate() returns false because the condition is strictly greater than AREA1.
     */
    @Test
    public void testBoundaryAreaEqualToArea1False() {
        Globals.NUMPOINTS = 3;
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(2, 0);
        Globals.POINTS[2] = new Point(0, 1); // area = 1.0
        Globals.PARAMETERS.AREA1 = 1.0;

        assertFalse(Lic3.evaluate());
    }

    /**
     * Contract: First triple is collinear (area 0 => false), second triple has area > AREA1 (=> true).
     * Oracle: evaluate() returns true.
     */
    @Test
    public void testFindsLaterTripleTrue() {
        Globals.NUMPOINTS = 4;

        // Triple (0,1,2): collinear => area 0
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(1, 0);
        Globals.POINTS[2] = new Point(2, 0);

        // Triple (1,2,3): forms triangle with area 0.5
        Globals.POINTS[3] = new Point(1, 1);

        Globals.PARAMETERS.AREA1 = 0.1;

        assertTrue(Lic3.evaluate());
    }
}
