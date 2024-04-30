package example.test1;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
@Data
public class TestClass1 {
    private int[] a;
    private double[] b;
    private char[] c;
    private String[] d;
    private Integer[] e;
    private AtomicInteger[] f;
}
