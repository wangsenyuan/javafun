package java8.learn.chapter6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by senyuanwang on 15/5/20.
 */
public class Exercise5 {

    public ConcurrentMap<String, Set<File>> findWordsInFileByMerge(String dir) throws IOException {
        ConcurrentMap<String, Set<File>> map = new ConcurrentHashMap<>();

        Files.list(Paths.get(dir)).filter(x -> Files.isRegularFile(x)).forEach(p -> {
            try {
                File f = p.toFile();
                String contents = new String(Files.readAllBytes(p));
                String[] words = contents.split("[\\P{L}]");

                Set<File> set = new HashSet<>();
                set.add(f);
                for (String word : words) {
                    if (word.isEmpty()) {
                        continue;
                    }

                    map.merge(word, set, (x, y) -> {
                        x.addAll(y);
                        return x;
                    });
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        return map;
    }

    public ConcurrentMap<String, Set<File>> findWordsInFileByComputeIfAbsent(String dir) throws IOException {
        ConcurrentMap<String, Set<File>> map = new ConcurrentHashMap<>();

        Files.list(Paths.get(dir)).filter(x -> Files.isRegularFile(x)).forEach(p -> {
            try {
                File f = p.toFile();
                String contents = new String(Files.readAllBytes(p));
                String[] words = contents.split("[\\P{L}]");

                for (String word : words) {
                    if (word.isEmpty()) {
                        continue;
                    }

                    map.computeIfAbsent(word, k -> new HashSet<>()).add(f);
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        return map;
    }
}
