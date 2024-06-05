package se.kth.iv1350.sem3.view;

import se.kth.iv1350.sem3.model.Sale;
import se.kth.iv1350.sem3.model.SaleObserver;

/**
 * Är egentligen en observer, vi har nu tagit det som är gemensamt för dem olika implementationerna av
 * SaleObserver interfacet, och ska nu låta klasserna som TotalRevenueView vara subklasser till denna.
 * Subklasserna kommer nu längre inte implementera SaleObserver interface:t,
 * utan dem kommer extend:a denna klass som i sin tur implementerar SaleObserver interface:t.
 * Vi börjar med att lägga in den gemensamma koden i denna klass.
 */
public abstract class TotalRevenueTemplate implements SaleObserver {

    private double totalRevenue = 0;


    /**
     * An implementation of the {@link SaleObserver} class.
     * Updates the <code>totalRevenue</code> and writes it to a file.
     * @param revenue The <code>revenue</code> generated from a {@link Sale}.
     */
    @Override
    public void updateTotalRevenue(double revenue) {
        totalRevenue = totalRevenue + revenue;
        showTotalRevenue(totalRevenue);
    }

    private void showTotalRevenue(double totalRevenue) {
        try {
            doShowTotalRevenue(totalRevenue);
        } catch (Exception e) {
            handleErrors(e);
        }
    }


    protected abstract void doShowTotalRevenue(double totalRevenue) throws Exception;

    protected abstract void handleErrors(Exception e);
}
