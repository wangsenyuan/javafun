package java8.learn.chapter6;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by senyuanwang on 15/5/21.
 */
public class Exercise11 {

    public static <T> CompletableFuture<T> repeat(Supplier<T> action, Predicate<T> predicate) {
        return CompletableFuture.supplyAsync(action).thenCompose(t -> {
            if (predicate.test(t)) {
                return CompletableFuture.completedFuture(t);
            } else {
                return repeat(action, predicate);
            }
        });
    }

    public static <T> void repeatSync(Supplier<T> action, Predicate<T> predicate, Consumer<T> consumer) {
        repeat(action, predicate).thenAccept(consumer);
    }
}
