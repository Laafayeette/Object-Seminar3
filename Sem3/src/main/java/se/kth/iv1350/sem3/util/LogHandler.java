package se.kth.iv1350.sem3.util;

import java.io.*;
import java.time.LocalDateTime;

public class LogHandler {

    private PrintWriter logStream;
    private LocalDateTime localDateTime = LocalDateTime.now();

    public LogHandler() {
        try {
            logStream = new PrintWriter(new FileWriter("log123.txt", true), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(Exception e) {
        StringBuilder str = new StringBuilder();
        str.append("An exception was thrown at: " + localDateTime + " with the following message: \n" + e.getMessage());
        logStream.println(str);
        System.out.println("\n\n");
        e.printStackTrace(logStream);
    }
}
