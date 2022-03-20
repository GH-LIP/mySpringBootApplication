package com.test.collection;

import java.util.*;
import java.util.concurrent.*;

public class MyCollection {
    public static void main(String[] args) {
        Collection arrayList = new ArrayList();
        Collection linkedList = new LinkedList();
        Collection vector = new Vector();
        Collection copyOnWriteArrayList = new CopyOnWriteArrayList();

        Collection hashSet = new HashSet();
        Collection linkedHashSet = new LinkedHashSet<>();
        Collection treeSet = new TreeSet();
        Collection copyOnWriteArraySet = new CopyOnWriteArraySet();

//        Collection arrayQueue = new ArrayQueue(10);
        Collection arrayDueue = new ArrayDeque(10);
        Collection arrayBlockingQueue = new ArrayBlockingQueue(10);
        Collection linkedBlockingQueue = new LinkedBlockingQueue(10);
        Collection linkedBlockingDuque = new LinkedBlockingDeque(10);



        Map<String, Object> hashMap = new HashMap<>();
        Map<String, Object> linkedHashMap = new LinkedHashMap<>();
        Map<String, Object> treeMap = new TreeMap<>();
        Map<String, Object> hashtable = new Hashtable<>();
        Map<String, Object> map = new ConcurrentHashMap<>();
    }

}
