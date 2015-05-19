package java8.learn.chapter6;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by senyuanwang on 15/5/19.
 */
public class MapEx {

    public static void newKeySet() {
        Set<String> set = ConcurrentHashMap.<String>newKeySet();
        set.add("abc");
        System.out.println(set);
        set.remove("abc");
        System.out.println(set);
    }
}
