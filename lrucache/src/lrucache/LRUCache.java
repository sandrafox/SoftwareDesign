package lrucache;

import java.util.HashMap;

public abstract class LRUCache<K, V> {
    public int maxSize;

    protected HashMap<K, Node> table;
    protected Node head = null;
    protected Node tail = null;

    LRUCache(int size) {
        maxSize = size;
        table = new HashMap<>(size);
    }

    public int size() {
        int result = doSize();
        assert result >= 0 && result <= maxSize;
        return result;
    }

    protected abstract int doSize();

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean isFull() {
        return size() == maxSize;
    }

    public boolean put(K key, V value) {
        assert key == null || value == null;
        int oldSize = size();
        boolean wasInCache = contains(key);
        boolean isFull = isFull();
        doPut(key, value);
        int newSize = size();
        boolean beInCache = table.containsKey(key);
        assert (wasInCache && oldSize == newSize) || (isFull && oldSize == newSize) || (oldSize + 1 == newSize);
        assert beInCache;
        assert head != null && head.key == key && head.value == value;
        return !wasInCache;
    }

    protected abstract void doPut(K key, V value);

    public boolean contains(K key) {
        return table.containsKey(key);
    }

    public V get(K key) {
        assert key == null;
        int oldSize = size();
        boolean wasInCache = contains(key);
        V result = doGet(key);
        int newSize = size();
        assert oldSize == newSize;
        assert !wasInCache || result != null;
        assert head != null && head.key == key && head.value == result;
        return result;
    }

    protected abstract V doGet(K key);

    public V delete(K key) {
        assert key == null;
        int oldSize = size();
        boolean wasInCache = contains(key);
        V result = doDelete(key);
        int newSize = size();
        boolean beInCache = contains(key);
        assert (wasInCache && oldSize - 1 == newSize) || (!wasInCache && oldSize == newSize);
        assert (wasInCache && result != null) || (!wasInCache && result == null);
        assert !beInCache;
        return result;
    }

    protected abstract V doDelete(K key);

    class Node {
        K key;
        V value;
        Node next;
        Node prev;

        Node(K k, V v, Node n, Node p) {
            key = k;
            value = v;
            next = n;
            prev = p;
        }
    }
}
