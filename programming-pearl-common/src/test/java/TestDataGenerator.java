import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.programming.pearls.data.gen.DataGenerator;

/**
 * @author frankdu
 */
public class TestDataGenerator {

    @Test
    public void testGenRandomKFromN() {
        int[] r = DataGenerator.genRandomKFromN(10000, 9000);
        Arrays.stream(r).forEach(i -> {
            Assert.assertTrue( i < 10000 && i >= 0);
        });
        Assert.assertEquals(9000, Arrays.stream(r).distinct().count());
    }
}
