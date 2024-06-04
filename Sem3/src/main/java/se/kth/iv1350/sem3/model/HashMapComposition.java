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
        mapLogger.logAddedItemToMap(key, value);
        hashMap.put(key, value);
    }

    public void remove(K key) {
        System.out.println("Abou...");
        if(!hashMap.containsKey(key)) {
            System.out.println("About to throw exception");
            throw new IllegalArgumentException("Could not remove item from map. The specified item " + "\"" + key + "\""+ " does not exist in the map");
        }
        mapLogger.logRemovedItemToMap(key);
        hashMap.remove(key);
    }
}
