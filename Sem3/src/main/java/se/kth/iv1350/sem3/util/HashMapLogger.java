package se.kth.iv1350.sem3.util;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Map;

public class HashMapLogger {

    /**
     * This class represents and handles the <code>totalRevenue</code> to be logged to a file.
     */

    private PrintWriter logStream;
    private LocalDateTime localDateTime = LocalDateTime.now();

    /**
     * Constructor for the {@link se.kth.iv1350.sem3.util.HashMapLogger} class.
     * Instantiates an object of type {@link PrintWriter}.
     * Tells the <code>logStream</code> to write to the "HashMapLog.txt" file.
     */
    public HashMapLogger(String fileName) {
        try {
            logStream = new PrintWriter(new FileWriter(fileName, true), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Logs the specified key-value pair with an appended string for context to the specified logStream i.e., the text file.
     * @param key The name of the sold item.
     * @param value The price of the sold item
     * @param <K> The type of the key, representing the name of the sold item.
     * @param <V> The type of the value, representing the price of the sold item.
     */
    public <K, V> void log(K key, V value) {
        logStream.println("Item: " + key + ", Price: " + value + ", Logged at: " + localDateTime + "\n");
    }
}

