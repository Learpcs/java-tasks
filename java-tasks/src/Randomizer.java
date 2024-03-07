import java.lang.reflect.*;
import java.util.*;

class Randomizer {
    private static Random rnd = new Random();

    public static <T> T getRandomObject(Class<T> cls) throws Exception {
        System.out.printf("!%s\n", cls.getTypeName());
        //System.out.printf("Зашел в рекурсию (название класса, притивный тип, массив тип): %s %s %s\n", cls.getName(), cls.isPrimitive(), cls.isArray());

        //TODO switch statements?

        if (cls.isPrimitive()) {
            if (cls == int.class) {
                return (T) (Object) (int) rnd.nextInt(100);
            }
            if (cls == byte.class) {
                return (T) (Object) (byte) rnd.nextInt(256);
            }
            if (cls == short.class) {
                return (T) (Object) (short) rnd.nextInt(65536);
            }
            if (cls == long.class) {
                return (T) (Object) (long) rnd.nextLong();
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

        //Попытка 1
        if (cls.isArray()) {
            if (cls.getComponentType() == int.class) {
                int[] arr = new int[rnd.nextInt(10) + 1];
                for (int i = 0; i < arr.length; ++i) {
                    arr[i] = (int)(Object)getRandomObject(cls.getComponentType());
                }
                return (T) arr;
            }
            else if (cls.getComponentType()  == byte.class) {
                byte[] arr = new byte[rnd.nextInt(10) + 1];
                for (int i = 0; i < arr.length; ++i) {
                    arr[i] = (byte)(Object)getRandomObject(cls.getComponentType());
                }
                return (T) arr;
            }
            else if (cls.getComponentType()  == short.class) {
                short[] arr = new short[rnd.nextInt(10) + 1];
                for (int i = 0; i < arr.length; ++i) {
                    arr[i] = (short)(Object)getRandomObject(cls.getComponentType());
                }
                return (T) arr;
            }
            else if (cls.getComponentType()  == long.class) {
                long[] arr = new long[rnd.nextInt(10) + 1];
                for (int i = 0; i < arr.length; ++i) {
                    arr[i] = (long)(Object)getRandomObject(cls.getComponentType());
                }
                return (T) arr;
            }
            else if (cls.getComponentType()  == char.class) {
                char[] arr = new char[rnd.nextInt(10) + 1];
                for (int i = 0; i < arr.length; ++i) {
                    arr[i] = (char)(Object)getRandomObject(cls.getComponentType());
                }
                return (T) arr;
            }
            else if (cls.getComponentType()  == float.class) {
                float[] arr = new float[rnd.nextInt(10) + 1];
                for (int i = 0; i < arr.length; ++i) {
                    arr[i] = (float)(Object)getRandomObject(cls.getComponentType());
                }
                return (T) arr;
            }
            else if (cls.getComponentType()  == double.class) {
                double[] arr = new double[rnd.nextInt(10) + 1];
                for (int i = 0; i < arr.length; ++i) {
                    arr[i] = (double)(Object)getRandomObject(cls.getComponentType());
                }
                return (T) arr;
            }
            else {
                Object[] arr = new Object[rnd.nextInt(10) + 1];
                for (int i = 0; i < arr.length; ++i) {
                    arr[i] = getRandomObject(cls.getComponentType());
                }
                return (T) arr;
            }
        }

        if (cls == String.class) {
            return (T) new String(getRandomObject(char[].class));
        }



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
