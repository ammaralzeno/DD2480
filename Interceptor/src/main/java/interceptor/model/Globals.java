package /*Interceptor.src.main.java.*/interceptor.model;

public final class Globals {
    private Globals() {}

    public static final double PI = Math.PI;

    public static int NUMPOINTS;
    public static Point[] POINTS;

    public static Parameters PARAMETERS;

    public static Connector[][] LCM;   // [15][15]
    public static boolean[] CMV;        // [15]
    public static boolean[][] PUM;      // [15][15]
    public static boolean[] FUV;        // [15]

    public static boolean LAUNCH;
}
