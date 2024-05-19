package se.kth.iv1350.sem3.model;

/**
 * A read-only Record class to be used for sending information related to the Payment to other implementations in need of it.
 */
public record PaymentResult(Receipt receipt, double customerChange) {

}
