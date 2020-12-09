package com.programming.pearls.problems;

import com.google.common.base.Preconditions;

/**
 * 使用int实现一个n位比特向量
 */
public class BitVector {
    private static final int SIZE_OF_INT = 32;
    private static final int MASK = 0x1F;
    private static final int SHIFT = 5;
    //使用int数组来进行一系列bit位，从 0...n-1 标识从小到大的数
    private final int[] bits;

    public BitVector(int n) {
        Preconditions.checkArgument(n > 0);
        bits = new int[n / SIZE_OF_INT + 1];
    }

    public void set(int k) {
        bits[k >> SHIFT] |= 1 << (k & MASK);
    }

    public void clr(int k) {
        bits[k >> SHIFT] &= ~(1 << (k & MASK));
    }

    public boolean ifSet(int k) {
        return ((bits[k >> SHIFT] >> (k & MASK)) & 0x1) == 1;
    }

}