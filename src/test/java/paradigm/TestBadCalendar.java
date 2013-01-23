package paradigm;

import me.rabierre.paradigm.badcalendar.Calendar;
import me.rabierre.paradigm.badcalendar.Month;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: seojihye
 * Date: 13. 1. 21.
 * Time: 오후 5:05
 * To change this template use File | Settings | File Templates.
 */
public class TestBadCalendar {
    @Test
    public void testIsLeapYear() {
        Calendar badCalendar = new Calendar();

        int year = 2000;
        boolean isLeap = badCalendar.isLeapYear(year);
        assertThat(isLeap, is(true));

        year = 1700;
        isLeap = badCalendar.isLeapYear(year);
        assertThat(isLeap, is(false));

        year = 2004;
        isLeap = badCalendar.isLeapYear(year);
        assertThat(isLeap, is(true));
    }

    @Test
    public void testPrint() {
        int year = 2013;
        Calendar calendar = new Calendar(year);

        boolean isLeap = calendar.isLeapYear(year);
        assertThat(isLeap, is(false));

        calendar.print(2013);
    }

    @Test
    public void testMonthArray() {
        /** 2013 */
        Calendar calendar = new Calendar(2013);
        int startDay = calendar.getStartDay();

        Month[] actual = calendar.getMonths();
        Month[] expect = {new Month(1, startDay, false), new Month(2, startDay, false), new Month(3, startDay, false),
                new Month(4, startDay, false), new Month(5, startDay, false), new Month(6, startDay, false),
                new Month(7, startDay, false), new Month(8, startDay, false), new Month(9, startDay, false),
                new Month(10, startDay, false), new Month(11, startDay, false), new Month(12, startDay, false)};

        assertThat(actual, is(expect));
    }
}
