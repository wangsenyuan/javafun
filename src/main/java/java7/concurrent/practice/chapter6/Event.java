package java7.concurrent.practice.chapter6;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by senyuanwang on 15/5/31.
 */
public class Event implements Delayed {
    public final Instant fireDate;

    public Event(Instant fireDate) {
        this.fireDate = fireDate;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        Duration delay = Duration.between(Instant.now(), fireDate);
        return unit.convert(delay.toMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed that) {
        long diff = this.getDelay(TimeUnit.MILLISECONDS) - that.getDelay(TimeUnit.MILLISECONDS);
        if (diff < 0) return -1;
        else if (diff > 0) return 1;
        return 0;
    }
}
