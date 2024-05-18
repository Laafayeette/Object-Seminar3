package se.kth.iv1350.sem3.model;

public interface PaymentStrategy {

    double calculateCustomerchange(double amount, double currentTotalPrice);
}
