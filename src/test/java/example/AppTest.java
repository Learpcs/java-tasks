package example;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void MainTest()
    {
        class Inner {
            private int a[];
            private double b[];
            private char c[];

            private String d[];

            private Integer e[];

            private AtomicInteger f[];

            public Inner(int[] a, double[] b, char[] c, String[] d, Integer[] e, AtomicInteger[] f) {
                this.a = a;
                this.b = b;
                this.c = c;
                this.d = d;
                this.e = e;
                this.f = f;
            }
        };

        assertNotNull(Randomizer.getRandomObject(Inner.class));
    }
}
