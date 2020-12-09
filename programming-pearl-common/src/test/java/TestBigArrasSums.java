import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Stopwatch;
import com.programming.pearls.data.gen.DataGenerator;
import com.programming.pearls.problems.BigArraySums;

/**
 * @author dufeng <dufeng@kuaishou.com>
 * Created on 2020-12-06
 */
public class TestBigArrasSums {

    private static int len = 5000;
    private static int[] data = DataGenerator.genRandomKFromN(len, len);

    @Test
    public void testAddBigAll() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        long res = BigArraySums.addBigAll(data);
        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        long simpleAdd = Arrays.stream(data).mapToObj(i -> new Long(i)).reduce(0L, (l1, l2) -> l1.longValue() + l2.longValue()).longValue();
        Assert.assertEquals(simpleAdd, res);
        System.out.println(String.format("simple add use %s ms, res = %s", elapsed, res));
    }

    @Test
    public void testAddBigAllWithMultiThreads() throws ExecutionException, InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        long res = BigArraySums.addBigAllWithMultiThreads(data);
        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        long simpleAdd = Arrays.stream(data).mapToObj(i -> new Long(i)).reduce(0L, (l1, l2) -> l1.longValue() + l2.longValue()).longValue();
        Assert.assertEquals(simpleAdd, res);
        System.out.println(String.format("multi threads add use %s ms, res = %s", elapsed, res));
    }

    @Test
    public void testForJoinCompute() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        long res = BigArraySums.forJoinCompute(data);
        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        long simpleAdd = Arrays.stream(data).mapToObj(i -> new Long(i)).reduce(0L, (l1, l2) -> l1.longValue() + l2.longValue()).longValue();
        Assert.assertEquals(simpleAdd, res);
        System.out.println(String.format("fork pool threads add use %s ms, res = %s", elapsed, res));
    }

}
