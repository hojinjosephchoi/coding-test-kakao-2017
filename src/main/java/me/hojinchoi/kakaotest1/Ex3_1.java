package me.hojinchoi.kakaotest1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache<K, V> {
    private static final float hashTableLoadFactor = 0.75f;
    private LinkedHashMap<K, V> map;
    private int cacheSize;

    public LRUCache(int cacheSize) {
        this.cacheSize = cacheSize;
        int hashTableCapacity = (int) Math.ceil(cacheSize / hashTableLoadFactor) + 1;
        map = new LinkedHashMap<K, V>(hashTableCapacity, hashTableLoadFactor, true) {
            // (an anonymous inner class)
            private static final long serialVersionUID = 1;

            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > LRUCache.this.cacheSize;
            }
        };
    }

    /**
     * The retrieved entry becomes the MRU (most recently used) entry.
     */
    public synchronized V get(K key) {
        return map.get(key);
    }

    /**
     * If the cache is full, the LRU (least recently used) entry is dropped.
     */
    public synchronized void put(K key, V value) {
        map.put(key, value);
    }

    public synchronized void clear() {
        map.clear();
    }

    public synchronized int usedEntries() {
        return map.size();
    }

    public synchronized Collection<Map.Entry<K, V>> getAll() {
        return new ArrayList<Map.Entry<K, V>>(map.entrySet());
    }
}

public class Ex3_1 {

    public static void main(String[] args) {

        int cacheSize = 0;
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        LRUCache<String, String> lruCache = new LRUCache<String, String>(cacheSize);

        int operatingTime = 0;

        for (int inx = 0; inx < cities.length; inx++) {
            if (lruCache.get(cities[inx]) != null) {
                operatingTime += 1;
            } else {
                lruCache.put(cities[inx].toLowerCase(), cities[inx].toLowerCase());
                operatingTime += 5;
            }

        }

        System.out.println(operatingTime);
    }

}
