package se.kth.iv1350.sem3.model;

class CreditCardPaymentMethod implements PaymentMethodStrategy {
    CreditCardPaymentMethod() {}

    @Override
    public double calculateCustomerchange(double amount, double currentTotalPrice) {
        return 0;
    }
}
