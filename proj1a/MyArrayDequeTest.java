import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MyArrayDequeTest {
    /**
     * This test tests the basis of addFirst, addLast,
     * removeFirst, and removeLast.
     * */
    @Test
    public void test1() {
        ArrayDeque<String> myArray1 = new ArrayDeque<>();
        myArray1.addFirst("aaa");
        myArray1.addFirst("bbb");
        myArray1.addFirst("ccc");
        myArray1.addLast("111");
        myArray1.addLast("222");
        myArray1.addLast("333");
        myArray1.addLast("444");
        myArray1.addLast("555");
        myArray1.addFirst("ddd");
        assertEquals("ddd", myArray1.removeFirst());
        assertEquals("ccc", myArray1.removeFirst());
        assertEquals("bbb", myArray1.removeFirst());
        assertEquals("aaa", myArray1.removeFirst());
        assertEquals("555", myArray1.removeLast());
        assertEquals("444", myArray1.removeLast());
        assertEquals("111", myArray1.removeFirst());
        assertEquals(2, myArray1.size());
    }

    /**
     * More tests.
     * */
    @Test
    public void test2() {
        ArrayDeque<String> myArray2 = new ArrayDeque<>();
        myArray2.addLast("111");
        myArray2.addLast("222");
        myArray2.addLast("333");
        myArray2.addLast("444");
        myArray2.addLast("555");
        myArray2.addLast("666");
        myArray2.addLast("777");
        myArray2.addLast("888");
        myArray2.addLast("999");
        assertEquals("111", myArray2.removeFirst());
        myArray2.addFirst("bbb");
        myArray2.addFirst("aaa");
        assertEquals("999", myArray2.removeLast());
        assertEquals("aaa", myArray2.removeFirst());
        assertEquals("bbb", myArray2.removeFirst());
        assertEquals("222", myArray2.removeFirst());
        assertEquals("333", myArray2.removeFirst());
        assertEquals("444", myArray2.removeFirst());
        assertEquals("555", myArray2.removeFirst());
        assertEquals("666", myArray2.removeFirst());
        myArray2.addFirst("666");
        myArray2.addFirst("555");
        myArray2.addFirst("444");
        myArray2.addFirst("333");
        myArray2.addFirst("222");
        myArray2.addFirst("111");
        myArray2.printDeque();
        assertEquals("888", myArray2.get(7));
        assertEquals("111", myArray2.get(0));
        assertEquals("888", myArray2.removeLast());
        assertEquals("777", myArray2.removeLast());
        assertEquals("666", myArray2.removeLast());
        assertEquals("555", myArray2.removeLast());
        assertEquals("444", myArray2.removeLast());
        assertEquals("333", myArray2.removeLast());
        assertEquals("222", myArray2.removeLast());
        assertEquals("111", myArray2.removeLast());
        assertEquals(null, myArray2.removeLast());
        myArray2.printDeque();
    }
}
