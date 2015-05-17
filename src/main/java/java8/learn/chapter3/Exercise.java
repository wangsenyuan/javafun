package java8.learn.chapter3;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.Comparator;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by senyuanwang on 15/5/10.
 */
public class Exercise {

    public static void logIf(Level level, Supplier<Boolean> predicate, Supplier<String> supplier) {
        if (Logger.getGlobal().isLoggable(level) && predicate.get()) {
            Logger.getGlobal().log(level, supplier.get());
        }
    }

    public static <T> void withLock(ReentrantLock lock, Supplier<T> action) {
        lock.lock();
        try {
            action.get();
        } finally {
            lock.unlock();
        }
    }

    public static <T> Image transform(Image in, BiFunction<Color, T, Color> f, T arg) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < width; y++) {
                out.getPixelWriter().setColor(x, y, f.apply(in.getPixelReader().getColor(x, y), arg));
            }
        }
        return out;
    }

    public static Comparator<String> stringComparator(BiFunction<String, String, Integer> f) {
        return (x, y) -> f.apply(x, y);
    }

    public static void exercise10() {
//        Image finalImage = transform(null, Color::brighter);
    }
}
