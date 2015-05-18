package java8.learn.chapter6;

import java8.learn.RunnableEx;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;

/**
 * Created by senyuanwang on 15/5/18.
 */
public class LongAdderEx {

    public void timestamp(Runnable func) {
        Instant start = Instant.now();
        func.run();
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println("it takes " + duration.toNanos() + " nano seconds to process");
    }

    public void runInPool(Consumer<Integer> func) throws InterruptedException {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        for (int i = 0; i < 4; i++) {
            int x = i;
            forkJoinPool.submit(() -> {
                timestamp(() -> {
                    for (int j = x * 4; j < x * 100000; j += x) {
                        func.accept(j);
                    }
                });
            });
        }

        forkJoinPool.awaitTermination(10, TimeUnit.SECONDS);
    }

    public void testAtomicLong() throws InterruptedException {
        AtomicLong largest = new AtomicLong();
//        timestamp(RunnableEx.uncheck(() -> )));
        runInPool(x -> largest.updateAndGet(y -> Math.max(x, y)));
        System.out.println(largest);
    }

    public void testLongAdder() throws InterruptedException {
        LongAdder longAdder = new LongAdder();
        runInPool(x -> longAdder.increment());
        System.out.println(longAdder.longValue());
    }

}
