package example;

public class ExampleClass {
    private int a;
    private char b;
    private Integer[] c;


    private ExampleClass() {
        System.out.println("ExampleClass instance created");
    }
    public ExampleClass(int _a, char _b, Integer[] _c) {
        a = _a;
        b = _b;
        c = _c;
    }

    @Override
    public String toString() {
        String ret = String.valueOf(a) + " " + String.valueOf(b) + " ";
        for (int i = 0; i < c.length; ++i) {
            ret += String.valueOf(c[i]) + ' ';
        }
        return ret;
    }
}
