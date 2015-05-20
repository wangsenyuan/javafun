package java8.learn.chapter6;

import java.util.concurrent.atomic.LongAccumulator;

/**
 * Created by senyuanwang on 15/5/20.
 */
public class Exercise4 {

    public long maxInArray(long[] nums) {
        LongAccumulator longAccumulator = new LongAccumulator(Math::max, Long.MIN_VALUE);
        for (int i = 0; i < nums.length; i++) {
            longAccumulator.accumulate(nums[i]);
        }
        return longAccumulator.get();
    }
}
