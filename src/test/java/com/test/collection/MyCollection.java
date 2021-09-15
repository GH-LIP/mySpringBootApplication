package com.test.collection;

import java.util.*;

public class MyCollection {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("x");
        list.add("y");
        list.add("z");
        list.add("a");
        list.add("b");

        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        Iterator<String> iterator = list.iterator();
        String ele;
        while (iterator.hasNext()) {
            ele = iterator.next();
            System.out.println("元素：" + ele);
        }

        Collections.unmodifiableCollection(list);
    }

}
