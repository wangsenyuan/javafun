package java8.learn.chapter6;

import java8.learn.RunnableEx;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * Created by senyuanwang on 15/5/18.
 */
public class AtomicNumberEx {

    public void tryCompareAndSet() throws InterruptedException {

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        AtomicLong largest = new AtomicLong();

        runInPool(x -> {
            long oldValue, newValue;
            do {
                oldValue = largest.get();
                newValue = Math.max(oldValue, x);
            } while(!largest.compareAndSet(oldValue, newValue));
        });

        forkJoinPool.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println(largest.get());
    }

    public void tryUpdateAndGet() throws InterruptedException {

        AtomicLong largest = new AtomicLong();

        runInPool(x -> largest.updateAndGet(y -> Math.max(x, y)));

        System.out.println(largest.get());
    }

    public void tryAccumulateAndGet() throws InterruptedException {
        AtomicLong largest = new AtomicLong();

        runInPool(x -> largest.accumulateAndGet(x, Math::max));

        System.out.println(largest.get());
    }

    public void runInPool(Consumer<Integer> func) throws InterruptedException {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        for(int i = 0; i < 4; i++) {
            int x = i;
            forkJoinPool.submit(() -> {
                for(int j = x * 4; j < x * 1000; j += x) {
                    func.accept(j);
                }
            });
        }

        forkJoinPool.awaitTermination(10, TimeUnit.SECONDS);
    }
}
