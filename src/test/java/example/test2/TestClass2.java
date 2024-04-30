package example.test2;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TestClass2 {
    private int a;
    private char b;
    private Integer[] c;
    private String d;

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder(a + " " + b + " ");
        for (Integer ch : c) {
            ret.append(ch + ' ');
        }
        ret.append(d).append(" ");
        return ret.toString();
    }
}
