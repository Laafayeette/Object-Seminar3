package se.kth.iv1350.sem3.model;

public class Payment {

    private final double amount;
    private final String paymentMethod;
    private final double currentTotalPrice;

    /**
     * Constructor for the @Link(Payment) class.
     * Initializes the arguments and calculates the change to give back to the customer.
     * @param amount The amount given from the customer.
     * @param paymentMethod The given payment method by which payment should take place.
     * @param currentTotalPrice The total price to be paid for the whole sale.
     */
    public Payment(double amount, String paymentMethod, double currentTotalPrice) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.currentTotalPrice = currentTotalPrice;
        getCustomerChange();
    }

    private double calculateCustomerChange(double amount, double currentTotalPrice) {
        double customerChange = amount - currentTotalPrice;
        return customerChange;
    }

    public double getCustomerChange() {
        return calculateCustomerChange(this.amount, this.currentTotalPrice);
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public double getCurrentTotalPrice() {
        return currentTotalPrice;
    }

}
