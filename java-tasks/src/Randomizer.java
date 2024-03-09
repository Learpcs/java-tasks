import java.lang.reflect.*;
import java.util.*;

//TODO refactor
class Randomizer {
    private static Random rnd = new Random();
    private static int DEPTH_UPPER_BOUND = 6;
    private static <T> T getRandomObjectRec(Class<T> cls, int depth) throws Exception {
//        System.out.printf("!%s %s\n", cls.getTypeName(), depth);

        if (depth == DEPTH_UPPER_BOUND) {
            return null;
        }


        //TODO switch statements?
        //TODO RandomizerSettings.specified(cls) => handle base case
        if (cls.isPrimitive()) {
            if (cls == int.class) {
                return (T) (Object) (int) rnd.nextInt();
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

        if (cls.isArray()) {
//            Object[] a = (Object[]) Array.newInstance(cls.getComponentType(), 10);
//
//            for (int i = 0; i < a.length; ++i) {
//                a[i] = getRandomObjectRec(cls.getComponentType(), depth + 1);
//            }
//            System.out.printf("!!!%s\n", cls.getComponentType());
//            return (T) a;
            if (cls.getComponentType() == int.class) {
                int[] arr = new int[rnd.nextInt(10) + 1];
                for (int i = 0; i < arr.length; ++i) {
                    arr[i] = (int)(Object)getRandomObjectRec(cls.getComponentType(), depth + 1);
                }
                return (T) arr;
            }
            else if (cls.getComponentType()  == byte.class) {
                byte[] arr = new byte[rnd.nextInt(10) + 1];
                for (int i = 0; i < arr.length; ++i) {
                    arr[i] = (byte)(Object)getRandomObjectRec(cls.getComponentType(), depth + 1);
                }
                return (T) arr;
            }
            else if (cls.getComponentType()  == short.class) {
                short[] arr = new short[rnd.nextInt(10) + 1];
                for (int i = 0; i < arr.length; ++i) {
                    arr[i] = (short)(Object)getRandomObjectRec(cls.getComponentType(), depth + 1);
                }
                return (T) arr;
            }
            else if (cls.getComponentType()  == long.class) {
                long[] arr = new long[rnd.nextInt(10) + 1];
                for (int i = 0; i < arr.length; ++i) {
                    arr[i] = (long)(Object)getRandomObjectRec(cls.getComponentType(), depth + 1);
                }
                return (T) arr;
            }
            else if (cls.getComponentType()  == char.class) {
                char[] arr = new char[rnd.nextInt(10) + 1];
                for (int i = 0; i < arr.length; ++i) {
                    arr[i] = (char)(Object)getRandomObjectRec(cls.getComponentType(), depth + 1);
                }
                return (T) arr;
            }
            else if (cls.getComponentType()  == float.class) {
                float[] arr = new float[rnd.nextInt(10) + 1];
                for (int i = 0; i < arr.length; ++i) {
                    arr[i] = (float)(Object)getRandomObjectRec(cls.getComponentType(), depth + 1);
                }
                return (T) arr;
            }
            else if (cls.getComponentType()  == double.class) {
                double[] arr = new double[rnd.nextInt(10) + 1];
                for (int i = 0; i < arr.length; ++i) {
                    arr[i] = (double)(Object)getRandomObjectRec(cls.getComponentType(), depth + 1);
                }
                return (T) arr;
            }
            else {
                Object[] arr = (Object[]) Array.newInstance(cls.getComponentType(), rnd.nextInt(10) + 1);

                for (int i = 0; i < arr.length; ++i) {
                    arr[i] = getRandomObjectRec(cls.getComponentType(), depth + 1);
                }
                return (T) arr;
            }
        }

        if (cls == String.class) {
            return (T) new String(getRandomObjectRec(char[].class, depth + 1));
        }

        Constructor[] ctrList = cls.getDeclaredConstructors();
        Collections.shuffle(Arrays.asList(ctrList));

        for (Constructor ctr : ctrList) {
            if (Modifier.isPublic(ctr.getModifiers()) ) {
                Object[] invokeParms = new Object[ctr.getParameterCount()];
                Class[] ctrParams = ctr.getParameterTypes();
                Boolean valid = true;
                for (int i = 0; i < invokeParms.length; i++) {
                    invokeParms[i] = getRandomObjectRec(ctrParams[i], depth + 1);
                    //TODO RandomizerSettings.Valid()
                    valid &= invokeParms[i] != null;
                }

                if (valid) {
                    try {
                        return (T)ctr.newInstance(invokeParms);
                    }
                    catch (Exception e) {
                        continue;
                    }
                }
            }
        }

        return null;
    }

    public static <T> T getRandomObject(Class<T> cls) throws Exception {
        return getRandomObjectRec(cls, 0);
    }
}
