package se.kth.iv1350.sem3.model;

/**
 * An interface representing different ways of handling payment methods.
 */
public interface PaymentMethodStrategy {

    double calculateCustomerChange(double amount, double currentTotalPrice);
}
