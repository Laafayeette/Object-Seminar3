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
     * Constructor for the {@link se.kth.iv1350.sem3.view.TotalRevenueFileOutput} class.
     * Instantiates an object of type {@link PrintWriter}.
     * Tells the <code>logStream</code> to write to the "TotalRevenue.txt" file.
     */
    public HashMapLogger() {
        try {
            logStream = new PrintWriter(new FileWriter("HashMapLog123.txt", true), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <K, V> void log(K key, V value) {
        logStream.println(key + ": " + value);
        logStream.println("Item: " + key + ", Price: " + value + ", Logged at: " + localDateTime + "\n");
    }
}

