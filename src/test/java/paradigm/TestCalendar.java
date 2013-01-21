package paradigm;

import me.rabierre.paradigm.Calendar;
import me.rabierre.paradigm.Month;
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
public class TestCalendar {
    @Test
    public void testIsLeapYear() {
        Calendar calendar = new Calendar();

        int year = 2000;
        boolean isLeap = calendar.isLeapYear(year);
        assertThat(isLeap, is(true));

        year = 1700;
        isLeap = calendar.isLeapYear(year);
        assertThat(isLeap, is(false));

        year = 2004;
        isLeap = calendar.isLeapYear(year);
        assertThat(isLeap, is(true));
    }

    @Test
    public void testPrint() {
        int year = 2013;
        Calendar calendar = new Calendar(year);

        boolean isLeap = calendar.isLeapYear(year);
        assertThat(isLeap, is(false));

        calendar.print(1);
    }

    @Test
    public void testMonthArray() {
        int year = 2013;
        Calendar calendar = new Calendar(year);

        Month[] actual = calendar.getMonths();
        Month[] expect = {new Month(1), new Month(2), new Month(3), new Month(4), new Month(5), new Month(6),
                new Month(7), new Month(8), new Month(9), new Month(10), new Month(11), new Month(12)};

        assertThat(actual, is(expect));
    }
}
