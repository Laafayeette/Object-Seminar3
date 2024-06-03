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
            logStream = new PrintWriter(new FileWriter("HashMapLog.txt"), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void logHashMap(K Key, V value) {
//
//        logHashMap(key, value);
//    }

//    private void log(double totalRevenue) {
//        logStream.println("The total revenue is: " + String.format("%.2f",totalRevenue) + " SEK" + " calculated at: " + localDateTime + "\n");
//    }

    public <K, V> void log(Map<String, Double> itemInfo) {
        logStream.println("This is the item info: " + itemInfo);
        //logStream.println("The hashmap key is: " + key + " and value is: " + value + " logged at: " + localDateTime + "\n");
    }
}

