package se.kth.iv1350.sem3.model;

public class Payment {

    private final double amount;
    private final double currentTotalPrice;
    private final PaymentMethodStrategy paymentMethodStrategy;

    /**
     * Constructor for the {@link Payment} class.
     * Initializes the arguments.
     * @param amount The amount given from the customer.
     * @param currentTotalPrice The total price to be paid for the whole sale.
     * @param paymentMethodStrategy The encapsulated method of payment.
     */
    public Payment(double amount, double currentTotalPrice, PaymentMethodStrategy paymentMethodStrategy) {
        this.amount = amount;
        this.currentTotalPrice = currentTotalPrice;
        this.paymentMethodStrategy = paymentMethodStrategy;
    }

    public PaymentMethodStrategy getPaymentStrategy() {
        return paymentMethodStrategy;
    }

    /**
     * A getter for the Payment method specified by the {@link PaymentMethodStrategy}.
     * This method is used by {@link Receipt} class to enable dynamic printout.
     * The method takes the class name from the class implementing the {@link PaymentMethodStrategy} interface,
     * and trims the "Payment" section of the string.
     * @return The trimmed version of the classname, representing the payment method as a String.
     */
    public String getPaymentMethod() {
        if (paymentMethodStrategy == null) {
            return null;
        }
        String paymentMethodString =  paymentMethodStrategy.getClass().getSimpleName();
        return paymentMethodString.replace("PaymentMethod", "");
    }

    /**
     * Calculates the customer change to be given back to the customer.
     * Uses the dynamic <code>paymentMethodStrategy</code> to enable different implementations of calculation to be used.
     * @return The amount of change to be returned to the customer.
     */
    public double getCustomerChange() {
        return paymentMethodStrategy.calculateCustomerChange(amount, currentTotalPrice);
    }

    public double getAmount() {
        return amount;
    }

    public double getCurrentTotalPrice() {
        return currentTotalPrice;
    }

}
