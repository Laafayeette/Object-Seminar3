package se.kth.iv1350.sem3.view;

import se.kth.iv1350.sem3.model.Sale;
import se.kth.iv1350.sem3.model.SaleObserver;

/**
 * This class represents and handles the <code>totalRevenue</code> to be printed on the user interface.
 */
public class TotalRevenueView extends TotalRevenueTemplate {


    @Override
    protected void doPrintLogTotalRevenue(double totalRevenue) throws Exception {
        System.out.println("Total Revenue: " + totalRevenue + " SEK");  //Specifik
    }


    @Override
    protected void handleErrors(Exception e) {

    }
}
