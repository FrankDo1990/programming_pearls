package com.programming.pearls.problems;

import com.google.common.base.Preconditions;

/**
 * 使用int实现一个n位k维向量
 */
public class KBitVector {
    private static final int SIZE_OF_INT_BIT = 5;
    private static final int M = 0x00000001;
    //用多少位来标识数字出现多少次
    private static int kBit;
    //使用int数组来进行一系列bit位，每一个int
    private final int[] bits;

    //暂时就支持下
    public KBitVector(int n, int kBit) {
        Preconditions.checkArgument(n > 0);
        int i = 1 << SIZE_OF_INT_BIT;
        Preconditions.checkArgument(kBit < i);
        Preconditions.checkArgument(i % kBit == 0);
        this.kBit = kBit;
        bits = new int[n / (i / kBit) + 1];
    }

    public void set(int k, int count) {
        Preconditions.checkArgument(count < (1 << kBit));
    }

    public void clr(int k) {
    }

    public boolean ifSet(int k) {
        return count(k) > 0;
    }

    public int count(int k) {
        return 0;
    }

}