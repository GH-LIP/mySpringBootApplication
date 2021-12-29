package com.test.layman.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "person", ignoreInvalidFields = true)
public class Person {
    private String name;
    private int age;
    private boolean boss;
    private Date birthday;
    private Map<String, Object> map;
    private List<Object> hobbies;
    private Dog dog;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birthday=" + birthday +
                ", map=" + map +
                ", hobbies=" + hobbies +
                ", dog=" + dog +
                '}';
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Person setAge(int age) {
        this.age = age;
        return this;
    }

    public boolean isBoss() {
        return boss;
    }

    public Person setBoss(boolean boss) {
        this.boss = boss;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Person setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public Person setMap(Map<String, Object> map) {
        this.map = map;
        return this;
    }

    public List<Object> getHobbies() {
        return hobbies;
    }

    public Person setHobbies(List<Object> hobbies) {
        this.hobbies = hobbies;
        return this;
    }

    public Dog getDog() {
        return dog;
    }

    public Person setDog(Dog dog) {
        this.dog = dog;
        return this;
    }
}
