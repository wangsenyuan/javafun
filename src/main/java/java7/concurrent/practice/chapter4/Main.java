package java7.concurrent.practice.chapter4;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by senyuanwang on 15/5/30.
 */
public class Main {
    static {
        // Get the root logger
        Logger rootLogger = Logger.getLogger("");
        for (Handler handler : rootLogger.getHandlers()) {
            // Change log level of default handler(s) of root logger
            // The paranoid would check that this is the ConsoleHandler ;)
            handler.setLevel(Level.FINEST);
        }
        // Set root logger level
        rootLogger.setLevel(Level.FINE);
    }

    public static void main(String[] args) {

        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        executorService.setRejectedExecutionHandler((runnable, pool) -> {
            Logger.getGlobal().fine(String.format("%s has been rejected", runnable.toString()));
            Logger.getGlobal().fine(String.format("pool is terminating? %s", pool.isTerminating()));
            Logger.getGlobal().fine(String.format("pool is terminated? %s", pool.isTerminated()));
        });

        for (int i = 0; i < 10; i++) {
            executorService.submit(new Task(i));
        }

        executorService.shutdown();

        executorService.submit(new Task(10));
    }

    static class Task implements Runnable {
        final int label;

        Task(int label) {
            this.label = label;
        }

        @Override
        public void run() {
            Logger.getGlobal().fine("Task " + label + " is running.");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Logger.getGlobal().fine("Task " + label + " done!");
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Task{");
            sb.append("label=").append(label);
            sb.append('}');
            return sb.toString();
        }
    }
}
