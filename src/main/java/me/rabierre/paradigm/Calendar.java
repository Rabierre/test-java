package me.rabierre.paradigm;

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
    private Month[] months = new Month[12];

    public Calendar() {
    }

    public Calendar(int year) {
        this.year = year;
        leapYear = isLeapYear(year);
        setMonths();
    }

    private void setMonths() {
        for (int i = 0; i < months.length; i++) {
            months[i] = new Month(i + 1);
        }
    }

    public void print(int distance) {
        for (Month m : months) {
            m.print(leapYear);
        }
    }


    public boolean isLeapYear(int year) {
        // FP!
        return (year % 400 == 0) || (year % 4 == 0) && (year % 100 != 0) ? true : false;
    }

    public Month[] getMonths() {
        return months;
    }
}
