package example;

public class ExampleClass {
    private int a;
    private char b;
    private Integer[] c;

    private String d;


    @SuppressWarnings("unused")
    private ExampleClass() {
        System.out.println("ExampleClass instance created");
    }
    public ExampleClass(int _a, char _b, Integer[] _c, String _d) {
        a = _a;
        b = _b;
        c = _c;
        d = _d;
    }

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
