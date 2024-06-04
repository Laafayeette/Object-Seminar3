package se.kth.iv1350.sem3.model;

import se.kth.iv1350.sem3.util.HashMapLogger;

import java.util.HashMap;

public class HashMapInheritance<K, V> extends HashMap<K, V> {

    private final HashMapLogger mapLogger;

    public HashMapInheritance( HashMapLogger mapLogger) {
        this.mapLogger = mapLogger;
    }

    @Override
    public V put(K key, V value) {
        mapLogger.addLog(key, value);
        System.out.println("We are here");
        return super.put(key, value);
    }

    @Override
    public V remove(Object key) throws IllegalArgumentException {
        System.out.println("Abou...");
        if(super.containsKey(key)) {
            System.out.println("About to throw exception");
            throw new IllegalArgumentException("The specified item " + "\"" + key + "\""+ " does not exist in the map");
        }
        mapLogger.removeLog(key);
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
