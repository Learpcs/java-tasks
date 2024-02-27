public class Main {
    public static void main(String[] args) {
        try {
            ExampleClass example = Randomizer.getRandomObject(ExampleClass.class);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        ExampleClass a1 = new ExampleClass(1, 'a' , new int[1]);
        ExampleClass a2 = new ExampleClass(2, 'a' , new int[1]);
        ExampleClass a3 = new ExampleClass(3, 'a' , new int[1]);

        ExampleClass[] arr = {a1, a2, a3};
        Object[] b = {a1, a3, a2};
        arr = (ExampleClass[])b;
        System.out.println(arr[0]);
    }
}
