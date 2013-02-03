package me.rabierre.paradigm.badcalendar;

/**
 * Created with IntelliJ IDEA.
 * User: seojihye
 * Date: 13. 1. 21.
 * Time: 오후 5:59
 * To change this template use File | Settings | File Templates.
 */
public class Month {
    private int month;
    private int length;
    private int startDay;
    private boolean isLeapYear;
    // todo private Day day

    public Month(int month, int startDay, boolean isLeapYear) {
        this.month = month;
        this.length = getDays(month);
        this.isLeapYear = isLeapYear;
        this.startDay = getStartDayOfMonth(startDay);
    }

    private int getStartDayOfMonth(int startDay) {
        for (int i = 1; i < this.month; i++) {
            startDay += getDays(i);
        }
        return startDay % 7 + 1;
    }

    public int getMonth() {
        return month;
    }

    public int getDays(int month) {
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
                return (isLeapYear ? 29 : 28);
            default:
                return 0;
        }
    }

    public void printDays() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t\t[" + month + "]\n").append("mon\ttue\twed\tthu\tfri\tsat\tsun\n");

        for (int i = 1; i <= startDay; i++) {
            sb.append("\t");
        }

        for (int i = 1; i <= length; i++) {
            sb.append(i).append("\t");
            if ((i + startDay) % 7 == 0) {
                sb.append("\n");
            }
        }

        System.out.println(sb.append("\n").toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Month)) return false;

        Month month1 = (Month) o;

        if (month != month1.month) return false;

        return true;
    }
}
