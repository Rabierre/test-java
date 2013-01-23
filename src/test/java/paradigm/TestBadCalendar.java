package paradigm;

import me.rabierre.paradigm.BadCalendar;
import me.rabierre.paradigm.BadMonth;
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
        BadCalendar badCalendar = new BadCalendar();

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
        BadCalendar calendar = new BadCalendar(year);

        boolean isLeap = calendar.isLeapYear(year);
        assertThat(isLeap, is(false));

        calendar.print(2013);
    }

    @Test
    public void testMonthArray() {
        /** 2013 */
        BadCalendar calendar = new BadCalendar(2013);
        int startDay = calendar.getStartDay();

        BadMonth[] actual = calendar.getMonths();
        BadMonth[] expect = {new BadMonth(1, startDay, false), new BadMonth(2, startDay, false), new BadMonth(3, startDay, false),
                new BadMonth(4, startDay, false), new BadMonth(5, startDay, false), new BadMonth(6, startDay, false),
                new BadMonth(7, startDay, false), new BadMonth(8, startDay, false), new BadMonth(9, startDay, false),
                new BadMonth(10, startDay, false), new BadMonth(11, startDay, false), new BadMonth(12, startDay, false)};

        assertThat(actual, is(expect));
    }
}
