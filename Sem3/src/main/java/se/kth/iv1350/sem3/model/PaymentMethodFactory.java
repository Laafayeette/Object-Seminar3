package se.kth.iv1350.sem3.model;

public class PaymentMethodFactory {

    public PaymentMethodStrategy getDefaultPaymentMethodStrategy(String paymentMethod) throws IllegalArgumentException {
        if(paymentMethod.equalsIgnoreCase("CreditCard")) {
             return new CreditCardPaymentMethod();
        }
        else if(paymentMethod.equalsIgnoreCase("Cash")) {
            return new CashPaymentMethod();
        }
        else {
            throw new IllegalArgumentException("Invalid payment method: " + paymentMethod);
        }
    }
}
