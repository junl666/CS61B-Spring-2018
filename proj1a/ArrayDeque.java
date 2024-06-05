public class ArrayDeque<T> {

    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;

    public ArrayDeque() {
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        items = (T[]) new Object[8];
    }

    /**
     * Resize the array to the given newSize,and copy
     * the items in the old array to the new array.
     * */
    private void resize(int newSize) {
        T[] newItems = (T[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newItems[i] = items[(nextFirst + 1 + i) % items.length];
        }
        nextLast = size;
        nextFirst = newSize - 1;
        items = newItems;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size += 1;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[(nextFirst + 1 + i) % items.length] + " ");
        }
    }


    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst = (nextFirst + 1) % items.length;
        T rtItem = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        if ((items.length >= 16) && (size * 4 < items.length)) {
            resize(items.length / 2);
        }
        return rtItem;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = (nextLast - 1 + items.length) % items.length;
        T rtItem = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        if ((items.length >= 16) && (size * 4 < items.length)) {
            resize(items.length / 2);
        }
        return rtItem;
    }

    public T get(int index) {
        return items[(nextFirst + 1 + index) % items.length];
    }

}
