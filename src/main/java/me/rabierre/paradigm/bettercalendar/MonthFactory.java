package me.rabierre.paradigm.bettercalendar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: seojihye
 * Date: 13. 1. 24.
 * Time: 오전 2:08
 * To change this template use File | Settings | File Templates.
 */
public class MonthFactory {
    public static List<Month> getMonths(int year) {
        List<Month> months = new ArrayList();

        for (int i = 1; i <= 12; i++) {
            months.add(new Month(i, getDaysOfMonth(year, i), getStartDate(year, i)));
        }

        return months;
    }

    public static int getDaysOfYear(int year) {
        return isLeap(year) ? 366 : 365;
    }

    public static int getDaysOfMonth(int year, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return (isLeap(year) ? 29 : 28);
            default:
                return 0;
        }
    }

    public static boolean isLeap(int year) {
        return (year % 400 == 0) || (year % 4 == 0) && (year % 100 != 0);
    }

    public static int getStartDate(int year, int month) {
        int totalDays = 0;

        // add days of years
        for (int i = 1; i < year; i++) {
            totalDays += getDaysOfYear(i);
        }

        // add days of months
        for (int i = 1; i < month; i++) {
            totalDays += getDaysOfMonth(year, i);
        }

        return (totalDays + 1) % 7;   // +1 for sunday
    }
}
