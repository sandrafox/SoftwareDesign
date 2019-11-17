package lrucache;

public class LRUCacheImpl<K, V> extends LRUCache<K, V> {
    public LRUCacheImpl(int size) {
        super(size);
    }

    protected int doSize() {
        return table.size();
    }

    private void putFirst(Node node) {
        node.next = head;
        if (head != null) {
            head.prev = node;
        }
        head = node;
    }

    private void deleteLast() {
        tail = tail.prev;
    }

    protected void doPut(K key, V value) {
        if (isFull()) {
            table.remove(tail.key);
            deleteLast();
        }
        Node node = table.get(key);
        if (node == null) {
            node = new Node(key, value, null, null);
            table.put(key, node);
        } else {
            if (node.value != value) {
                table.remove(key);
                node.value = value;
            }
            deleteFromList(node);
        }
        putFirst(node);
        if (tail == null) tail = node;
    }

    private void deleteFromList(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        if (prev != null) {
            prev.next = next;
        }
        if (next != null) {
            next.prev = prev;
        }
    }

    protected V doGet(K key) {
        Node node = table.get(key);
        if (node != null) {
            deleteFromList(node);
            putFirst(node);
            return node.value;
        }
        return null;
    }

    protected V doDelete(K key) {
        Node node = table.get(key);
        if (node != null) {
            deleteFromList(node);
            table.remove(key);
            return node.value;
        }
        return null;
    }
}
