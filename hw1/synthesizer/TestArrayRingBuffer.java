package synthesizer;
import org.junit.Test;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<Integer>(4);
        assertTrue(arb.isEmpty());
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        assertEquals(1, (int) arb.dequeue());
        assertEquals(2, (int) arb.dequeue());
        arb.enqueue(5);
        arb.enqueue(6);
        assertTrue(arb.isFull());
        assertEquals(3, (int) arb.dequeue());
        assertEquals(4, (int) arb.dequeue());
        assertEquals(5, (int) arb.dequeue());
        assertFalse(arb.isEmpty());
        assertFalse(arb.isFull());
        assertEquals(6, (int) arb.dequeue());
        assertTrue(arb.isEmpty());
    }

    @Test
    public void testIterator() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<Integer>(4);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.dequeue();
        arb.dequeue();
        arb.enqueue(5);
        arb.enqueue(6);
        int[] expected = {3, 4, 5, 6};
        int index = 0;
        for (int item: arb) {
            assertEquals(item, expected[index]);
            index++;
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
