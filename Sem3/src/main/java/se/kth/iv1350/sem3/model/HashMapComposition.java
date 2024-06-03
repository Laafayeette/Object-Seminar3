package se.kth.iv1350.sem3.model;

import java.util.HashMap;

public class HashMapComposition<K, V> {

    private HashMap<K, V> hashMap =  new HashMap<>();



    public void put(K key, V value) {

        hashMap.put(key, value);
    }
}
