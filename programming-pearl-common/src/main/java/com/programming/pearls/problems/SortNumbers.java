package com.programming.pearls.problems;

/**
 * @author dufeng <dufeng@kuaishou.com>
 * Created on 2020-12-08
 * 排序某个算法
 */
public class SortNumbers {

    public static int[] bitSort(int[] data, int upper) {
        BitVector vector = new BitVector(upper);
        for (int i = 0 ; i< upper; i++) {
            vector.clr(i);
        }
        for (int c : data) {
            vector.set(c);
        }
        int[] res = new int[data.length];
        int i = 0;
        for (int c = 0 ; c <= upper; c++) {
            if (vector.ifSet(c)) {
                res[i++] = c;
            }
        }
        return res;
    }
}
