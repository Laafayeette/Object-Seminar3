package se.kth.iv1350.sem3.model;

public class CreditCardPaymentMethod implements PaymentMethodStrategy {

    @Override
    public double calculateCustomerchange(double amount, double currentTotalPrice) {
        return 0;
    }
}
