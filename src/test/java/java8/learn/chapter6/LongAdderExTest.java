package java8.learn.chapter6;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by senyuanwang on 15/5/18.
 */
public class LongAdderExTest {

    LongAdderEx longAdderEx;

    @Before
    public void setUp() {
        longAdderEx = new LongAdderEx();
    }

    @Test
    public void testAtomicLong() throws InterruptedException {
        longAdderEx.testAtomicLong();
    }

    @Test
    public void testLongAdder() throws InterruptedException {
        longAdderEx.testLongAdder();
    }
}
