package java8.learn.chapter5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * Created by senyuanwang on 15/5/18.
 */
public class Exercise6 {

    public static void main(String[] args) {
        LocalDate century21 = LocalDate.of(2000, 1, 1);

        LocalDate century22 = LocalDate.of(2100, 1, 1);

        while(century21.isBefore(century22)) {
            century21 = century21.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
            System.out.println(century21);
            century21 = century21.plusDays(1);
        }
    }
}
