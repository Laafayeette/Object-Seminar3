package se.kth.iv1350.sem3.model;

/**
 * Represents the payment method corresponding to Cash.
 */
public class CashPaymentMethod implements PaymentMethodStrategy {

    /**
     * An implementation of the {@link PaymentMethodStrategy} interface.
     * Implements the <code>calculateCustomerChange</code> interface.
     * Calculates the amount of change to be given back to the customer.
     * @param amount The amount given from the customer to pay.
     * @param currentTotalPrice The total price generated from the sale.
     * @return The amount of change the customer is given back.
     */
    @Override
    public double calculateCustomerChange(double amount, double currentTotalPrice) {
        return amount - currentTotalPrice;
    }




}
