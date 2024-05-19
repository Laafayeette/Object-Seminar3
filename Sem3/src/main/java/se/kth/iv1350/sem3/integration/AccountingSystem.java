package se.kth.iv1350.sem3.integration;

import se.kth.iv1350.sem3.integration.dto.SaleDTO;

/**
 * Represents the external accounting system of the retail store.
 * Finished sales are sent here for updating of account.
 * Although this class is responsible for a printout, it is not part of the view, or user interface.
 */
public class AccountingSystem {

    public AccountingSystem() {
    }

    /**
     * Sends the sale info to the external accounting system.
     * @param saleDTO The saleDTO to be sent to the external accounting system.
     */
    public void sendSaleInfo(SaleDTO saleDTO) {
        System.out.println("\n-------------------Updating Accounting System------------------------");
        System.out.println("Sent sale information to Accounting System for accounting");
    }
}
