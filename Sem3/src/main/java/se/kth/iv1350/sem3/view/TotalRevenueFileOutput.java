package se.kth.iv1350.sem3.view;

import jdk.jshell.spi.ExecutionControlProvider;
import se.kth.iv1350.sem3.model.SaleObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class TotalRevenueFileOutput implements SaleObserver {

    private PrintWriter logStream;
    private LocalDateTime localDateTime = LocalDateTime.now();

    private double totalRevenue = 0;

    public TotalRevenueFileOutput() {
        try {
            logStream = new PrintWriter(new FileWriter("TotalRevenue.txt", true), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTotalRevenue(double revenue) {
        totalRevenue = totalRevenue + revenue;
        log(totalRevenue);
    }

    public void log(double totalRevenue) {
        logStream.println(String.format("%.2f", totalRevenue));
        StringBuilder str = new StringBuilder();
        str.append("The total revenue is: ").append(totalRevenue).append(" SEK").append(" calculated at: ").append(localDateTime).append("\n");
        logStream.println(str);
    }
}
