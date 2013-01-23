package me.rabierre.paradigm.bettercalendar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: seojihye
 * Date: 13. 1. 24.
 * Time: 오전 2:08
 * To change this template use File | Settings | File Templates.
 */
public class Year {
    private int year;
    private List<Month> months;

    public Year(int year) {
        this.year = year;
        setMonths();
    }

    private void setMonths() {
        months = MonthFactory.getMonths(year);
    }

    public void print() {
        System.out.println("THIS YEAR IS : " + year + "\n\n");

        for (Month m : months) {
            m.print();
        }
    }
}
