package com.programming.pearls.problems;

import com.google.common.base.Preconditions;

/**
 * 使用int实现一个n位k维向量
 */
public class KBitVector {
    private static final int SIZE_OF_INT = 32;
    private static final int M = 0x00000001;
    //使用int数组来进行一系列bit位，每一个int
    private final int[] bits;
    private final int k;

    public KBitVector(int n, int k) {
        Preconditions.checkArgument(n > 0);
        Preconditions.checkArgument(k < SIZE_OF_INT);
        this.k = k;
        int count = (int) Math.ceil(Math.log(k) / Math.log(2));
        bits = new int[n / (SIZE_OF_INT - count) -  + 1];
    }

    public void set(int k, int count) {
        Preconditions.checkArgument(count <= k);
        int c = k / SIZE_OF_INT;
        int r = k % SIZE_OF_INT;
        bits[c] |= 1 << (SIZE_OF_INT - r);
    }

    public void clr(int k) {
        int c = k / SIZE_OF_INT;
        int r = k % SIZE_OF_INT;
        bits[c] &= ~(1 << (SIZE_OF_INT - r));
    }

    public boolean ifSet(int k) {
        int c = k / SIZE_OF_INT;
        int r = k % SIZE_OF_INT;
        return (bits[c] >> (SIZE_OF_INT - r) & M) == 1;
    }

    public int count(int k) {
        return 0;
    }

}