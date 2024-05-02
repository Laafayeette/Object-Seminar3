package se.kth.iv1350.sem3.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaleLogTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testInitialSaleIdSequence() {
        int initialSaleIDSequence = SaleLog.saleIDSequence; //1
        SaleLog saleLog = new SaleLog();

        int saleID = saleLog.getSaleID();   //1 eftersom ++ kommer efteråt
        assertEquals(initialSaleIDSequence, saleID);
    }

    @Test
    void testSaleIDSequenceIncrement() {
        int initialSaleIDSequence = SaleLog.saleIDSequence; //1
        SaleLog saleLog = new SaleLog();   //Nu är SaleLog.saleIDSequence == 2.

        int saleID = saleLog.getSaleID();   //1 eftersom ++ körs efteråt

        assertEquals(initialSaleIDSequence + 1, SaleLog.saleIDSequence);
    }

    @Test
    public void testSaleIDSequenceIncrementThrice() {
        int initialSaleIDSequence = SaleLog.saleIDSequence; //Remains at one, initialized once.

        SaleLog saleLog1 = new SaleLog();
        SaleLog saleLog2 = new SaleLog();
        SaleLog saleLog3 = new SaleLog();

        assertEquals(initialSaleIDSequence + 3, SaleLog.saleIDSequence, "The initial static sequence variable was not incremented by three when calling the constructor thrice.");
    }
}