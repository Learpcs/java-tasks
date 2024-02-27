public class ExampleClass {
    private int a;
    private char b;
    private int[] c;

    private ExampleClass() {
        System.out.println("ExampleClass instance created");
    }
    public ExampleClass(int _a, char _b, int[] _c) {
        a = _a;
        b = _b;
        c = _c;
    }

    @Override
    public String toString() {
        return String.valueOf(a) + " " + String.valueOf(b) + " " + c.toString();
    }
}
