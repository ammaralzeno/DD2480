package interceptor.unlock;

import interceptor.model.Connector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PumTest {

    /**
     * Contract: CMV och LCM är givna. LCM innehåller NOTUSED, ANDD och ORR.
     * Oracle:
     * - NOTUSED => PUM[i,j] = true
     * - ANDD    => PUM[i,j] = CMV[i] && CMV[j]
     * - ORR     => PUM[i,j] = CMV[i] || CMV[j]
     */
    @Test
    public void testPumRules() {
        boolean[] cmv = { true, false, true };

        Connector[][] lcm = {
                { Connector.NOTUSED, Connector.ANDD,   Connector.ORR },
                { Connector.ANDD,    Connector.NOTUSED,Connector.ORR },
                { Connector.ORR,     Connector.ORR,    Connector.NOTUSED }
        };

        Pum pum = new Pum(cmv, lcm);

        // NOTUSED -> true
        assertTrue(pum.get(0, 0));
        assertTrue(pum.get(1, 1));
        assertTrue(pum.get(2, 2));

        // ANDD: (0,1) => true && false = false
        assertFalse(pum.get(0, 1));
        assertFalse(pum.get(1, 0)); // symmetric check

        // ORR: (0,2) => true || true = true
        assertTrue(pum.get(0, 2));
        assertTrue(pum.get(2, 0)); // symmetric check

        // ORR: (1,2) => false || true = true
        assertTrue(pum.get(1, 2));
        assertTrue(pum.get(2, 1)); // symmetric check
    }

    /**
     * Contract: Alla LCM entries är NOTUSED.
     * Oracle: Hela PUM blir true.
     */
    @Test
    public void testAllNotUsedBecomesTrue() {
        boolean[] cmv = { false, false, false };

        Connector[][] lcm = {
                { Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED },
                { Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED },
                { Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED }
        };

        Pum pum = new Pum(cmv, lcm);

        for (int i = 0; i < cmv.length; i++) {
            for (int j = 0; j < cmv.length; j++) {
                assertTrue(pum.get(i, j));
            }
        }
    }
}
