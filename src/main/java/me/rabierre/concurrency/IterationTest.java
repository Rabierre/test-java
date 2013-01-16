package me.rabierre.concurrency;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: seojihye
 * Date: 13. 1. 12.
 * Time: 오전 1:53
 * To change this template use File | Settings | File Templates.
 */
public class IterationTest {
    Map<Integer, String> testMap1;
    Map<Integer, String> testMap2;

    /**
     * Iterator.remove() method is safe
     */
    public void removeFromKeyObj() {
        Iterator<Integer> itKey = testMap1.keySet().iterator();

        while (itKey.hasNext()) {
            itKey.next();
            itKey.remove(); // fine
        }

        System.out.println(" TestMap1 Size : " + testMap1.size());
    }

    /**
     * Map.remove(i) is unsafe here. if map has changed "Iterator" throw ConcurrentModificationException
     *
     * see javadoc
     *  The iterators returned by this class's iterator and listIterator methods are fail-fast:
     *  if the list is structurally modified at any time after the iterator is created,
     *  in any way except through the iterator's own remove or add methods,
     *  the iterator will throw a ConcurrentModificationException.
     */
    public void removeFromKeyFromLoop() {

        Iterator<Integer> itKey = testMap2.keySet().iterator();

        while (itKey.hasNext()) {
            Integer index = itKey.next();
            testMap2.remove(index);        // error
        }

        System.out.println(" TestMap2 Size : " + testMap2.size());
    }

    public void initTable() {

        this.testMap1 = new HashMap<Integer, String>();
        this.testMap2 = new HashMap<Integer, String>();


        for (int i = 0; i < 10; i++) {
            testMap1.put(i, i + "");
            testMap2.put(i + 10, (i + 10) + "");
        }

    }

    public static void main(String[] args) {

        IterationTest test = new IterationTest();
        test.initTable();
        test.removeFromKeyObj();
        test.removeFromKeyFromLoop();
    }
}
