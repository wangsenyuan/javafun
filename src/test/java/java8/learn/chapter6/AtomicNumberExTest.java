package java8.learn.chapter6;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by senyuanwang on 15/5/18.
 */
public class AtomicNumberExTest {

    AtomicNumberEx atomicNumberEx;

    @Before
    public void setUp() {
        atomicNumberEx = new AtomicNumberEx();
    }

    @Test
    public void testCompareAndSet() throws InterruptedException {
        atomicNumberEx.tryCompareAndSet();
    }

    @Test
    public void testUpdateAndGet() throws InterruptedException {
        atomicNumberEx.tryUpdateAndGet();
    }

    @Test
    public void testAccumulateAndGet() throws InterruptedException {
        atomicNumberEx.tryAccumulateAndGet();
    }
}
