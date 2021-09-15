package com.test.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class QueueTest {

    public static void main(String[] args) {
        Queue<Integer> linkedList = new LinkedList();
        linkedList.offer(1); // same to add
        linkedList.add(2);
        linkedList.add(3);
        for(int i : linkedList) {
            System.out.println(i);
        }


        Queue arrayBlockingQueue = new ArrayBlockingQueue(10);
        arrayBlockingQueue.add("IN");
        arrayBlockingQueue.add("THE");
        arrayBlockingQueue.add("END");
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());


    }
}
