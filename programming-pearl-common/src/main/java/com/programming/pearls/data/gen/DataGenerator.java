package com.programming.pearls.data.gen;

import java.util.Arrays;
import java.util.Random;

import com.google.common.base.Preconditions;

/**
 * @author frankdu
 */
public class DataGenerator {

    /**
     * 生成小于upper的k个随机整数
     * @param upper
     * @param k
     * @return
     */
    public static int[] genRandomKFromN(int upper, int k) {
        Preconditions.checkArgument(upper > 0 && upper >= k);

        int[] data = new int[upper];
        for (int i = 0; i < data.length; i++) {
            data[i] = i;
        }

        Random random = new Random();
        for (int i = 0; i < k; i++) {
            int index = random.nextInt(upper -i);
            int temp = data[upper - 1 - i];
            data[upper - 1 - i] = data[index];
            data[index] = temp;

        }
        return Arrays.copyOfRange(data, upper - k, upper);
    }
}
