package example.ExampleClass;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExampleClass {
    private int a;
    private char b;
    private Integer[] c;
    private String d;

    @Override
    public String toString() {
        String ret = a + " " + b + " ";
        for (Integer ch : c) {
            ret += ch + ' ';
        }
        ret += d;
        return ret;
    }
}
