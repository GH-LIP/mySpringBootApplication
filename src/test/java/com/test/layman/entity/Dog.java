package com.test.layman.entity;

public class Dog {

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public Dog setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Dog setAge(Integer age) {
        this.age = age;
        return this;
    }
}
