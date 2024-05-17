package se.kth.iv1350.sem3.util;

import java.io.*;

public class LogHandler {

    private PrintWriter logStream;

    public LogHandler() {
        try {
            logStream = new PrintWriter(new FileWriter("log.txt", true), true);
            logStream.println("Hej");
        } catch (IOException e) {
            System.out.println("CANT log");
            e.printStackTrace();
        }
    }

    public void log(String message) {
        logStream.println(message);
    }
}
