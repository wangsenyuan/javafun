package java7.concurrent.practice.chapter6;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Created by senyuanwang on 15/5/31.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool pool = ForkJoinPool.commonPool();

        DelayQueue<Event> queue = new DelayQueue<>();
        for (int i = 0; i < 10; i++) {
            pool.submit(new FeedEventTask(queue, i));
        }

        pool.awaitTermination(1, TimeUnit.SECONDS);

        int count = 0;
        do {
            Event event;

            do {
                event = queue.poll();
                if (event != null) {
                    count++;
                }
            } while (event != null);

            System.out.printf("At %s you have read %d events\n", new Date(), count);
            TimeUnit.MILLISECONDS.sleep(500);
        } while (queue.size() > 0);
    }
}
