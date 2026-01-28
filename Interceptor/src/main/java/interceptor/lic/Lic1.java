package interceptor.lic;

import interceptor.model.*;


/**
* LIC 1: There exists at least one set of three consecutive data points that cannot all be contained
* within or on a circle of radius RADIUS1.
*/
public final class Lic1 {


   private Lic1() {}

   public static boolean evaluate() {
       if (Globals.NUMPOINTS < 3) {
           return false;
       }

       for (int i = 0; i < Globals.NUMPOINTS - 2; i++) {
           Point p1 = Globals.POINTS[i];
           Point p2 = Globals.POINTS[i + 1];
           Point p3 = Globals.POINTS[i + 2];

           double mecRadius = calculateMecRadius(p1, p2, p3);

           if (mecRadius > Globals.PARAMETERS.RADIUS1) {
               return true;
           }
       }
       return false;
   }


   /**
    * Calculates the radius of the Minimum Enclosing Circle (MEC) for three points.
    */
   private static double calculateMecRadius(Point p1, Point p2, Point p3) {
       double a = distance(p1, p2);
       double b = distance(p2, p3);
       double c = distance(p3, p1);

       if (isObtuseOrRight(a, b, c)) {
           return Math.max(a, Math.max(b, c)) / 2.0;
       }

       return calculateCircumradius(a, b, c);
   }

   /**
    * Calculates the distance between two points.
    */
   private static double distance(Point p1, Point p2) {
       return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
   }

   /**
    * Checks if the triangle is obtuse or right-angled.
    */
   private static boolean isObtuseOrRight(double a, double b, double c) {
       double a2 = a * a;
       double b2 = b * b;
       double c2 = c * c;

       return (a2 + b2 <= c2) || (a2 + c2 <= b2) || (b2 + c2 <= a2);
   }

   /**
    * Calculates the circumradius of the triangle.
    */
   private static double calculateCircumradius(double a, double b, double c) {
       double term = (a + b + c) * (b + c - a) * (c + a - b) * (a + b - c);
      
       if (term <= 0) {
           return Math.max(a, Math.max(b, c)) / 2.0;
       }
      
       return (a * b * c) / Math.sqrt(term);
   }
}
