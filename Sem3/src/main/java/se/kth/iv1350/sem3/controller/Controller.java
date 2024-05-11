package se.kth.iv1350.sem3.controller;

import se.kth.iv1350.sem3.integration.AccountingSystem;
import se.kth.iv1350.sem3.integration.InventorySystem;
import se.kth.iv1350.sem3.integration.Printer;
import se.kth.iv1350.sem3.integration.dto.ItemDTO;
import se.kth.iv1350.sem3.integration.dto.SaleDTO;
import se.kth.iv1350.sem3.model.Payment;
import se.kth.iv1350.sem3.model.Receipt;
import se.kth.iv1350.sem3.model.Sale;
import se.kth.iv1350.sem3.model.SaleLog;

/**
 * This class represents the communication endpoint between the view layer and the rest of the program.
 * All inputs coming from the store goes through this class.
 */
public class Controller {

    private AccountingSystem accSys;
    private InventorySystem invSys;
    private Printer printer;
    private SaleLog saleLog;

    private Sale sale;


    /**
     * Constructor for the Controller class. Initiates the program by instantiating objects of the program,
     * The constructor returns an object of Controller with references to these instantiations.
     */
    public Controller() {
        accSys = new AccountingSystem();
        invSys = new InventorySystem();
        printer = new Printer();
        saleLog = new SaleLog();
    }

    /**
     * Method that calls on the constructor in Sale to start a sale operation in the store.
     */
    public void startSale() {
        sale = new Sale();
    }


    /**
     *  Scans a given item with a given <code>itemID</code> and <code>quantity</code>.
     *  If the item has previously been scanned in the sale, it fetches the item information from the current sale and returns an update saleDTO.
     *  Otherwise, it fetches the item information from the inventory system and returns an updated saleDTO with the scanned item.
     * @param itemID The ID of the item to be scanned.
     * @param quantity The quantity of the item to be scanned.
     * @return A saleDTO representing the updated state of the sale after scanning the item.
     */
    public SaleDTO scanItem(int itemID, int quantity) {
        if(sale.findItemInfo(itemID)) {
            SaleDTO saleDTO = sale.increaseQuantity(itemID);
            return saleDTO;
        }
        else {
            ItemDTO itemDTO = invSys.fetchItemInfo(itemID);
            SaleDTO saleDTO = sale.updateSale(itemDTO);
            return saleDTO;
        }
    }

    /**
     * Ends the current sale and returns the total price accumulated during the sale.
     * @return The total price of the sale.
     */
    public double endSale() {
        double currentTotalSale = sale.getCurrentTotalPrice();
        return currentTotalSale;
    }

    private void updateExternalSystems() {
        invSys.sendSaleInfo(sale.getSaleDTO());
        accSys.sendSaleInfo(sale.getSaleDTO());
    }

    /**
     * Issues the payment for the current sale.
     * Encompasses calculating the change to give to customer.
     * Printing the receipt and updating accounting and inventory systems.
     * @param amount The amount given from the customer
     * @param paymentMethod Payment method customer enters, usually cash.
     * @return Change given back to the customer
     */
    public double pay(double amount, String paymentMethod) {
        System.out.println("\n-------------------Begin Payment------------------------" + "\nCustomer pays: " + amount + " SEK via " + paymentMethod);
        double currentTotalPrice = sale.getCurrentTotalPrice();
        Payment payment = new Payment(amount, paymentMethod, currentTotalPrice);
        Receipt receipt = new Receipt(sale.getSaleDTO(), payment);
        printer.print(receipt);
        saleLog.registerSaleLog(sale.getSaleDTO());
        updateExternalSystems();
        return payment.getCustomerChange();
    }

    public Sale getSale() {
        return sale;
    }
}
