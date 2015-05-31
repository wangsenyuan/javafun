package java7.concurrent.practice.chapter5;

import java.util.Random;
import java.util.function.Supplier;

/**
 * Created by senyuanwang on 15/5/30.
 */
public class ArrayGenerator implements Supplier<int[]> {
    private final int size;

    public ArrayGenerator(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public int[] get() {
        int[] array = new int[size];

        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = Math.abs(random.nextInt()) % 1000001;
        }
        return array;
    }
}
