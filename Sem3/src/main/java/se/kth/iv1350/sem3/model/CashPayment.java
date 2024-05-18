package se.kth.iv1350.sem3.model;

public class CashPayment implements PaymentStrategy{
    @Override
    public double calculateCustomerchange(double amount, double currentTotalPrice) {
        return amount - currentTotalPrice;
    }




}
