import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.Assert.fail;

public class UnionFindTest {

    private UnionFind uf;
//    /**
//     * Checks that the initial state of the disjoint sets are correct (this will pass with the skeleton
//     * code, but ensure it still passes after all parts are implemented).
//     */
//    @Test
//    public void initialStateTest() {
//        UnionFind uf = new UnionFind(4);
//        assertThat(uf.connected(0, 1)).isFalse();
//        assertThat(uf.connected(0, 2)).isFalse();
//        assertThat(uf.connected(0, 3)).isFalse();
//        assertThat(uf.connected(1, 2)).isFalse();
//        assertThat(uf.connected(1, 3)).isFalse();
//        assertThat(uf.connected(2, 3)).isFalse();
//    }
//
//    /**
//     * Checks that invalid inputs are handled correctly.
//     */
//    @Test
//    public void illegalFindTest() {
//        UnionFind uf = new UnionFind(4);
//        try {
//            uf.find(10);
//            fail("Cannot find an out of range vertex!");
//        } catch (IllegalArgumentException e) {
//            return;
//        }
//        try {
//            uf.union(1, 10);
//            fail("Cannot union with an out of range vertex!");
//        } catch (IllegalArgumentException e) {
//            return;
//        }
//    }
//
//    /**
//     * Checks that union is done correctly (including the tie-breaking scheme).
//     */
//    @Test
//    public void basicUnionTest() {
//        UnionFind uf = new UnionFind(10);
//        uf.union(0, 1);
//        assertThat(uf.find(0)).isEqualTo(1);
//        uf.union(2, 3);
//        assertThat(uf.find(2)).isEqualTo(3);
//        uf.union(0, 2);
//        assertThat(uf.find(1)).isEqualTo(3);
//
//        uf.union(4, 5);
//        uf.union(6, 7);
//        uf.union(8, 9);
//        uf.union(4, 8);
//        uf.union(4, 6);
//
//        assertThat(uf.find(5)).isEqualTo(9);
//        assertThat(uf.find(7)).isEqualTo(9);
//        assertThat(uf.find(8)).isEqualTo(9);
//
//        uf.union(9, 2);
//        assertThat(uf.find(3)).isEqualTo(9);
//    }
//
//    /**
//     * Unions the same item with itself. Calls on find and checks that the outputs are correct.
//     */
//    @Test
//    public void sameUnionTest() {
//        UnionFind uf = new UnionFind(4);
//        uf.union(1, 1);
//        for (int i = 0; i < 4; i += 1) {
//            assertThat(uf.find(i)).isEqualTo(i);
//        }
//    }

    /**
     * Write your own tests below here to verify for correctness. The given tests are not comprehensive.
     * Specifically, you may want to write a test for path compression and to check for the correctness
     * of all methods in your implementation.
     */
    @Before
    public void setUp() {
        // 初始化一个包含10个元素的 UnionFind 实例
        uf = new UnionFind(10);
    }

    @Test
    public void testInitialConditions() {
        // 所有节点都应该是自己的父节点，集合大小为1
        for (int i = 0; i < 10; i++) {
            assertThat(uf.parent(i)).isEqualTo(-1);
            assertThat(uf.sizeOf(i)).isEqualTo(1);
        }
    }

    @Test
    public void testUnion() {
        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(1, 3); // Now, 0, 1, 2, 3 should be connected

        assertThat(uf.connected(0, 1)).isTrue();
        assertThat(uf.connected(2, 3)).isTrue();
        assertThat(uf.connected(0, 3)).isTrue();
        assertThat(uf.connected(1, 2)).isTrue();
    }

    @Test
    public void testSizeOf() {
        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(1, 3);

        assertThat(uf.sizeOf(0)).isEqualTo(4);
        assertThat(uf.sizeOf(1)).isEqualTo(4);
        assertThat(uf.sizeOf(2)).isEqualTo(4);
        assertThat(uf.sizeOf(3)).isEqualTo(4);
    }

    @Test
    public void testFind() {
        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(1, 3);

        // Ensure path compression works
        int root0 = uf.find(0);
        int root1 = uf.find(1);
        int root2 = uf.find(2);
        int root3 = uf.find(3);

        assertThat(root0).isEqualTo(root1);
        assertThat(root2).isEqualTo(root3);
        assertThat(root0).isEqualTo(root2);
    }

    @Test
    public void testConnected() {
        uf.union(4, 5);
        uf.union(6, 7);
        uf.union(5, 7);

        assertThat(uf.connected(4, 7)).isTrue();
        assertThat(uf.connected(5, 6)).isTrue();
        assertThat(uf.connected(4, 6)).isTrue();
        assertThat(uf.connected(7, 5)).isTrue();

        assertThat(uf.connected(0, 7)).isFalse();
        assertThat(uf.connected(1, 4)).isFalse();
        assertThat(uf.connected(2, 5)).isFalse();
        assertThat(uf.connected(3, 6)).isFalse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindInvalid() {
        uf.find(10); // Assumes elements are from 0 to 9
    }

    @Test
    public void testUnionSameElement() {
        uf.union(8, 8);
        assertThat(uf.sizeOf(8)).isEqualTo(1); // Union with itself should not change anything
    }

    @Test
    public void testUnionAlreadyConnected() {
        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(0, 2); // Already connected, should not change the structure

        assertThat(uf.sizeOf(0)).isEqualTo(3);
        assertThat(uf.sizeOf(1)).isEqualTo(3);
        assertThat(uf.sizeOf(2)).isEqualTo(3);
    }

    @Test
    public void testMultipleUnions() {
        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(3, 4);
        uf.union(2, 3); // 0,1,2,3,4 should be connected

        assertThat(uf.sizeOf(0)).isEqualTo(5);
        assertThat(uf.connected(0, 4)).isTrue();
        assertThat(uf.connected(1, 3)).isTrue();
    }
}


