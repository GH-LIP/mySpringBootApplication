package com.test.basic;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @auther: LIP
 * @create: 2021-01-20 21:44
 * @desc:
 */
public class SpringIOC {
    /**
     * 依赖注入的数据类型分三类：
     * 1. 基本类型和String
     * 2. 其他bean类型
     * 3. 复杂类型和集合类型
     *
     * 注入的方式有三种：
     * 1. 构造函数注入
     * 2. set方法注入
     * 3. 注解提供
     */

    private String[] myStrs;
    private List<String> myList;
    private Set<String> mySet;
    private Map<String,Object> myMap;
    private Properties myProps;

    public void setMyStrs(String[] myStrs) {
        this.myStrs = myStrs;
    }

    public void setMyList(List<String> myList) {
        this.myList = myList;
    }

    public void setMySet(Set<String> mySet) {
        this.mySet = mySet;
    }

    public void setMyMap(Map<String, Object> myMap) {
        this.myMap = myMap;
    }

    public void setMyProps(Properties myProps) {
        this.myProps = myProps;
    }

    public void readData () {
        System.out.println("myStrs:"+ myStrs);
        System.out.println("myList:"+ myList);
        System.out.println("mySet:"+ mySet);
        System.out.println("myMap:"+ myMap);
        System.out.println("myProps:"+ myProps);
    }



}
