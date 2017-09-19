package com.wangwenjun.juc.atomic;

import sun.misc.Unsafe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

/***************************************
 * @author:Alex Wang
 * @Date:2017/7/16
 * QQ交流群:601980517，463962286
 ***************************************/
public class UnsafeFooTest {
    private static Unsafe getUnsafe() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchFieldException, IOException, NoSuchMethodException, InvocationTargetException {
        /*Simple simple = new Simple();
        System.out.println(simple.get());*/
//        Simple simple = Simple.class.newInstance();


//        Class.forName("com.wangwenjun.jcu.atomic.UnsafeFooTest$Simple");


/*        Simple simple = (Simple) unsafe.allocateInstance(Simple.class);

        System.out.println(simple.get());
        System.out.println(simple.getClass());
        System.out.println(simple.getClass().getClassLoader());*/

/*
        Guard guard = new Guard();
        guard.work();

        Field f = guard.getClass().getDeclaredField("ACCESS_ALLOWED");
        unsafe.putInt(guard, unsafe.objectFieldOffset(f), 42);
        guard.work();*/

        /*byte[] bytes = loadClassContent();
        Class aClass = unsafe.defineClass(null, bytes, 0, bytes.length);
        int v = (Integer) aClass.getMethod("get").invoke(aClass.newInstance(), null);
        System.out.println(v);*/

        System.out.println(sizeOf(new Simple()));

    }

    private static long sizeOf(Object obj) {
        Unsafe unsafe = getUnsafe();
        Set<Field> fields = new HashSet<Field>();
        Class c = obj.getClass();
        while (c != Object.class) {
            Field[] declaredFields = c.getDeclaredFields();
            for (Field f : declaredFields) {
                if ((f.getModifiers() & Modifier.STATIC) == 0) {
                    fields.add(f);
                }
            }
            c = c.getSuperclass();
        }

        long maxOffSet = 0;
        for (Field f : fields) {
            long offSet = unsafe.objectFieldOffset(f);
            if (offSet > maxOffSet) {
                maxOffSet = offSet;
            }
        }

        return ((maxOffSet / 8) + 1) * 8;
    }

    private static byte[] loadClassContent() throws IOException {
        File f = new File("G:\\Teaching\\A.class");
        FileInputStream fis = new FileInputStream(f);
        byte[] content = new byte[(int) f.length()];
        fis.read(content);
        fis.close();
        return content;
    }

    static class Guard {
        private int ACCESS_ALLOWED = 1;

        private boolean allow() {
            return 42 == ACCESS_ALLOWED;
        }

        public void work() {
            if (allow()) {
                System.out.println("I am working by allowed");
            }
        }

    }

    static class Simple {
        private long l = 0;
        private int i = 10;
        private byte b = (byte) 0x01;


        public Simple() {
            this.l = 1;
            System.out.println("==========");
        }

        public long get() {
            return this.l;
        }
    }


}
