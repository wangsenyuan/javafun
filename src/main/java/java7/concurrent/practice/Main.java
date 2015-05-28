package java7.concurrent.practice;

import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by senyuanwang on 15/5/27.
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

    public static void main(String[] args) throws InterruptedException {
//        Logger.getGlobal().setLevel(Level.FINE);

        Handler[] handlers =
                Logger.getGlobal().getHandlers();
        for (int index = 0; index < handlers.length; index++) {
            handlers[index].setLevel(Level.FINE);
        }


        FileMock fileMock = new FileMock(100, 10);
        Buffer buffer = new Buffer(20);

        Producer producer = new Producer(fileMock, buffer);

        Thread producerThread = new Thread(producer, "Producer");

        Consumer[] consumers = new Consumer[3];
        Thread[] consumerThreads = new Thread[3];
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Consumer(buffer);
            consumerThreads[i] = new Thread(consumers[i], "Consumer-" + i);
        }

        producerThread.start();
        for (int i = 0; i < consumerThreads.length; i++) {
            consumerThreads[i].start();
        }
        producerThread.join();
    }

}

class Producer implements Runnable {
    private final FileMock fileMock;
    private final Buffer buffer;

    Producer(FileMock fileMock, Buffer buffer) {
        this.fileMock = fileMock;
        this.buffer = buffer;
    }


    @Override
    public void run() {
        buffer.setPendingLines(true);
        while (fileMock.hasMoreLines()) {
            String line = fileMock.getLine();
            buffer.insert(line);
        }
        buffer.setPendingLines(false);
    }
}

class Consumer implements Runnable {
    private final Buffer buffer;

    Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (buffer.hasPendingLines()) {
            processLine(buffer.get());
        }
    }

    private void processLine(String s) {
        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}