import java.lang.reflect.InvocationTargetException;
public class Main {
    public static void main(String[] args) {
        try {
            ExampleClass example = Randomizer.getRandomObject(ExampleClass.class);
            System.out.printf("Constructed object: %s", example);
        }
        catch (InvocationTargetException ex) {
            System.out.printf("oops! %s", ex.getCause());
        }

        catch (Exception e) {
            System.out.println(e);
        }
    }
}
