package se.kth.iv1350.sem3.view;

import se.kth.iv1350.sem3.model.SaleObserver;

/**
 * Är egentligen en observer, vi har nu tagit det som är gemensamt för dem olika implementationerna av
 * SaleObserver interfacet, och ska nu låta klasserna som TotalRevenueView vara subklasser till denna.
 * Subklasserna kommer nu längre inte implementera SaleObserver interface:t,
 * utan dem kommer extend:a denna klass som i sin tur implementerar SaleObserver interface:t.
 *
 * Vi börjar med att lägga in den gemensamma koden i denna klass.
 */
public abstract class TotalRevenueTemplate implements SaleObserver {

    public void


    protected abstract void doShowTotalRevenue() throws Exception;

    protected abstract void handleErrors(Exception e);
}
