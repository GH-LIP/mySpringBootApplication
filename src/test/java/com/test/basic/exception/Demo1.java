package com.test.basic.exception;

import org.junit.Test;

/**
 * @auther: LIP
 * @create: 2021-01-24 10:41
 * @desc:
 */
public class Demo1 {

    @Test
    public void test() {
        Throwable throwable = new Throwable();
        throwable.getMessage();
        throwable.printStackTrace();
        throwable.toString();



    }
}
