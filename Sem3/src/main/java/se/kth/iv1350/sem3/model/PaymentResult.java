package se.kth.iv1350.sem3.model;

public class PaymentResult {

    private final Receipt receipt;
    private final double customerChange;

    public PaymentResult(Receipt receipt, double customerChange) {
        this.receipt = receipt;
        this.customerChange = customerChange;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public double getCustomerChange() {
        return customerChange;
    }
}
