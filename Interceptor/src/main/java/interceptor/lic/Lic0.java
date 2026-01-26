package interceptor.lic;

import interceptor.model.Globals; 
import interceptor.model.Parameters;
import interceptor.model.Point;

public final class Lic0 {

    private Lic0() {}

    public static boolean evaluate() {
        boolean result = false;
        for (int i = 0; i < Globals.NUMPOINTS - 1; i++) {
            if (dist(Globals.POINTS[i], Globals.POINTS[i+1]) > Parameters.LENGTH1) {
                result = true;
                System.out.println("condition met at iteration " + i);
                break;
            }
        }
        if (!result) System.out.println("condition not met");
        return result;
    }

    private static double dist(Point point1, Point point2) {
        double diffx = point2.x - point1.x;
        double diffy = point2.y - point1.y;
        System.out.println(Math.pow(diffx, 2) + Math.pow(diffy, 2));
        return Math.sqrt(Math.pow(diffx, 2) + Math.pow(diffy, 2));
    }

    public static void main(String[] args) {
        Parameters.LENGTH1 = 1.0;

        Globals.NUMPOINTS = 3;

        Globals.POINTS = new Point[Globals.NUMPOINTS];

        Globals.POINTS[0] = new Point(1.0, 2.0);
        Globals.POINTS[1] = new Point(0.0, 2.0);
        Globals.POINTS[2] = new Point(0.0, 1.0);

        boolean result = evaluate();
        System.out.println("Result: " + result);
    }
}
