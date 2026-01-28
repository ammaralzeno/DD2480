package interceptor.lic;

import interceptor.model.Globals;
import interceptor.model.Parameters;
import interceptor.model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class Lic1Test {


    @BeforeEach
    public void setUp() {
        Globals.PARAMETERS = new Parameters();
        Globals.POINTS = new Point[100];
    }

    /**
     * Tests insufficient points.
     * Contract: NUMPOINTS is set to 2, which is less than the minimum 3 required points.
     * Oracle: evaluate() returns false.
     */
    @Test
    public void testInsufficientPoints() {
        Globals.NUMPOINTS = 2;
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(1, 1);
        Globals.PARAMETERS.RADIUS1 = 1.0;


        assertFalse(Lic1.evaluate());
    }


    /**
     * Tests a positive acute triangle.
     * Contract: Points form an equilateral triangle with side length 10.
     * The circumradius is approx. 5.77. RADIUS1 is set to 5.0.
     * Oracle: evaluate() returns true because 5.77 > 5.0.
     */
    @Test
    public void testPositiveAcuteTriangle() {
        Globals.NUMPOINTS = 3;
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(10, 0);
        Globals.POINTS[2] = new Point(5, 5 * Math.sqrt(3));
       
        Globals.PARAMETERS.RADIUS1 = 5.0;


        assertTrue(Lic1.evaluate());
    }


    /**
     * Tests a negative acute triangle.
     * Contract: Points form an equilateral triangle with side length 1.
     * The circumradius is approx. 0.577. RADIUS1 is set to 1.0.
     * Oracle: evaluate() returns false because 0.577 <= 1.0.
     */
    @Test
    public void testNegativeAcuteTriangle() {
        Globals.NUMPOINTS = 3;
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(1, 0);
        Globals.POINTS[2] = new Point(0.5, 0.5 * Math.sqrt(3));
       
        Globals.PARAMETERS.RADIUS1 = 1.0;


        assertFalse(Lic1.evaluate());
    }


    /**
     * Tests a positive obtuse triangle.
     * Contract: Points (-3,0), (0,1), (3,0) form an obtuse triangle.
     * The Minimum Enclosing Circle (MEC) has a radius of 3.0.
     * RADIUS1 is set to 2.5.
     * Oracle: evaluate() returns true because 3.0 > 2.5.
     */
    @Test
    public void testPositiveObtuseTriangle() {
        Globals.NUMPOINTS = 3;
        Globals.POINTS[0] = new Point(-3, 0);
        Globals.POINTS[1] = new Point(0, 1);
        Globals.POINTS[2] = new Point(3, 0);
       
        Globals.PARAMETERS.RADIUS1 = 2.5;


        assertTrue(Lic1.evaluate());
    }


    /**
     * Tests a negative obtuse triangle.
     * Contract: Points (-3,0), (0,1), (3,0) form an obtuse triangle.
     * Oracle: evaluate() returns false because 3.0 <= 3.5.
     */
    @Test
    public void testNegativeObtuseTriangle() {
        Globals.NUMPOINTS = 3;
        Globals.POINTS[0] = new Point(-3, 0);
        Globals.POINTS[1] = new Point(0, 1);
        Globals.POINTS[2] = new Point(3, 0);
       
        Globals.PARAMETERS.RADIUS1 = 3.5;


        assertFalse(Lic1.evaluate());
    }


    /**
     * Tests collinear points.
     * Contract: Points (0,0), (2,0), (4,0) are collinear.
     * The MEC radius is 2.0 (half the distance between endpoints).
     * Case 1: RADIUS1 = 1.5.
     * Case 2: RADIUS1 = 2.5.
     * Oracle: evaluate() returns true for Case 1 (2.0 > 1.5) and false for Case 2 (2.0 <= 2.5).
     */
    @Test
    public void testCollinearPoints() {
        Globals.NUMPOINTS = 3;
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(2, 0);
        Globals.POINTS[2] = new Point(4, 0);
       
        Globals.PARAMETERS.RADIUS1 = 1.5;
        assertTrue(Lic1.evaluate());
       
        Globals.PARAMETERS.RADIUS1 = 2.5;
        assertFalse(Lic1.evaluate());
    }


    /**
     * Tests boundary condition.
     * Contract: Points form a triangle with a specific circumradius r.
     * Case 1: RADIUS1 is exactly r.
     * Case 2: RADIUS1 is slightly less than r (r - 0.0001).
     * Oracle: evaluate() returns false for Case 1 (condition is strictly greater)
     * and true for Case 2.
     */
    @Test
    public void testBoundaryCondition() {
        double r = 10.0 / Math.sqrt(3.0);
       
        Globals.NUMPOINTS = 3;
        Globals.POINTS[0] = new Point(0, 0);
        Globals.POINTS[1] = new Point(10, 0);
        Globals.POINTS[2] = new Point(5, 5 * Math.sqrt(3));
       
        Globals.PARAMETERS.RADIUS1 = r;
        assertFalse(Lic1.evaluate());
       
        Globals.PARAMETERS.RADIUS1 = r - 0.0001;
        assertTrue(Lic1.evaluate());
    }
}
