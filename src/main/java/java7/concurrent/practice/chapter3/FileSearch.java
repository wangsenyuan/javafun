package java7.concurrent.practice.chapter3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by senyuanwang on 15/5/30.
 */
public class FileSearch implements Runnable {

    private String initPath;
    private String end;
    private List<Path> results;
    private Phaser phaser;

    public FileSearch(String initPath, String end, Phaser phaser) {
        this.initPath = initPath;
        this.end = end;
        this.phaser = phaser;
    }

    @Override
    public void run() {
        phaser.arriveAndAwaitAdvance();
        Logger.getGlobal().fine(() ->
                        String.format("%s: Starting.\n", Thread.currentThread().getName())
        );
        collectResults();
        if (!checkResults()) {
            return;
        }
        filterResults();

        if (!checkResults()) {
            return;
        }

        showInfo();

        phaser.arriveAndDeregister();

        Logger.getGlobal().fine(String.format("%s: Work completed.\n", Thread.currentThread().getName()));
    }

    private void showInfo() {
        results.forEach(path -> {
            Logger.getGlobal().fine(() ->
                            String.format("%s: %s\n", Thread.currentThread().getName(), path.toAbsolutePath().toString())
            );
        });
        phaser.arriveAndAwaitAdvance();
    }

    private void collectResults() {
        try (Stream<Path> files = Files.walk(Paths.get(initPath))) {
            results = files.peek(path -> System.out.println(path.toString())).filter(path -> !Files.isDirectory(path) && path.toString().endsWith(end)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void filterResults() {
        long actualDate = System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS);
        results = results.stream().filter(path -> path.toFile().lastModified() > actualDate).collect(Collectors.toList());
    }

    private boolean checkResults() {
        long count = results.size();
        if (count == 0) {
            Logger.getGlobal().fine(() -> {
                String message0 = String.format("%s: Phase %d: 0 results\n", Thread.currentThread().getName(), phaser.getPhase());
                String message1 = String.format("%s: Phase %d: End.\n", Thread.currentThread().getName(), phaser.getPhase());
                return message0 + message1;
            });
            phaser.arriveAndDeregister();
            return false;
        } else {
            Logger.getGlobal().fine(() ->
                            String.format("%s: Phase %d: %d results.\n", Thread.currentThread().getName(), phaser.getPhase(), count)
            );
            phaser.arriveAndAwaitAdvance();
            return true;
        }
    }
}
