package java7.concurrent.practice.chapter6;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.concurrent.DelayQueue;

/**
 * Created by senyuanwang on 15/5/31.
 */
public class FeedEventTask implements Runnable {
    private final DelayQueue<Event> delayQueue;
    private final int delayOffset;

    public FeedEventTask(DelayQueue<Event> delayQueue, int delayOffset) {
        this.delayQueue = delayQueue;
        this.delayOffset = delayOffset;
    }


    @Override
    public void run() {
        Instant current = Instant.now();
        for (int i = 0; i < 100; i++) {
            Instant fireTime = current.plusSeconds(i + delayOffset);
            Event event = new Event(fireTime);
            delayQueue.offer(event);
        }
    }
}
