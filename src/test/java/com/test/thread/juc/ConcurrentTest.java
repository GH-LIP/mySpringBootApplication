package com.test.thread.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class ConcurrentTest {

    public static void main(String[] args) {

        List NotSafelist = new ArrayList(30);
        // 并发读写list集合解决方案：Vector 、synchronizedList 、CopyOnWriteArrayList
        List list = new Vector(30);
        List list1 = Collections.synchronizedList(NotSafelist);
        List list2 = new CopyOnWriteArrayList();

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().toUpperCase(Locale.CHINA));
                // 为什么加上读取这一句会出错：并发修改异常 java.util.ConcurrentModificationException
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

        Set<String> set = new HashSet<>();
        Set<String> set1 = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set1.add(UUID.randomUUID().toString().toUpperCase(Locale.CHINA));
                // 为什么加上输出这一句会出错：并发修改异常 java.util.ConcurrentModificationException
                System.out.println(set1);
            }, String.valueOf(i)).start();
        }

        Map<String, String> map = new HashMap<>();
        Map<String, String> map1 = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            String key = String.valueOf(i);
            new Thread(()->{
                map1.put(key, UUID.randomUUID().toString().toUpperCase(Locale.CHINA));
                // 为什么加上输出这一句会出错：并发修改异常 java.util.ConcurrentModificationException
                System.out.println(map1);
            }, String.valueOf(i)).start();
        }

    }
}
