package com.example.java;

import java.util.Arrays;

public class MergeAndSortChess {
    public static int[] mergeAndSort(int[] chess1, int[] chess2) {
        // 创建一个新数组，长度为两个输入数组长度之和
        int[] mergedArray = new int[chess1.length + chess2.length];

        // 将chess1复制到新数组
        System.arraycopy(chess1, 0, mergedArray, 0, chess1.length);
        // 将chess2复制到新数组，紧接着chess1的数据
        System.arraycopy(chess2, 0, mergedArray, chess1.length, chess2.length);

        // 对合并后的数组进行排序
        Arrays.sort(mergedArray);

        return mergedArray;
    }

    public static void main(String[] args) {
        int[] Chess1 = {2, 3, 7};
        int[] Chess2 = {3, 1, 4};

        int[] sortedChess = mergeAndSort(Chess1, Chess2);
        System.out.println(Arrays.toString(sortedChess));  // 输出合并后有序的棋子编号
    }
}
