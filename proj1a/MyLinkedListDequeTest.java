import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MyLinkedListDequeTest {
    @Test
    public void testGet() {
        LinkedListDeque <String> myList1 = new LinkedListDeque<>();
        myList1.addFirst("first");
        myList1.addLast("second");
        assertEquals("first", myList1.get(0));
        assertEquals("second", myList1.get(1));
        assertEquals(null, myList1.get(2));
        myList1.removeFirst();
        assertEquals("second", myList1.get(0));
        myList1.removeLast();
        assertEquals(null, myList1.get(0));
        assertEquals(0, myList1.size());

        LinkedListDeque <Integer> myList2 = new LinkedListDeque<>();
        myList2.addLast(2);
        myList2.addLast(3);
        myList2.addFirst(1);
        assertEquals(3, (int)myList2.get(2));
        myList2.removeFirst();
        myList2.removeFirst();
        assertEquals(3, (int)myList2.get(0));
        assertEquals(1, myList2.size());
    }

    @Test
    public void testGetR() {
        LinkedListDeque <String> myList = new LinkedListDeque<>();
        myList.addFirst("first");
        myList.addLast("second");
        assertEquals("first", myList.getRecursive(0));
        assertEquals("second", myList.getRecursive(1));
        assertEquals(null, myList.getRecursive(2));
        myList.removeFirst();
        assertEquals("second", myList.getRecursive(0));
        myList.removeLast();
        assertEquals(null, myList.getRecursive(0));
        assertEquals(0, myList.size());
    }
}
