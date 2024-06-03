package se.kth.iv1350.sem3.view;

import se.kth.iv1350.sem3.model.Sale;
import se.kth.iv1350.sem3.model.SaleObserver;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * This class represents and handles the <code>totalRevenue</code> to be logged to a file.
 */
public class TotalRevenueFileOutput extends TotalRevenueTemplate {

    private PrintWriter logStream;  //Specifik
    private LocalDateTime localDateTime = LocalDateTime.now(); //Specifik

    private final ErrorMessageHandler errorMsgHandler = new ErrorMessageHandler();


    @Override
    protected void doPrintLogTotalRevenue(double totalRevenue) throws Exception {
        logStream.println("The total revenue is: " + String.format("%.2f",totalRevenue) + " SEK" + " calculated at: " + localDateTime + "\n");
    }

    @Override
    protected void handleErrors(Exception e) {
        errorMsgHandler.showMessage("Operation resulted in error.");
    }



    //*     KODEN NEDAN ÄR SPECIFIK FÖR LOGGING * //
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


}
