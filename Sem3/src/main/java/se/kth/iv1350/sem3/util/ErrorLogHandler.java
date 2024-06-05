package se.kth.iv1350.sem3.util;

import java.io.*;
import java.time.LocalDateTime;

/**
 * This class represents the logger class that logs errors pertaining to the sale operation.
 */
public class ErrorLogHandler {

    private PrintWriter logStream;
    private LocalDateTime localDateTime = LocalDateTime.now();

    /**
     * Constructor for the {@link se.kth.iv1350.sem3.util.ErrorLogHandler} class.
     * Instantiates an object of type {@link PrintWriter}.
     * Tells the <code>logStream</code> to write to the specified text file.
     * @param fileName The specified name of the text file.
     */
    public ErrorLogHandler(String fileName) {
        try {
            logStream = new PrintWriter(new FileWriter(fileName, true), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Logs the specified exception with an appended string defined in this method.
     * @param e The specified exception that is thrown and logged to the specified text file.
     */
    public void log(Exception e) {
        logStream.println("An exception was thrown at: " + localDateTime + " with the following message: \n" + e.getMessage() + "\n");
        e.printStackTrace(logStream);
        logStream.println("\n");
    }

    public void logIndexErrorForRemove(Exception e) {
        logStream.println(("An index out of bounds error was thrown at: " + localDateTime + "\n The error was thrown as you tried to remove an item in the hash map that was never scanned during sale\n" + "The error message is: " + e.getMessage()) + "\n");
    }
}
