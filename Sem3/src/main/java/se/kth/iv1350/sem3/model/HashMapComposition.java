package se.kth.iv1350.sem3.model;

import se.kth.iv1350.sem3.util.HashMapLogger;

import java.util.HashMap;

/**
 * This class makes use of composition to add functionality to the {@link HashMap} implementation in Java.
 * It provides additional logging functionality for the put, remove and size {@link HashMap] function.}
 * @param <K>   The type of keys maintained by this map.
 * @param <V>   The type of the mapped values.
 */
public class HashMapComposition<K, V> {

    private HashMap<K, V> hashMap = new HashMap<>();

    private final HashMapLogger mapLogger;

    /**
     * Constructor for the {@link HashMapComposition} class. Takes in a {@link HashMapLogger} object and creates a new instance of {@link HashMapComposition} with the specified logger
     * that is used to log the operations (the overriden methods) performed within this {@link HashMapComposition} class.
     * @param mapLogger The given {@link HashMapLogger} object, with information pertaining on where to log these operations. The caller upon this constructor decides on the location of the text file.
     */
    public HashMapComposition(HashMapLogger mapLogger) {
        this.mapLogger = mapLogger;
    }

    /**
     *  Adds a given key-value pair to the map, associating the key with its given value.
     *  Logs the item added to the map to the specified text file.
     * @param key Key with which the specified value is to be associated.
     * @param value Value to be associated with the specified key.
     * @return The previous value associated with the key.
     */
    public V put(K key, V value) {
        mapLogger.logAddedItemToMap(key, value);
        return hashMap.put(key, value);
    }

    /**
     *  Removes a given key-value pair from the map.
     *  Logs the removal of the item to the specified text file.
     * @param key Key whose mapping is to be removed from the map
     * @return The previous value associated with the key, or null if no mapping exists for the key.
     * @throws IllegalArgumentException If the given key does not exist in the map.
     */
    public V remove(K key) throws IllegalArgumentException {
        if(!hashMap.containsKey(key)) {
            throw new IllegalArgumentException("Could not remove item from map. The specified item " + "\"" + key + "\""+ " does not exist in the map");
        }
        mapLogger.logRemovedItemToMap(key);
        return hashMap.remove(key);
    }

    /**
     * Retrieves the size of the map, i.e the number of key-value mappings in the map,
     * and logs the size to the specified text file.
     * @return The size of the map, in terms of number of mappings.
     */
    public int size() {
        mapLogger.logSizeOfMap(hashMap.size());
        return hashMap.size();
    }

}
