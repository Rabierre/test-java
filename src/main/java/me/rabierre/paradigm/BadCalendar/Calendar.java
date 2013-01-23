package me.rabierre.paradigm.badcalendar;

/**
 * Created with IntelliJ IDEA.
 * User: seojihye
 * Date: 13. 1. 21.
 * Time: 오후 4:51
 * To change this template use File | Settings | File Templates.
 */
public class Calendar {
    private int year;
    private boolean leapYear;
    private int startDay;
    private Month[] months = new Month[12];

    public Calendar() {
    }

    public Calendar(int year) {
        this.year = year;
        this.leapYear = isLeapYear(year);
        this.startDay = getStartDayOfYear();
        setMonths();
    }

    private void setMonths() {
        for (int i = 0; i < months.length; i++) {
            months[i] = new Month(i + 1, startDay, leapYear);
        }
    }

    public void print(int distance) {
        for (Month m : months) {
            m.printDays();
        }
    }

    private int getStartDayOfYear() {
        int totalDays = 0;
        for (int i = 1; i < year; i++) {
            totalDays += (isLeapYear(i) ? 366 : 365);
        }
        return totalDays;
    }

    public boolean isLeapYear(int year) {
        return (year % 400 == 0) || (year % 4 == 0) && (year % 100 != 0) ? true : false;    // FP!
    }

    public Month[] getMonths() {
        return months;
    }

    public int getStartDay() {
        return startDay;
    }
}
