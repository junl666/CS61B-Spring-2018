public class LinkedListDeque<T> {

    private static class TNode<T> {
        private T item;
        private TNode prev;
        private TNode next;
        private TNode(T item, TNode prev, TNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
    private int size;
    private TNode sentinel;

    public LinkedListDeque() {
        this.size = 0;
        this.sentinel = new TNode(null, null, null);
        this.sentinel.prev = this.sentinel;
        this.sentinel.next = this.sentinel;
    }

    public void addFirst(T item) {
        this.sentinel.next = new TNode(item, this.sentinel, this.sentinel.next);
        this.sentinel.next.next.prev = this.sentinel.next;
        this.size += 1;
    }

    public void addLast(T item) {
        this.sentinel.prev.next =  new TNode(item, this.sentinel.prev, this.sentinel);
        this.sentinel.prev = this.sentinel.prev.next;
        this.size += 1;
    }

    public boolean isEmpty() {
        return (this.size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        TNode curr = this.sentinel.next;
        while (curr != this.sentinel) {
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
    }

    public T removeFirst() {
        T rt_item = (T) this.sentinel.next.item;
        this.sentinel.next = this.sentinel.next.next;
        this.sentinel.next.prev = this.sentinel;
        this.size -= 1;
        return rt_item;
    }

    public T removeLast() {
        T rt_item = (T) this.sentinel.prev.item;
        this.sentinel.prev = this.sentinel.prev.prev;
        this.sentinel.prev.next = this.sentinel;
        this.size -= 1;
        return rt_item;
    }

    public T get(int index) {
        TNode curr = this.sentinel.next;
        while ((index > 0) && (curr != this.sentinel)) {
            curr = curr.next;
            index -= 1;
        }
        if (curr == this.sentinel) {
            return null;
        } else {
            return (T) curr.item;
        }
    }

    private T getRecursiveHelper(TNode node, int index) {
        if (index <= 0) {
            return (T) node.item;
        }
        if (node == this.sentinel) {
            return null;
        }
        return getRecursiveHelper(node.next, index - 1);
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(this.sentinel.next, index);
    }

}
