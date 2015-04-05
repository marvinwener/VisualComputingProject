package nl.tue.win.vcp.virtualbreitenbergenvironment.utility;

import static java.lang.Math.cos;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author maikel
 */
public class MoreMathTest {
    
    public MoreMathTest() {
    }
    
    @Test
    public void testPowercos() {
        System.out.println("normal cos");
        double a = 7.0;
        int n = 1;
        double expResult = cos(a);
        double result = MoreMath.powercos(a, n);
        assertEquals(expResult, result, expResult);
    }

    @Test
    public void testPowercos2() {
        System.out.println("powercos power 2");
        double a = 7.0;
        int n = 2;
        double expResult = cos(cos(a));
        double result = MoreMath.powercos(a, n);
        assertEquals(expResult, result, expResult);
    }
    
    @Test
    public void testPowercos0() {
        System.out.println("powercos power 0");
        double a = 7.0;
        int n = 0;
        double expResult = a;
        double result = MoreMath.powercos(a, n);
        assertEquals(expResult, result, expResult);
    }
    
}
