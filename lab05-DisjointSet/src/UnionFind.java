public class UnionFind {

    private int[] arr;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = -1;
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        if (parent(v) < 0) {
            return -parent(v);
        }
        return sizeOf(parent(v));
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        return arr[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        if (v >= arr.length || v < 0) {
            throw new IllegalArgumentException();
        }
        return findHelper(v);
    }

    private int findHelper(int v) {
        if (arr[v] < 0) {
            return v;
        }
        arr[v] = findHelper(arr[v]);
        return arr[v];
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        int newRoot = -1;
        int v1Size = sizeOf(v1);
        int v2Size = sizeOf(v2);
        if (connected(v1, v2)) {
            return;
        } else if (v1Size <= v2Size) {
            newRoot = find(v2);
            arr[find(v1)] = newRoot;
            arr[newRoot] = -(v1Size + v2Size);
        } else {
            newRoot = find(v1);
            arr[find(v2)] = newRoot;
            arr[newRoot] = -(v1Size + v2Size);
        }
    }
}
