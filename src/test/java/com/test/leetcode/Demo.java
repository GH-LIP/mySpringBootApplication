package com.test.leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList(){{
            add(1);
        }};
        List<Integer> list1 = Arrays.asList(1,2,3);
        list1.add(4);
        List<String> list2 = Collections.nCopies(3, "1");
        list2.add("2");
    }

    /**
     * board.length == 9
     * board[i].length == 9
     * board[i][j] 是一位数字或者 '.'
     */
    public boolean isValidSudoku(char[][] board) {
        Map<String, String> map = new HashMap();
        for (int i = 0; i < board.length; i++) {

            for (int j = 0; j < board[i].length; j++) {
                char c = board[i][j];
            }
        }
        return true;
    }
}
