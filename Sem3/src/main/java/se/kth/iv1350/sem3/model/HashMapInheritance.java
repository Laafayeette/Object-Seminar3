package se.kth.iv1350.sem3.model;

import se.kth.iv1350.sem3.integration.dto.ItemDTO;
import se.kth.iv1350.sem3.integration.dto.SaleDTO;
import se.kth.iv1350.sem3.util.HashMapLogger;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class HashMapInheritance<K, V> extends HashMap<K, V> {


    private final HashMapLogger mapLogger;

    public HashMapInheritance( HashMapLogger mapLogger) {
        this.mapLogger = mapLogger;
    }

    @Override
    public V put(K key, V value) {
        mapLogger.log(key, value);
        System.out.println("We are here");
        return super.put(key, value);
    }

//    @Override
//    public V remove(Object key) {
//        mapLogger.log(key, null);
//        return super.remove(key);
//    }



}
