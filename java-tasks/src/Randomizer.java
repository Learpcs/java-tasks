import java.lang.reflect.*;
import java.util.*;

class Randomizer {
    private static Random rnd = new Random();

    public static <T> T getRandomObject(Class<T> cls) throws Exception {
        System.out.printf("Зашел в рекурсию (название класса, притивный тип, массив тип): %s %s %s\n", cls.getName(), cls.isPrimitive(), cls.isArray());

        //TODO Add all primitives
        if (cls.isPrimitive()) {
            System.out.println("ВЫШЕЛ ИЗ РЕКУРСИИ");
            if (cls == int.class) {
                return (T) (Object) (int) rnd.nextInt();
            }
            if (cls == byte.class) {
                return (T) (Object) (byte) rnd.nextInt(256);
            }
            if (cls == char.class) {
                return (T) (Object) (char) ('a' + rnd.nextInt(26));
            }
            if (cls == float.class) {
                return (T) (Object) (float) rnd.nextFloat();
            }
            if (cls == double.class) {
                return (T) (Object) (double) rnd.nextDouble();
            }
        }

        //FIXME Fix this crap somehow
        if (cls.isArray()) {
            Object[] arr = new Object[rnd.nextInt(10) + 1];
            for (int i = 0; i < arr.length; ++i) {
                arr[i] = getRandomObject(cls.getComponentType());
            }
            System.out.println("ПЫТАЮСЬ ВЕРНУТЬ МАССИВ");
            return (T) arr;
        }

        //Attempt with Arrays.copyOf
//        if (cls.isArray()) {
//            Object[] arr = new Object[rnd.nextInt(10) + 1];
//            for (int i = 0; i < arr.length; ++i) {
//                arr[i] = getRandomObject(cls.getComponentType());
//            }
//            return (T) Arrays.copyOf(arr, arr.length, T.class);
//        }
        //Attempt with Arrays.copyOf
//        if (cls.isArray()) {
//
//            T arr = new T[rnd.nextInt(10) + 1];
//            for (int i = 0; i < arr.length; ++i) {
//                arr[i] = getRandomObject(cls.getComponentType());
//            }
//            return arr;
//        }

        //Attempt with ArrayList
//        if (cls.isArray()) {
//            ArrayList<T> arr = new ArrayList<T>();
//            int sz = rnd.nextInt(10) + 1;
//            while (arr.size() < sz) {
//                arr.add(new T()); //<=== can't create instance of T again
//            }
//        }

        Constructor[] ctrList = cls.getDeclaredConstructors();
        Collections.shuffle(Arrays.asList(ctrList));

        Constructor ctr = null;

        for (Constructor c : ctrList) {
            System.out.println(c.getModifiers());
            if (Modifier.isPublic(c.getModifiers())) {
                ctr = c;
                break;
            }
        }

        if (ctr == null) {
            throw new Exception("All constructors are private");
        }

        Object[] invokeParms = new Object[ctr.getParameterCount()];
        Class[] ctrParams = ctr.getParameterTypes();
        for (int i = 0; i < invokeParms.length; i++) {
            invokeParms[i] = getRandomObject(ctrParams[i]);
        }
        T obj = (T)ctr.newInstance(invokeParms);

        System.out.println("ВЫШЕЛ ИЗ РЕКУРСИИ");
        return obj;
    }
}
