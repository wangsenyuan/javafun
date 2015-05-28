package oschina.questions;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by senyuanwang on 15/5/25.
 */
public class TimerExample {

    public static void main(String[] args) {
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("running");
            }
        };
        timer.schedule(task, 0L, 10L);
    }
}
