package com.programming.pearls.problems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;

import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import javafx.util.Pair;

/**
 * @author dufeng
 * Created on 2020-12-12
 */
public class SearchAbsentNum {

    public static int MASK = 0x1;

    //内存中每次可以多有bytes
    public static int MAX_BYTES = 1024;

    private static int maxSize = 32;


    //二分搜索
    public static Map<Boolean, List<Long>> binarySplitByBit(List<Long>data, int i) {
        Map<Boolean, List<Long>> res = Maps.newHashMap();
        res.put(true, new ArrayList<>());
        res.put(false, new ArrayList<>());
        for (long d : data) {
            res.get((d >> i & 1) == 1).add(d);
        }
        return res;
    }

    public static long getAbsent(String originalPath, String tempPath1, String tempPath2) throws IOException {
        List<Boolean> booleans = Lists.newArrayList();
        File original = new File(originalPath);
        File zero = new File(tempPath1);
        File one = new File(tempPath2);
        int round = 31;
        int r = 31;
        Pair<Integer, Integer> p = checkOneRound(original, zero, one, round);
        while (round >= 0 && (p.getKey() == 0 || p.getValue() == 0)) {
            round--;
            boolean zeroLess = p.getKey() < p.getValue();
            booleans.add(zeroLess);
            // TODO: 2020/12/12 修复这个bug
            p = checkOneRound(zeroLess ? zero : one, original, one, round);
        }
        long res = 0L;
        for (boolean ifOne : booleans) {
            if (ifOne) {
                res += 1 << r;
            }
            r--;
        }
        return res;
    }

    public static Pair<Integer, Integer> checkOneRound(File original, File zero, File one, int round) throws IOException {
        BufferedReader oriBR = new BufferedReader(new FileReader(original));
        BufferedWriter zeroBW = new BufferedWriter(new FileWriter(zero));
        BufferedWriter oneBW = new BufferedWriter(new FileWriter(one));

        List<Long> tempLongs = Lists.newArrayList();
        int size = 0;
        int zeroCount = 0;
        int oneCount = 0;
        String line = "";
        while ((line = oriBR.readLine()) != null) {
            tempLongs.add(NumberUtils.toLong(line));
            size++;
            if (size == maxSize) {
                Map<Boolean, List<Long>> tempSplit = binarySplitByBit(tempLongs, round);
                List<Long> ones = tempSplit.get(true);
                List<Long> zeros = tempSplit.get(false);
                oneCount += ones.size();
                zeroCount += zeros.size();
                ones.forEach(l -> writeLine(oneBW, l.toString()));
                zeros.forEach(l -> writeLine(zeroBW, l.toString()));
                oneBW.flush();
                zeroBW.flush();
            }
        }
        return new Pair<>(zeroCount, oneCount);
    }

    public static void writeLine(BufferedWriter bf, String content) {
        try {
            bf.write(content + "\n");
        } catch (IOException e) {
            Throwables.throwIfUnchecked(e);
            throw new RuntimeException(e);
        }
    }


}
