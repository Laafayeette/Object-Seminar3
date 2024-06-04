package se.kth.iv1350.sem3.util;

import java.io.*;
import java.time.LocalDateTime;

public class ErrorLogHandler {

    private PrintWriter logStream;
    private LocalDateTime localDateTime = LocalDateTime.now();

    public ErrorLogHandler(String fileName) {
        try {
            logStream = new PrintWriter(new FileWriter(fileName, true), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(Exception e) {
        logStream.println("An exception was thrown at: " + localDateTime + " with the following message: \n" + e.getMessage());
        System.out.println("\n\n");
        e.printStackTrace(logStream);
    }
}
