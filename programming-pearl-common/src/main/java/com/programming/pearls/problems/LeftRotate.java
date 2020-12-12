package com.programming.pearls.problems;

import com.google.common.base.Preconditions;

/**
 * @author dufeng <dufeng@kuaishou.com>
 * Created on 2020-12-13
 */
public class LeftRotate {

    public static char[] leftRotate(char[] ori, int i) {
        Preconditions.checkArgument(ori != null && ori.length > 0);
        int r = i % ori.length;
        //翻转左[0,  r-1];
        reverse(ori, 0, r - 1);
        //翻转左[r,  len - 1];
        reverse(ori, r, ori.length - 1);
        //翻转左[0,  len - 1];
        reverse(ori, 0, ori.length - 1);
        return ori;
    }

    public static void reverse(char[] d, int start, int end) {
        for (int i = start, j = end; j > i; i++,j--) {
            char temp = d[i];
            d[i] = d[j];
            d[j] = temp;
        }
    }
}
