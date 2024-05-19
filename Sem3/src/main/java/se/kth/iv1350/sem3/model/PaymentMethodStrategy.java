package se.kth.iv1350.sem3.model;

public interface PaymentMethodStrategy {

    double calculateCustomerchange(double amount, double currentTotalPrice);
}
