package se.kth.iv1350.sem3.integration;

import se.kth.iv1350.sem3.integration.dto.SaleDTO;
import se.kth.iv1350.sem3.model.SaleLog;

public class AccountingSystem {

    public AccountingSystem() {
    }

    /**
     * Kommer transformeras till en privat metod i View klassen som gör printout.
     * @param saleDTO
     */
    //Testa system.out. STRINGS!!!!!!
    public void sendSaleInfo(SaleDTO saleDTO) {
        System.out.println("\n-------------------Updating Accounting System------------------------");
        System.out.println("Sent sale information to Accounting System for accounting");
    }
}
