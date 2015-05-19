package java8.learn.chapter6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Created by senyuanwang on 15/5/19.
 */
public class ArraysEx {

    public void parallelSort() throws IOException {
        String contents =
                new String(Files.readAllBytes(Paths.get("src/main/resources/two cities.txt")));
        String[] words = contents.split("[\\P{L}]");
        System.out.println(words.length);
        for (int i = 0; i < 10; i++) {
            System.out.println(words[i]);
        }
        Arrays.parallelSort(words);
        for (int i = 0; i < 10; i++) {
            System.out.println(words[i]);
        }
    }

    public static int product(int a, int b) {
        return a * b;
    }

    public void parallePrefix() {
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8};
        Arrays.parallelPrefix(values, ArraysEx::product);
        for(int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
        }
    }
}
