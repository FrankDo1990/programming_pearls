import org.junit.Assert;
import org.junit.Test;

import com.programming.pearls.problems.BitVector;

/**
 * @author dufeng <dufeng@kuaishou.com>
 * Created on 2020-12-08
 */
public class TestVector {
    @Test
    public void testVector() {
        //按照100的最大值，应该new出来 int[4]。
        BitVector vector = new BitVector(100);
        int[] testData = {0, 7, 31, 32, 37, 100};
        for (int c : testData) {
            vector.set(c);
            Assert.assertTrue(vector.ifSet(c));
            vector.clr(c);
            Assert.assertFalse(vector.ifSet(c));
        }
    }
}
