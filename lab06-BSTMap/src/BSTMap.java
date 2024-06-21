import edu.princeton.cs.algs4.BST;

import java.util.Iterator;
import java.util.Set;
import java.util.Stack;
import java.util.ArrayList;

public class BSTMap<K extends Comparable<K>, V extends Comparable<V>> implements Map61B<K, V> {

    private class BSTNode{
        public K key;
        public V value;
        public BSTNode left;
        public BSTNode right;

        public BSTNode(K k, V v) {
            key = k;
            value = v;
            left = right = null;
        }
    }

    private BSTNode root;

    public BSTMap() {
        root = null;
    }

    @Override
    public void put(K key, V value) {
        BSTNode newNode = new BSTNode(key, value);
        root = put(root, newNode);
    }

    /**
     * Helper function to implement "put" recursively.
     * */
    private BSTNode put(BSTNode currNode, BSTNode newNode) {
        if (currNode == null) {
            return new BSTNode(newNode.key, newNode.value);
        }
        if (currNode.key.compareTo(newNode.key) > 0) {
            currNode.left = put(currNode.left, newNode);
        } else if (currNode.key.compareTo(newNode.key) < 0) {
            currNode.right = put(currNode.right, newNode);
        } else {
            currNode.value = newNode.value;
        }
        return currNode;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    /**
     * Helper function to implement "get" recursively.
     * */
    private V get(BSTNode currNode, K key) {
        if (currNode == null) {
            return null;
        }
        if (currNode.key.compareTo(key) > 0) {
            return get(currNode.left, key);
        } else if (currNode.key.compareTo(key) < 0) {
            return get(currNode.right, key);
        } else {
            return currNode.value;
        }
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(root, key);
    }

    /**
     * Helper function to implement "containsKey" recursively.
     * */
    private boolean containsKey(BSTNode currNode, K key) {
        if (currNode == null) {
            return false;
        }
        if (currNode.key.compareTo(key) == 0) {
            return true;
        } else {
            return containsKey(currNode.left, key) | containsKey(currNode.right, key);
        }
    }

    @Override
    public int size() {
        return size(root);
    }

     /**
     * Helper function to implement "size" recursively.
     * */
    private int size(BSTNode currNode) {
        if (currNode == null) {
            return 0;
        }
        return 1 + size(currNode.left) + size(currNode.right);
    }

    @Override
    public void clear() {
        clear(root);
        root = null;
    }

     /**
     * Helper function to implement "clear" recursively.
     * */
    private void clear(BSTNode currNode) {
        if (currNode != null) {
            clear(currNode.left);
            clear(currNode.right);
            currNode.left = currNode.right = null;
        }
    }

    @Override
    public Set<K> keySet() {
        return Set.of();
    }

    @Override
    public V remove(K key) {
        return null;
    }

    private class BSTMapIterator implements Iterator<K> {

        Stack<BSTNode> stack;
        ArrayList<K> arr;
        int index;
        int num;

        public BSTMapIterator() {
            stack = new Stack<BSTNode>();
            arr = new ArrayList<K>();
            initialize();
            index = 0;
            num = arr.size();
        }

        private void initialize() {
            BSTNode pNode = root;
            BSTNode node = null;
            while (pNode != null || !stack.isEmpty()) {
                if (pNode != null) {
                    stack.push(pNode);
                    pNode = pNode.left;
                } else {
                    node = stack.pop();
                    arr.add(node.key);
                    pNode = node.right;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return index < num;
        }

        @Override
        public K next() {
            K rt = arr.get(index);
            index++;
            return rt;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    /**
     * Print all the key-value mapping in order of increasing Key.
     * This function is for self-debugging only.
     * */
    public void printInOrder() {
        for (K k: this) {
            System.out.print(k + ": " + this.get(k) + ",  ");
        }
        System.out.println();
    }
}
