package se.kth.iv1350.sem3.model;

import se.kth.iv1350.sem3.view.View;

/**
 * Represents the factory for the {@link PaymentMethodStrategy} implementations.
 * Initiates the objects once and lets the instances permeate the entire program.
 */
public class PaymentMethodFactory {

    private static final PaymentMethodFactory INSTANCE = new PaymentMethodFactory();
    private final PaymentMethodStrategy creditCardPaymentMethod = new CreditCardPaymentMethod();
    private final PaymentMethodStrategy cashPaymentMethod = new CashPaymentMethod();

    private PaymentMethodFactory() {
    }

    /**
     * A getter for the {@link PaymentMethodFactory INSTANCE}.
     * Allows other classes using it to not initiate it when needing it.
     * @return The <code>PaymentMethodFactory</code> {@link PaymentMethodFactory object}
     */
    public static PaymentMethodFactory getInstance() {
        return INSTANCE;
    }

    /**
     * A getter for the instances of the implementations of the {@link PaymentMethodStrategy} interface.
     * @param paymentMethod The <code>paymentMethod</code> entered in the {@link View} class, i.e from the customer.
     * @return The relevant <code>paymentMethod</code> object.
     * @throws IllegalArgumentException If an unsupported <code>paymentMethod</code> was used by the customer.
     */
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
