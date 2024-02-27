public class Main {
    public static void main(String[] args) {
        try {
            ExampleClass example = Randomizer.getRandomObject(ExampleClass.class);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
