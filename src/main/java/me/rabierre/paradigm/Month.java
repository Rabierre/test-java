package me.rabierre.paradigm;

/**
 * Created with IntelliJ IDEA.
 * User: seojihye
 * Date: 13. 1. 21.
 * Time: 오후 5:59
 * To change this template use File | Settings | File Templates.
 */
public class Month {
    private int month;
    private int startDay;
    // todo private Day day

    public Month(int month) {
        this.month = month;
    }

    public int getMonth() {
        return month;
    }

    void print(boolean leapYear) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                printDays(31);
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                printDays(30);
                break;
            case 2:
                printDays(leapYear ? 29 : 28);
        }
    }

    private void printDays(int days) {
        // todo date indentation need
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= days; i++) {
            sb.append(i).append("\t");
            if (i % 7 == 0) {
                sb.append("\n");
            }
        }

        System.out.println(sb.toString());
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
