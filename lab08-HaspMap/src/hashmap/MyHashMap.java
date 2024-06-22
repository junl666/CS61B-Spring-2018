package hashmap;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int capacity;
    private double loadFactor;
    private int size;

    /** Constructors */
    public MyHashMap() {
        capacity = 16;
        loadFactor = 0.75;
        size = 0;
        buckets = new Collection[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = createBucket();
        }
    }

    public MyHashMap(int initialCapacity) {
        capacity = initialCapacity;
        loadFactor = 0.75;
        size = 0;
        buckets = new Collection[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        capacity = initialCapacity;
        this.loadFactor = loadFactor;
        size = 0;
        buckets = new Collection[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    @Override
    public void put(K key, V value) {
        if (size + 1 > loadFactor * capacity) {
            resize(capacity * 2);
        }
        int category = Math.floorMod(key.hashCode(), capacity);

        for (Node currNode: buckets[category]) {
            if (currNode.key.equals(key)) {
                currNode.value = value;
                return;
            }
        }
        buckets[category].add(new Node(key, value));
        size++;
    }

    /**
     * Helper function to resize the hash map to the new capacity,
     * and also remove all the content to the new buckets.
     * */
    private void resize(int newCapacity) {
        Collection<Node>[] newBuckets = new Collection[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            newBuckets[i] = createBucket();
        }
        for (int category = 0; category < capacity; category++) {
            for (Node currNode: buckets[category]) {
                int newCategory = Math.floorMod(currNode.key.hashCode(), newCapacity);
                newBuckets[newCategory].add(currNode);
            }
        }
        capacity = newCapacity;
        buckets = newBuckets;
    }

    @Override
    public V get(K key) {
        if (buckets == null) return null;
        int category = Math.floorMod(key.hashCode(), capacity);
        for (Node currNode: buckets[category]) {
            if (currNode.key.equals(key)) {
                return currNode.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        if (buckets == null) return false;
        int category = Math.floorMod(key.hashCode(), capacity);
        for (Node currNode: buckets[category]) {
            if (currNode.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (int category = 0; category < capacity; category++) {
            buckets[category] = null;
        }
        buckets = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        V rtVal;
        int category = Math.floorMod(key.hashCode(), capacity);
        for (Node currNode: buckets[category]) {
            if (currNode.key.equals(key)) {
                rtVal = currNode.value;
                buckets[category].remove(currNode);
                size--;
                return rtVal;
            }
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return new MyHashMapIterator();
    }

    private class MyHashMapIterator implements Iterator<K> {


        public MyHashMapIterator() {

        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public K next() {
            return null;
        }
    }

}
