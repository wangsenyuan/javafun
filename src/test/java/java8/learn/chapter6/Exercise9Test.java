package java8.learn.chapter6;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by senyuanwang on 15/5/20.
 */
public class Exercise9Test {

    Exercise9 exercise9;

    @Before
    public void setUp() {
        this.exercise9 = new Exercise9();
    }

    @Test
    public void test3FibNum() {
        int res = exercise9.fib(3);
        Assert.assertEquals(3, res);
    }

    @Test
    public void test4FibNum() {
        int res = exercise9.fib(4);
        Assert.assertEquals(5, res);
    }

    public void test6FibNum() {
        int res = exercise9.fib(6);
        Assert.assertEquals(13, res);
    }
}
