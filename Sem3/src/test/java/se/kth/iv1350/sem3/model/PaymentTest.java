package se.kth.iv1350.sem3.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    private Payment payment;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        payment = null;
    }

    @Test
    void testPaymentAmount() {
        double amount = 200;
        String paymentMethod = "Cash";
        double currentTotalPrice = 150;
        Payment payment = new Payment(amount, paymentMethod, currentTotalPrice);

        assertEquals(payment.getAmount(), amount);
    }

    @Test
    void testPaymentPrice() {
        double amount = 200;
        String paymentMethod = "Cash";
        double currentTotalPrice = 150;
        Payment payment = new Payment(amount, paymentMethod, currentTotalPrice);

        assertEquals(payment.getCurrentTotalPrice(), currentTotalPrice);
    }

    @Test
    void testPaymentChange() {
        double amount = 200;
        String paymentMethod = "Cash";
        double currentTotalPrice = 150;
        double expectedChange = amount - currentTotalPrice;

        Payment payment = new Payment(amount, paymentMethod, currentTotalPrice);

        assertEquals(payment.getCustomerChange(), expectedChange);
    }

    @Test
    void testPaymentChangePaymentMethod() {
        double amount = 200;
        String paymentMethod = "Cash";
        double currentTotalPrice = 150;
        double expectedChange = amount - currentTotalPrice;

        Payment payment = new Payment(amount, paymentMethod, currentTotalPrice);

        assertEquals(payment.getPaymentMethod(), paymentMethod);
    }

    @Test
    void testPaymentFalseEquivalencyViaNull() {
        double amount = 200;
        String paymentMethod = "Cash";
        double currentTotalPrice = 150;
        double expectedChange = amount - currentTotalPrice;

        Payment payment = new Payment(amount, null, currentTotalPrice);

        assertFalse(payment.getPaymentMethod() == paymentMethod);
    }

    @Test
    void testPaymentMethodNull() {
        double amount = 200;
        String paymentMethod = "Cash";
        double currentTotalPrice = 150;
        double expectedChange = amount - currentTotalPrice;

        Payment payment = new Payment(amount, null, currentTotalPrice);

        assertNull(payment.getPaymentMethod());
    }

    @Test
    void testPaymentMethodNotNull() {
        double amount = 200;
        String paymentMethod = "Cash";
        double currentTotalPrice = 150;
        double expectedChange = amount - currentTotalPrice;

        Payment payment = new Payment(amount, null, currentTotalPrice);

        assertNotNull(payment.getCustomerChange());
    }

    @Test
    void testPaymentMethodNotNull2() {
        double amount = 200;
        String paymentMethod = "Cash";
        double currentTotalPrice = 150;
        double expectedChange = amount - currentTotalPrice;

        Payment payment = new Payment(amount, null, currentTotalPrice);

        assertNotNull(payment.getCurrentTotalPrice());
    }
}