package example;

import java.lang.reflect.*;
import java.util.*;

//can't suppress Casting '...' to '...' is redundant, sooooo no more warnings
//@SuppressWarnings({"unchecked", "cast", "boxing"})
@SuppressWarnings("all")

//TODO refactor
class Randomizer {
    private static final Random rnd = new Random();
    private static final int DEPTH_UPPER_BOUND = 8; //less than 6 seems not usable for wrapper classes
    private static <T> T getRandomObjectRec(Class<T> cls, int depth) {
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
                    Object obj = (Object)getRandomObjectRec(cls.getComponentType(), depth + 1);

                    //TODO RandomSettings.valid(int, obj);
                    if (obj == null) {
                        return null;
                    }
                    arr[i] = (int) obj;
                }
                return (T) arr;
            }
            else if (cls.getComponentType()  == byte.class) {
                byte[] arr = new byte[rnd.nextInt(10) + 1];
                for (int i = 0; i < arr.length; ++i) {
                    Object obj = (Object)getRandomObjectRec(cls.getComponentType(), depth + 1);

                    //TODO RandomSettings.valid(int, obj);
                    if (obj == null) {
                        return null;
                    }
                    arr[i] = (byte) obj;
                }
                return (T) arr;
            }
            else if (cls.getComponentType()  == short.class) {
                short[] arr = new short[rnd.nextInt(10) + 1];
                for (int i = 0; i < arr.length; ++i) {
                    Object obj = (Object)getRandomObjectRec(cls.getComponentType(), depth + 1);

                    //TODO RandomSettings.valid(int, obj);
                    if (obj == null) {
                        return null;
                    }
                    arr[i] = (short) obj;
                }
                return (T) arr;
            }
            else if (cls.getComponentType()  == long.class) {
                long[] arr = new long[rnd.nextInt(10) + 1];
                for (int i = 0; i < arr.length; ++i) {
                    Object obj = (Object)getRandomObjectRec(cls.getComponentType(), depth + 1);

                    //TODO RandomSettings.valid(int, obj);
                    if (obj == null) {
                        return null;
                    }
                    arr[i] = (long) obj;
                }
                return (T) arr;
            }
            else if (cls.getComponentType()  == char.class) {
                char[] arr = new char[rnd.nextInt(10) + 1];
                for (int i = 0; i < arr.length; ++i) {
                    Object obj = (Object)getRandomObjectRec(cls.getComponentType(), depth + 1);

                    //TODO RandomSettings.valid(int, obj);
                    if (obj == null) {
                        return null;
                    }
                    arr[i] = (char) obj;
                }
                return (T) arr;
            }
            else if (cls.getComponentType()  == float.class) {
                float[] arr = new float[rnd.nextInt(10) + 1];
                for (int i = 0; i < arr.length; ++i) {
                    Object obj = (Object)getRandomObjectRec(cls.getComponentType(), depth + 1);

                    //TODO RandomSettings.valid(int, obj);
                    if (obj == null) {
                        return null;
                    }
                    arr[i] = (float) obj;
                }
                return (T) arr;
            }
            else if (cls.getComponentType()  == double.class) {
                double[] arr = new double[rnd.nextInt(10) + 1];
                for (int i = 0; i < arr.length; ++i) {
                    Object obj = (Object)getRandomObjectRec(cls.getComponentType(), depth + 1);

                    //TODO RandomSettings.valid(int, obj);
                    if (obj == null) {
                        return null;
                    }
                    arr[i] = (double) obj;
                }
                return (T) arr;
            }
            else {
                Object[] arr = (Object[]) Array.newInstance(cls.getComponentType(), rnd.nextInt(10) + 1);

                for (int i = 0; i < arr.length; ++i) {
                    Object obj = (Object)getRandomObjectRec(cls.getComponentType(), depth + 1);

                    //TODO RandomSettings.valid(int, obj);
                    if (obj == null) {
                        return null;
                    }
                    arr[i] = obj;
                }
                return (T) arr;
            }
        }

        if (cls == String.class) {
            Object obj = (Object)getRandomObjectRec(char[].class, depth + 1);
            if (obj == null) {
                return null;
            }
            return (T) new String((char[])obj);
        }

        Constructor<?>[] ctrList = cls.getDeclaredConstructors();
        Collections.shuffle(Arrays.asList(ctrList));

        for (Constructor<?> ctr : ctrList) {
            if (Modifier.isPublic(ctr.getModifiers()) ) {
                Object[] invokeParms = new Object[ctr.getParameterCount()];
                Class<?>[] ctrParams = ctr.getParameterTypes();
                boolean valid = true;
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
                        //continue;
                    }
                }
            }
        }

        return null;
    }

    public static <T> T getRandomObject(Class<T> cls) {
        return getRandomObjectRec(cls, 0);
    }
}
