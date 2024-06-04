package se.kth.iv1350.sem3.model;

import se.kth.iv1350.sem3.util.HashMapLogger;

import java.util.HashMap;

/**
 * This class extends the standard {@link HashMap} implementation in Java.
 * It overrides methods in the library with added functionality, logging the operations taking place.
 * @param <K>   The type of keys maintained by this map.
 * @param <V>   The type of the mapped values.
 */
public class HashMapInheritance<K, V> extends HashMap<K, V> {

    private final HashMapLogger mapLogger;

    /**
     * Constructor for the {@link HashMapInheritance} class. Takes in a {@link HashMapLogger} object and creates a new instance of {@link HashMapInheritance} with the specified logger
     * that is used to log the operations (the overriden methods) performed within this {@link HashMapInheritance} class.
     * @param mapLogger The given {@link HashMapLogger} object, with information pertaining on where to log these operations. The caller upon this constructor decides on the location of the text file.
     */
    public HashMapInheritance(HashMapLogger mapLogger) {
        this.mapLogger = mapLogger;
    }

    /**
     *  Adds a given key-value pair to the map, associating the key with its given value.
     *  Logs the item added to the map to the specified text file.
     * @param key Key with which the specified value is to be associated.
     * @param value Value to be associated with the specified key.
     * @return The previous value associated with the key.
     */
    @Override
    public V put(K key, V value) {
        mapLogger.logAddedItemToMap(key, value);
        System.out.println("We are here");
        return super.put(key, value);
    }

    /**
     *  Removes a given key-value pair from the map.
     *  Logs the removal of the item to the specified text file.
     * @param key Key whose mapping is to be removed from the map
     * @return The previous value associated with the key, or null if no mapping exists for the key.
     * @throws IllegalArgumentException If the given key does not exist in the map.
     */
    @Override
    public V remove(Object key) throws IllegalArgumentException {
        System.out.println("Abou...");
        if(!super.containsKey(key)) {
            System.out.println("About to throw exception");
            throw new IllegalArgumentException("Could not remove item from map. The specified item " + "\"" + key + "\""+ " does not exist in the map");
        }
        mapLogger.logRemovedItemToMap(key);
        return super.remove(key);
    }

    //Remove later
    @Override
    public boolean containsValue(Object value) {
        try {
            return super.containsValue(value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}
