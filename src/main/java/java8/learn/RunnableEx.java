package java8.learn;


/**
 * Created by senyuanwang on 15/4/19.
 */
@FunctionalInterface
public interface RunnableEx {

    public void apply() throws Throwable;
    public static Runnable uncheck(RunnableEx runnableEx) {
        return () -> {
            try {
                runnableEx.apply();
            } catch (Throwable ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    public static Runnable andThen(Runnable a, Runnable b) {
        return uncheck(() -> {
            a.run();
            b.run();
        });
    }
}
