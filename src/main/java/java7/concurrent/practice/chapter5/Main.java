package java7.concurrent.practice.chapter5;

import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
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
            handler.setLevel(Level.FINE);
        }
        // Set root logger level
        rootLogger.setLevel(Level.FINE);
    }

    public static void main(String[] args) {
        ArrayGenerator arrayGenerator = new ArrayGenerator(10000000);
        int[] array = arrayGenerator.get();
        TaskManager<Optional<Integer>> taskManager = new TaskManager<>();
        SearchArrayTask searchArrayTask = new SearchArrayTask(array, 0, array.length, 10000, taskManager, null);

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        Optional<Integer> result = forkJoinPool.invoke(searchArrayTask);

        if (result.isPresent()) {
            Logger.getGlobal().fine("target found at " + result.get());
        } else {
            Logger.getGlobal().fine("target not found.");
        }
    }
}
