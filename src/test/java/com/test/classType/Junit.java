package com.test.classType;

import com.layman.basic.SpringIOC;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @auther: LIP
 * @create: 2021-01-23 21:07
 * @desc: 类的反射
 *          获取java类的字节码文件
 */
public class Junit {

    @Test
    public void reflect () throws Exception {
        /**
         * 获取java类的字节码文件：
         * 1. Class clazz1 = Class.forName(全限定类名);
         * 2. Class clazz2 = Person.class;
         */
        Class clazz1 = Class.forName("com.layman.basic.SpringIOC");
        Class clazz2 = SpringIOC.class;
        Class clazz3 = new SpringIOC().getClass();


        Method[] ms = clazz1.getMethods();
        for (Method m : ms) {
            System.out.println("方法名：" +m.getName());
        }

        Method[] dms = clazz1.getDeclaredMethods();
        for (Method dm : dms) {
            System.out.println("声明的方法："+dm);
        }

        Field[] fs = clazz1.getFields();
        Field f = null;
        for (int i = 0; i < fs.length; i++) {
            f = fs[i];
            System.out.println("字段："+ f);
        }

        Field[] dfs = clazz1.getDeclaredFields();
        Field df = null;
        for (int i = 0; i < dfs.length; i++) {
            df = dfs[i];
            System.out.println("声明的字段："+ df);
        }

    }
}
