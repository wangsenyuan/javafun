package java8.learn.chapter1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by senyuanwang on 15/4/19.
 */
public class Execise8 {

    public static void main(String[] args) {
        String[] names = {"apple", "pear", "orange"};

        List<Runnable> runnersA = test1(names);

        for(Runnable runner : runnersA) {
            runner.run();
        }

        List<Runnable> runnersB = test2(names);
        for(Runnable runner : runnersB) {
            runner.run();
        }

    }

    public static List<Runnable> test1(String[] names) {
        List<Runnable> runners = new ArrayList<>();

        for (String name : names) {
            runners.add(() -> {
                System.out.println(name);
            });
        }

        return runners;
    }

    public static List<Runnable> test2(String[] names) {
        List<Runnable> runners = new ArrayList<>();

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            runners.add(() -> {
                System.out.println(name);
            });
        }

        return runners;
    }
}
