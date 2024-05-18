package se.kth.iv1350.sem3.view;

import se.kth.iv1350.sem3.model.SaleObserver;

public class TotalRevenueView implements SaleObserver {

    private double totalRevenue = 0;

    @Override
    public void updateTotalRevenue(double revenue) {
        totalRevenue = totalRevenue + revenue;
        System.out.println("Total Revenue: " + totalRevenue + " SEK");
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }
}
