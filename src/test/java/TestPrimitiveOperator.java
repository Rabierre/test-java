import junit.framework.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created with IntelliJ IDEA.
 * User: seojihye
 * Date: 13. 1. 12.
 * Time: 오후 6:34
 * To change this template use File | Settings | File Templates.
 */
public class TestPrimitiveOperator {
    @Test
    public void testRightShift() {
        int number = 8;
        number = number >> 1;

        assertEquals(number, is(4));
    }

    @Test
    public void testLeftShift() {
        int number = 4;
        number = number << 1;

        assertEquals(number, is(8));
    }

    @Test
    public void testBitMask() {
        int number = -16541789;
        System.out.println(Integer.toBinaryString(number));

        number = (number & 0x7fffffff);
        System.out.println(Integer.toBinaryString(number));

        number = (number & 0x000000ff);
        System.out.println(Integer.toBinaryString(number));

        int intMax = 0x7fffffff;    // intMax
        System.out.println(intMax);
        int negativeInt = 0xffffffff;        // -1, 0이 아닌 이유는 0x00000000이 있기때문.
        System.out.println(negativeInt); //최상위비트가 1이면 값은 -1 + -(나머지 31개의 비트값)이 된다.
        negativeInt += 0x7fffffff;   // intMax - 1, 위에서 0을 건너뛰고 -1이 되었으므로 +1을 더 해줘야함.
        System.out.println(negativeInt);

       // assertEquals(number, is(3));
    }
}
