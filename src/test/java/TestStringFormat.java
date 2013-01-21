import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: seojihye
 * Date: 13. 1. 19.
 * Time: 오전 2:28
 * To change this template use File | Settings | File Templates.
 */
public class TestStringFormat {
    @Test
    public void testStringFormat() {
        String helloWorld = String.format("%s", "hello world!");

        assertThat(helloWorld, is("hello world!"));
    }

    @Test
    public void testDateFormat() {
        Date date = new Date(System.currentTimeMillis());
        String stringDate = String.format("Today's date is '%ty %tm %td", date, date, date);
        System.out.println(stringDate);

        stringDate = String.format("Today's date is %tY년 %tB %te일", date, date, date);
        System.out.println(stringDate);

        stringDate = String.format("Time is %tH시 %tM분 %tS초", date, date, date);
        System.out.println(stringDate);

    }

    @Test
    public void testIndent() {
        String s = String.format("Amount : %08d", 221);
        System.out.println(s);

        System.out.printf("Amount : %08d %n" , 221/60);
    }
}
