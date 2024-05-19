package se.kth.iv1350.sem3.view;

import se.kth.iv1350.sem3.model.Sale;
import se.kth.iv1350.sem3.model.SaleObserver;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * This class represents and handles the <code>totalRevenue</code> to be logged to a file.
 */
public class TotalRevenueFileOutput implements SaleObserver {

    private PrintWriter logStream;
    private LocalDateTime localDateTime = LocalDateTime.now();

    private double totalRevenue = 0;

    /**
     * Constructor for the {@link TotalRevenueFileOutput} class.
     * Instantiates an object of type {@link PrintWriter}.
     * Tells the <code>logStream</code> to write to the "TotalRevenue.txt" file.
     */
    public TotalRevenueFileOutput() {
        try {
            logStream = new PrintWriter(new FileWriter("TotalRevenue.txt", true), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * An implementation of the {@link SaleObserver} class.
     * Updates the <code>totalRevenue</code> and writes it to a file.
     * @param revenue The <code>revenue</code> generated from a {@link Sale}.
     */
    @Override
    public void updateTotalRevenue(double revenue) {
        totalRevenue = totalRevenue + revenue;
        log(totalRevenue);
    }

    private void log(double totalRevenue) {
        logStream.println(String.format("%.2f", totalRevenue));
        StringBuilder str = new StringBuilder();
        str.append("The total revenue is: ").append(totalRevenue).append(" SEK").append(" calculated at: ").append(localDateTime).append("\n");
        logStream.println(str);
    }
}
