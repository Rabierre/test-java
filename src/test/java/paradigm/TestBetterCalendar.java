package paradigm;

import me.rabierre.paradigm.bettercalendar.Month;
import me.rabierre.paradigm.bettercalendar.MonthFactory;
import me.rabierre.paradigm.bettercalendar.Year;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: seojihye
 * Date: 13. 1. 24.
 * Time: 오전 2:12
 * To change this template use File | Settings | File Templates.
 */
public class TestBetterCalendar {
    @Test
    /** test MonthFactory isLeap method */
    public void test() {
        boolean isLeap = MonthFactory.isLeap(2012);
        assertThat(isLeap, is(true));

        isLeap = MonthFactory.isLeap(2013);
        assertThat(isLeap, is(false));

        isLeap = MonthFactory.isLeap(1700);
        assertThat(isLeap, is(false));
    }

    @Test
    /** test MonthFactory getDaysOfMonth method */
    public void test2() {
        int dayLength = MonthFactory.getDaysOfMonth(2013, 1);
        assertThat(dayLength, is(31));

        dayLength = MonthFactory.getDaysOfMonth(2013, 2);
        assertThat(dayLength, is(28));

        dayLength = MonthFactory.getDaysOfMonth(2013, 4);
        assertThat(dayLength, is(30));

        dayLength = MonthFactory.getDaysOfMonth(2012, 2);
        assertThat(dayLength, is(29));
    }

    @Test
    /** test MonthFactory getDaysOfYear method */
    public void test3() {
        int yearLength = MonthFactory.getDaysOfYear(4);
        assertThat(yearLength, is(366));

        yearLength = MonthFactory.getDaysOfYear(2012);
        assertThat(yearLength, is(366));

        yearLength = MonthFactory.getDaysOfYear(2013);
        assertThat(yearLength, is(365));
    }

    @Test
    /** test MonthFactory getStartDate method */
    public void test4() {
        int startDate = MonthFactory.getStartDate(2013, 1);
        assertThat(startDate, is(2));   // tue

        startDate = MonthFactory.getStartDate(2013, 2);
        assertThat(startDate, is(5));   // fri

        startDate = MonthFactory.getStartDate(2013, 3);
        assertThat(startDate, is(5));   // fri

        startDate = MonthFactory.getStartDate(2013, 4);
        assertThat(startDate, is(1));   // mon
    }

    @Test
    /** test MonthFactory getMonths method */
    public void test5() {
        List<Month> actual = MonthFactory.getMonths(2013);

        List<Month> expect = new ArrayList();
        expect.add(new Month(1, 31, 2));
        expect.add(new Month(2, 28, 5));
        expect.add(new Month(3, 31, 5));
        expect.add(new Month(4, 30, 1));
        expect.add(new Month(5, 31, 3));
        expect.add(new Month(6, 30, 6));
        expect.add(new Month(7, 31, 1));
        expect.add(new Month(8, 31, 4));
        expect.add(new Month(9, 30, 0));
        expect.add(new Month(10, 31, 2));
        expect.add(new Month(11, 30, 5));
        expect.add(new Month(12, 31, 0));

        assertThat(actual, is(expect));
    }

    @Test
    /** Month print method */
    public void test6() {
        new Month(1, 31, 2).print();
    }

    @Test
    /** Year print method */
    public void test7() {
        new Year(2013).print();
    }
}