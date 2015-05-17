package java8.learn.chapter5;

import java.time.LocalDate;

/**
 * Created by senyuanwang on 15/5/17.
 */
public class LocalDateEx {

    public LocalDate now() {
        return LocalDate.now();
    }

    public static void main(String[] args) {
        LocalDateEx localDateEx = new LocalDateEx();
        System.out.println(localDateEx.now());
    }
}
