package test;

import lrucache.*;
import org.junit.Test;
import org.junit.Assert.*;

import static org.junit.Assert.*;

public class TestLRUCache {
    @Test
    public void testEmptyCache() {
        LRUCache<Integer, String> lru = new LRUCacheImpl<>(20);
        assertEquals(lru.maxSize, 20);
        assertEquals(lru.size(), 0);
        assertTrue(lru.isEmpty());
        assertFalse(lru.isFull());
    }

    @Test
    public void testPutAndGet() {
        LRUCache<Integer, Integer> lru = new LRUCacheImpl<>(20);
        Integer k = 1, v = 20;
        lru.put(k, v);
        assertEquals(lru.size(), 1);
        assertTrue(lru.contains(k));
        assertEquals(lru.get(k), v);
    }

    @Test
    public void testDelete() {
        LRUCache<Integer, Integer> lru = new LRUCacheImpl<>(20);
        Integer k = 1, v = 20;
        lru.put(k, v);
        assertEquals(lru.delete(k), v);
        assertFalse(lru.contains(k));
        assertEquals(lru.size(), 0);
    }

    @Test
    public void testFull() {
        LRUCache<Integer, Integer> lru = new LRUCacheImpl<>(3);
        Integer k1 = 1, k2 = 2, k3 = 3, k4 = 4,
                v1 = 5, v2 = 6, v3 = 7, v4 = 8;
        lru.put(k1, v1);
        lru.put(k2, v2);
        lru.put(k3, v3);
        lru.put(k4, v4);
        assertFalse(lru.contains(k1));
        assertEquals(lru.size(), 3);
    }
}
