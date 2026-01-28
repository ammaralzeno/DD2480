package interceptor.unlock;

/**
 * FUV: Final Unlocking Vector
 * 
 * Creates the FUV based on PUM and PUV.
 * 
 * PUV[i] = false --> FUV[i] = true
 * all elements in PUM[i] = true --> FUV[i] = true
 * 
 * The FUV is a boolean array of length PUV.length.
 * 
 */

public class Fuv {

    private boolean[] fuv;

    public Fuv(boolean[][] pum, boolean[] puv) {

        fuv = new boolean[pum.length];

        for(int i = 0; i < pum.length; i++) {

            if(!puv[i] || rowTrue(pum[i], i)) {
                fuv[i] = true;
            }
        }
    }

    private static boolean rowTrue(boolean[] row, int i) {

        for(int j = 0; j < row.length; j++) {
            if(i == j) continue;
            if(!row[j]) return false;
        }
        
        return true;
    }

    public boolean get(int i) {
        return fuv[i];
    }

}
