package se.kth.iv1350.sem3.view;

import se.kth.iv1350.sem3.model.Sale;
import se.kth.iv1350.sem3.model.SaleObserver;

/**
 * This class represents and handles the <code>totalRevenue</code> to be printed on the user interface.
 */
public class TotalRevenueView implements SaleObserver {

    private double totalRevenue = 0; //Generell

    /**
     * An implementation of the {@link SaleObserver} class.
     * Updates the <code>totalRevenue</code> and prints it to the user interface.
     * @param revenue The <code>revenue</code> generated from a {@link Sale}.
     */
    @Override
    public void updateTotalRevenue(double revenue) {
        totalRevenue = totalRevenue + revenue;
        System.out.println("Total Revenue: " + totalRevenue + " SEK");
    }
}
