package com.leetcode;

public class CombineArray {

    /*
     * Click `Run` to execute the snippet below!
     */

    /*
     * To execute Java, please define "static void main" on a class
     * named Solution.
     *
     * If you need more classes, simply define them inline.
     */
    public static void main(String[] args) {
        Integer[][] combine = combine(new Integer[][]{{1, 3}, {6, 9}}, new Integer[]{2, 5});
        for (Integer[] cb : combine) {
            System.out.println("[" + cb[0] + "," + cb[1] + "]");
        }
    }

    // num 表示合并后的数组的左右两个值，index 表示合并起始坐标，可用来计算结果的长度
    public static Integer[][] combine (Integer[][] intervals, Integer[] newInterval) {
        Integer leftNum = null;
        Integer rightNum = null;
        Integer leftIndex = null;
        Integer rightIndex = null;
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][1] < newInterval[0]) {
                continue;
            } else {
                if (intervals[i][0] < newInterval[0]) {
                    leftNum = intervals[i][0];
                } else {
                    leftNum = newInterval[0];
                }
                leftIndex = i;
                break;
            }
        }
        for (int i = intervals.length; i > leftIndex; i--) {
            if (intervals[i][1] < newInterval[1]) {
                if (intervals[i][1] < newInterval[0]) {
                    rightNum = null;
                    rightIndex = null;
                } else {
                    rightNum = newInterval[1];
                }
            } else {
                if (intervals[i][0] < newInterval[1]) {
                    rightNum = intervals[i][1];
                    rightIndex = i;
                    break;
                } else {
                    continue;
                }
            }
        }
        Integer[][] result;
        if (leftNum != null && leftIndex != null) {
            int len = intervals.length - (rightIndex - leftIndex) + 1;
            result = new Integer[len][2];
        } else {
            result = intervals;
        }
        return result;
    }

// Your previous Plain Text content is preserved below:

// Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

// You may assume that the intervals were initially sorted according to their start times.



// Example 1:
// Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
// Output: [[1,5],[6,9]]

// Example 2:
// Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
// Output: [[1,2],[3,10],[12,16]]
// Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

// Example 3:
// Input: intervals = [], newInterval = [5,7]
// Output: [[5,7]]

// Example 4:
// Input: intervals = [[1,5]], newInterval = [2,3]
// Output: [[1,5]]

// Example 5:
// Input: intervals = [[1,5]], newInterval = [2,7]
// Output: [[1,7]]
}
