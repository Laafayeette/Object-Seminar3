package se.kth.iv1350.sem3.util;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * This class represents the logger that logs the unique items that have been sold during a finalized sale.
 * It has support for adding and removing items to a given hash map.
 * The hash map only takes into account the items that have been sold, and not the amount of each item, i.e quantity.
 * After a {@link se.kth.iv1350.sem3.model.Sale Sale} has been finalized, the items are added to the hash map.
 * The same items can also be removed from the hash map, however this functionality ought to be taken with great caution.
 * Use cases may be wherein the program for some reason has added faulty data to the hashmap and has to be removed.
 */
public class HashMapLogger {


    private PrintWriter logStream;
    private LocalDateTime localDateTime = LocalDateTime.now();

    /**
     * Constructor for the {@link se.kth.iv1350.sem3.util.HashMapLogger} class.
     * Instantiates an object of type {@link PrintWriter}.
     * Tells the <code>logStream</code> to write to the specified text file.
     * @param fileName The specified name of the text file.
     */
    public HashMapLogger(String fileName) {
        try {
            logStream = new PrintWriter(new FileWriter(fileName, true), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Logs the specified key-value pair with an appended string for context to the specified {@link PrintWriter logStream} i.e., the text file.
     * @param key The name of the sold item.
     * @param value The price of the sold item
     * @param <K> The type of the key, representing the name of the sold item.
     * @param <V> The type of the value, representing the price of the sold item.
     */
    public <K, V> void logAddedItemToMap(K key, V value) {
        logStream.println("Item: " + key + ", Price: " + value + ", Logged at: " + localDateTime + "\n");
    }

    /**
     * Logs the removal of a specified item in the sale with the given key from the hash map, to the specified text file.
     * @param key The name of the (sold) item.
     * @param <K> The type of the key, representing the name of the sold item.
     */
    public <K> void logRemovedItemToMap(K key) {
        logStream.println("Item: " + key + " removed from map at: "+ " Logged at: " + localDateTime + "\n");
    }
}

