package java8.learn.chapter5;

import java.time.LocalDate;

/**
 * Created by senyuanwang on 15/5/18.
 */
public class Exercise4 {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Exercise4 3 2013");
        }

        int month = Integer.parseInt(args[0]);
        int year = Integer.parseInt(args[1]);

        LocalDate cal = LocalDate.of(year, month, 1);

        LocalDate nextMonth = cal.plusMonths(1);

        System.out.println("SUN MON TUE WED THU FRI SAT");

        int start = cal.getDayOfWeek().getValue() % 7;

        for (int i = 0; i < start; i++) {
            System.out.print("    ");
        }

        for (int i = 1; i < 32; i++) {
            System.out.print(String.format("%3d", i));
            start = (start + 1) % 7;
            if(start == 0) {
                System.out.println();
            } else {
                System.out.print(" ");
            }
            if(cal.plusDays(i - 1).isEqual(nextMonth)) {
                break;
            }
        }
    }
}
