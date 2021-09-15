package com.lip;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MySpringBootApplicationTest {

    @Autowired
    Person person;

    @Test
    public void testMethod () {
        StringBuffer sb = new StringBuffer();
        System.out.println(person);
    }


    /**
     * 数组：求给定数组的最大子序和
     * 说明：1. 从数组中筛选出连续的子数组求和
     *      2. 子数组最少一个元素
     *      3. 求最大的和
     */
    @Test
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0)
                sum += num;
            else
                sum = num;
            res = Math.max(res, sum);
        }
        return res;
    }

}
