package se.kth.iv1350.sem3.model;

public class PaymentMethodFactory {

    private static final PaymentMethodFactory INSTANCE = new PaymentMethodFactory();
    private final PaymentMethodStrategy creditCardPaymentMethod = new CreditCardPaymentMethod();
    private final PaymentMethodStrategy cashPaymentMethod = new CashPaymentMethod();

    private PaymentMethodFactory() {
    }

    public static PaymentMethodFactory getInstance() {
        return INSTANCE;
    }

    public PaymentMethodStrategy getDefaultPaymentMethodStrategy(String paymentMethod) throws IllegalArgumentException {
        if(!paymentMethod.equalsIgnoreCase("Credit Card") && !paymentMethod.equalsIgnoreCase("Cash")) {
            throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
        }
        else if (paymentMethod.equalsIgnoreCase("Credit Card")) {
             return creditCardPaymentMethod;
        }
        return cashPaymentMethod;
    }
}
