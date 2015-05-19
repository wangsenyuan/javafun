package java8.learn.chapter6;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by senyuanwang on 15/5/19.
 */
public class ArraysExTest {

    ArraysEx arraysEx;

    @Before
    public void setUp() {
        arraysEx = new ArraysEx();
    }

    @Test
    public void testParalleSort() throws IOException {
        arraysEx.parallelSort();
    }

    @Test
    public void testParallePrefix() {
        arraysEx.parallePrefix();
    }
}
