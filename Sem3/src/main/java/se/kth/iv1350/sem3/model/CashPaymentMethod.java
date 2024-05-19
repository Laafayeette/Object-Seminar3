package se.kth.iv1350.sem3.model;

class CashPaymentMethod implements PaymentMethodStrategy {
    CashPaymentMethod() {}

    @Override
    public double calculateCustomerchange(double amount, double currentTotalPrice) {
        return amount - currentTotalPrice;
    }




}
