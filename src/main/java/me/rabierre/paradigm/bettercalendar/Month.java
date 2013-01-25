package me.rabierre.paradigm.bettercalendar;

/**
 * Created with IntelliJ IDEA.
 * User: seojihye
 * Date: 13. 1. 24.
 * Time: 오전 2:08
 * To change this template use File | Settings | File Templates.
 */
public class Month {
    private int month;
    private int length;
    private int startDate; // 1:mon 2:tue 3:wed 4:thu 5:fri 6 :sat 0:sun

    public Month(int month, int length, int startDate) {
        this.month = month;
        this.length = length;
        this.startDate = startDate;
    }

    public void print() {
        System.out.println("MONTH : " + month);
        System.out.println("sun\tmon\ttue\twed\tthu\tfri\tsat");

        String days = "";

        for (int i = 1; i <= startDate; i++) {
            days += "\t";
        }

        for (int i = 1; i <= length; i++) {
            days += i;
            days += ((i + startDate) % 7 == 0 ? "\n" : "\t");
        }

        System.out.println(days + "\n");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Month)) return false;

        Month month1 = (Month) o;

        if (length != month1.length) return false;
        if (month != month1.month) return false;
        if (startDate != month1.startDate) return false;

        return true;
    }
}
