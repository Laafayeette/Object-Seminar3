package se.kth.iv1350.sem3.model;

/**
 * An implementation of the {@link PaymentMethodStrategy} interface.
 *
 */
public class CreditCardPaymentMethod implements PaymentMethodStrategy {

    /**
     * The credit card payment calculates the change to give back to the customer.
     * It is a logically an unsound operation to calculate a change total in the case of credit cards.
     * However this implementation is meant to illustrate that when a customer pays via card,
     * then there is nothing to be returned back to the customer.
     * And the <code>amount</code> that is given equals the <code>currentTotalPrice</code>, as in the case of card payments.
     * No error is thrown if the <code>amount</code>> in the does not equal the <code>currentTotalPrice</code>
     * @param amount The amount that the terminal withdraws from the customer's credit card, i.e the amount the customer pays.
     * @param currentTotalPrice The total price of the sale.
     * @return <code>0</code> since the terminal withdraws as much as the given total price.
     */
    @Override
    public double calculateCustomerchange(double amount, double currentTotalPrice) {
        return 0;
    }
}
