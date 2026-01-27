package interceptor.unlock;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FuvTest {

    /**
     * Tests (smaller version of) example 1 from assignment instructions.
     * 
     * Contract: 
     * PUV[0] is true and PUM[0,1] and PUM[0,3] are false.
     * PUV[1] is false and PUM[1,0] is false.
     * PUV[2] is true and every value in PUM[2] is true.
     * PUV[3] is true and PUM[3,0] is false.
     * 
     * Oracle:
     * PUV[0] = true && PUM[0,1] = PUM[0,3] = false         =>  FUV[0] = false
     * PUV[1] = false                                       =>  FUV[1] = true
     * PUV[2] = true && PUM[2,i] = true (i != 2, 0 ≤ i ≤ 4) =>  FUV[2] = true
     * PUV[3] = true && PUM[3,1] = false                    =>  FUV[3] = false
     * 
     */
    @Test
    public void testFuvExample1() {

        boolean[][] pum = {
            {true, false, true, false},
            {false, true, true, true},
            {true, true, true, true},
            {false, true, true, true}
        };

        boolean[] puv = {true, false, true, true};

        Fuv fuv = new Fuv(pum, puv);

        assertFalse(fuv.get(0));
        assertTrue(fuv.get(1));
        assertTrue(fuv.get(2));
        assertFalse(fuv.get(3));
    }

    /**
     * Tests the case where all values in PUM are true except where i = j.
     * 
     * Contract:
     * In PUM, the only false values are those where i = j.
     * PUV[2] is the only true value in PUV.
     * 
     * Oracle:
     * Every row in PUM = true     =>  every value in FUV = true
     * 
     */
    @Test
    public void testFuvAllTrue() {
        boolean[][] pum = {
            {false, true, true, true},
            {true, false, true, true},
            {true, true, false, true},
            {true, true, true, false}
        };

        boolean[] puv = {false, false, true, false};

        Fuv fuv = new Fuv(pum, puv);

        assertTrue(fuv.get(0));
        assertTrue(fuv.get(1));
        assertTrue(fuv.get(2));
        assertTrue(fuv.get(3));
    }
}
