package com.test.collection.paradigm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ParadigmTest {
    public static void main(String[] args) {
        // 编译报错
        // List<Parent> list = new ArrayList<Son>();

        Parent parent = new Parent();
        Parent son = new Son();
        Parent daughter = new Daughter();

        //编译通过
        List<Son> sonList = new LinkedList<>();
        sonList.add(new Son("LIP"));
        sonList.add(new Son("LIP1"));
        sonList.add(new Son("LIP2"));
        sonList.add(new Son("LIP3"));

        // 向下限定：? 是子类，Parent 是父类
        List<? extends Parent> list = new ArrayList<Son>(sonList);
        // 向上限定：? 是父类，Son 是子类
        List<? super Son> list1 = new ArrayList<Son>(5);

        // 向上限定和向下限定，集合中都无法再添加元素,



    }

}

class Parent {
    private String name;

    public String getName() {
        return name;
    }

    public Parent setName(String name) {
        this.name = name;
        return this;
    }

    public Parent(){}
    public Parent (String name) {
        this.name = name;
    }

    public void phone () {
        System.out.println("父亲会玩手机...");
    }
}

class Son extends Parent{
    private String name;

    public String getName() {
        return name;
    }

    public Parent setName(String name) {
        this.name = name;
        return this;
    }

    public Son(){}
    public Son (String name) {
        this.name = name;
    }
    @Override
    public void phone () {
        System.out.println("儿子会玩手机...");
    }

    public void computer () {
        System.out.println("儿子会玩电脑...");
    }
}


class Daughter extends Parent{

    @Override
    public void phone () {
        System.out.println("女儿会玩手机...");
    }

    public void computer () {
        System.out.println("女儿会玩电脑...");
    }

    public void dance () {
        System.out.println("女儿会跳舞...");
    }
}
