package se.kth.iv1350.sem3.integration;

import se.kth.iv1350.sem3.model.Receipt;

/**
 * Printer class for the sale.
 * Its purpose is to handle the printing of the receipts given to it.
 */
public class Printer {

    /**
     * Constructor for the Printer class, initiates an object of the class.
     */
    public Printer() {
    }

    /**
     * Sets the given receipt to be printed at the end of the sale.
     * @param receipt The receipt object that is to be printed.
     */
    public void print(Receipt receipt) {
        System.out.println(receipt.getReceipt());
    }



}
