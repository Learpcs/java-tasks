package example;

//TODO maven
//TODO warnings T_T
public class Main {
    public static void main(String[] args) {
        try {
            ExampleClass example = Randomizer.getRandomObject(ExampleClass.class);
            System.out.printf("\nConstructed object: %s\n", example);

        }
        catch (Exception e) {
            //https://stackoverflow.com/questions/7469316/why-is-exception-printstacktrace-considered-bad-practice
            e.printStackTrace();
        }
    }
}
