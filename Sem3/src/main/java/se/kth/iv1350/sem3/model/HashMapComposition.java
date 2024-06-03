package se.kth.iv1350.sem3.model;

import se.kth.iv1350.sem3.util.HashMapLogger;

import java.util.HashMap;

public class HashMapComposition<K, V> {

    private HashMap<K, V> hashMap =  new HashMap<>();

    private final HashMapLogger mapLogger;

    public HashMapComposition(HashMapLogger mapLogger) {
        this.mapLogger = mapLogger;
    }

    public void put(K key, V value) {
        mapLogger.addLog(key, value);
        hashMap.put(key, value);
    }

    public V get(K key) {
        return hashMap.get(key);
    }

    public V remove(K key) {
        return hashMap.remove(key);
    }


}
