package se.kth.iv1350.sem3.model;

public class Payment {

    private final double amount;
    //private final String paymentMethod;
    private final double currentTotalPrice;
    private final PaymentStrategy paymentStrategy;

    /**
     * Constructor for the @Link(Payment) class.
     * Initializes the arguments and calculates the change to give back to the customer.
     * @param amount The amount given from the customer.
     * //@param paymentMethod The given payment method by which payment should take place.
     * @param currentTotalPrice The total price to be paid for the whole sale.
     */
    public Payment(double amount, double currentTotalPrice, PaymentStrategy paymentStrategy) {
        this.amount = amount;
        //this.paymentMethod = paymentMethod;
        this.currentTotalPrice = currentTotalPrice;
        //getCustomerChange();
        this.paymentStrategy = paymentStrategy;
    }

//    private double calculateCustomerChange(double amount, double currentTotalPrice) {
//        double customerChange = amount - currentTotalPrice;
//        return customerChange;
//    }


    public PaymentStrategy getPaymentStrategy() {
        return paymentStrategy;
    }

    /**
     * A getter for the Payment method specified by the {@link PaymentStrategy}.
     * This method is used by {@link Receipt} class to enable dynamic printout.
     * The method takes the class name from the class implementing the {@link PaymentStrategy} interface,
     * and trims the "Payment" section of the string.
     * @return The trimmed version of the classname, representing the payment method as a String.
     */
    public String getPaymentMethod() {
        String paymentMethodString =  paymentStrategy.getClass().getSimpleName();
        String trim = "Payment";
        String trimmedStr = removeSuffix(paymentMethodString, trim);
        return trimmedStr;
    }

    private String removeSuffix(String str, String trim) {
        if (str != null && trim != null && str.endsWith(trim)) {
            return str.substring(0, str.length() - trim.length());
        }
        return str;
    }

    public double getCustomerChange() {
        return paymentStrategy.calculateCustomerchange(amount, currentTotalPrice);
    }

    public double getAmount() {
        return amount;
    }

//    public String getPaymentMethod() {
//        return paymentMethod;
//    }

    public double getCurrentTotalPrice() {
        return currentTotalPrice;
    }

}
