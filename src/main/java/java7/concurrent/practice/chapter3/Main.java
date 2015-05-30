package java7.concurrent.practice.chapter3;

import java.util.concurrent.Phaser;
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
        Phaser phaser = new Phaser(2);

        FileSearch searchChapter3 = new FileSearch("src/main/java/java7/concurrent/practice/chapter3", "java", phaser);
        FileSearch searchDownload = new FileSearch("/Users/senyuanwang/Downloads", ".JPG", phaser);

        Thread searchChapter3Thread = new Thread(searchChapter3, "Chapter3");
        Thread searchDownloadThread = new Thread(searchDownload, "Downloads");

        searchChapter3Thread.start();
        searchDownloadThread.start();

        try {
            searchChapter3Thread.join();
            searchDownloadThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
