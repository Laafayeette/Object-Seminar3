package se.kth.iv1350.sem3.model;

public class Payment {

    private final double amount;
    //private final String paymentMethod;
    private final double currentTotalPrice;
    private final PaymentMethodStrategy paymentMethodStrategy;

    /**
     * Constructor for the @Link(Payment) class.
     * Initializes the arguments and calculates the change to give back to the customer.
     * @param amount The amount given from the customer.
     * //@param paymentMethod The given payment method by which payment should take place.
     * @param currentTotalPrice The total price to be paid for the whole sale.
     */
    public Payment(double amount, double currentTotalPrice, PaymentMethodStrategy paymentMethodStrategy) {
        this.amount = amount;
        //this.paymentMethod = paymentMethod;
        this.currentTotalPrice = currentTotalPrice;
        //getCustomerChange();
        this.paymentMethodStrategy = paymentMethodStrategy;
    }

//    private double calculateCustomerChange(double amount, double currentTotalPrice) {
//        double customerChange = amount - currentTotalPrice;
//        return customerChange;
//    }


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

    public double getCustomerChange() {
        return paymentMethodStrategy.calculateCustomerchange(amount, currentTotalPrice);
    }

    public double getAmount() {
        return amount;
    }

    public double getCurrentTotalPrice() {
        return currentTotalPrice;
    }

}
