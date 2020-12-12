import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.programming.pearls.data.gen.DataGenerator;
import com.programming.pearls.problems.SortNumbers;

/**
 * @author dufeng
 * Created on 2020-12-08
 */
public class TestSortNumbers {
    @Test
    public void BitBitSort() {
        //按照100的最大值，应该new出来 int[4]。
        int[] testData = DataGenerator.genRandomKFromN(100, 20);
        int[] res = SortNumbers.bitSort(testData, 100);
        int[] testCopy = Arrays.copyOf(testData, testData.length);
        Arrays.sort(testData);
        Assert.assertArrayEquals(res, testData);
        for (int i = 0; i < res.length; i++) {
            System.out.println(String.format("res %s, testDataCopy %s", res[i], testCopy[i]));
        }
    }
}
