package example;

import example.ExampleClass.ExampleClass;
import example.randomizer.Randomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Randomizer randomizer = context.getBean("Randomizer", Randomizer.class);
        try{
            ExampleClass example = randomizer.getRandomObject(ExampleClass.class);
            System.out.printf("\nConstructed object: %s\n", example);
        }
        catch (Exception e) {
            //https://stackoverflow.com/questions/7469316/why-is-exception-printstacktrace-considered-bad-practice
            e.printStackTrace();
        }
    }
}
