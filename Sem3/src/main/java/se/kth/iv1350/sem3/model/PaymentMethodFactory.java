package se.kth.iv1350.sem3.model;

public class PaymentMethodFactory {

    private static final PaymentMethodFactory INSTANCE = new PaymentMethodFactory();
    private PaymentMethodStrategy creditCardPaymentMethod = new CreditCardPaymentMethod();
    private PaymentMethodStrategy cashPaymentMethod = new CashPaymentMethod();

    private PaymentMethodFactory() {
    }

    public static PaymentMethodFactory getInstance() {
        return INSTANCE;
    }

    public PaymentMethodStrategy getDefaultPaymentMethodStrategy(String paymentMethod) throws IllegalArgumentException {
        if(paymentMethod.equalsIgnoreCase("CreditCard")) {
             return creditCardPaymentMethod;
        }
        else if(paymentMethod.equalsIgnoreCase("Cash")) {
            return cashPaymentMethod;
        }
        else {
            throw new IllegalArgumentException("Invalid payment method: " + paymentMethod);
        }
    }
}
