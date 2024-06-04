package se.kth.iv1350.sem3.controller;

import se.kth.iv1350.sem3.integration.*;
import se.kth.iv1350.sem3.integration.dto.ItemDTO;
import se.kth.iv1350.sem3.integration.dto.SaleDTO;
import se.kth.iv1350.sem3.model.*;
import se.kth.iv1350.sem3.util.ErrorLogHandler;

import java.util.ArrayList;
import java.util.List;

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

    private ErrorLogHandler errorLogger;

    private List<SaleObserver> saleObservers = new ArrayList<>();

    /**
     * Constructor for the Controller class. Initiates the program by instantiating objects of the program,
     * The constructor returns an object of Controller with references to these instantiations.
     */
    public Controller() {
        accSys = new AccountingSystem();
        invSys = new InventorySystem();
        printer = new Printer();
        saleLog = new SaleLog();
        this.errorLogger = new ErrorLogHandler("ScannedItemErrorLog.txt");
    }

    /**
     * Method that calls on the constructor in Sale to start a sale operation in the store.
     * Takes the list of {@link List<SaleObserver> saleObservers} that was given from the view,
     * and gives it to the newly instantiated {@link Sale sale} object.
     */
    public void startSale() {
        sale = new Sale();
        sale.addSaleObservers(saleObservers);
    }


    /**
     *  Scans a given item with a given <code>itemID</code> and <code>quantity</code>.
     *  If the item has previously been scanned in the sale, it fetches the item information from the current sale and returns an update saleDTO.
     *  Otherwise, it fetches the item information from the inventory system and returns an updated saleDTO with the scanned item.
     * @param itemID The ID of the item to be scanned.
     * @return A {@link SaleDTO saleDTO} representing the updated state of the sale after scanning the item.
     * @throws ItemInvalidException If the scanItem operation resulted in a itemID that does not exist in the inventory system.
     * @throws DatabaseConnectionException If the scanItem operation resulted in that the database could not be reached.
     */
    public SaleDTO scanItem(int itemID) throws ItemInvalidException, DatabaseConnectionException {
        if(sale.findItemInfo(itemID)) {
            SaleDTO saleDTO = sale.increaseQuantity(itemID);
            return saleDTO;
        }
        else {
            try {
                ItemDTO itemDTO = invSys.fetchItemInfo(itemID);
                SaleDTO saleDTO = sale.updateSale(itemDTO);
                return saleDTO;
            } catch (ItemInvalidException | DatabaseConnectionException e) {
                errorLogger.log(e);
                throw e;
            }
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
     * @throws IllegalArgumentException If the given paymentMethod is unsupported by the retail store.
     */
    public double pay(double amount, String paymentMethod) throws DatabaseConnectionException, IllegalArgumentException {
        PaymentMethodFactory paymentMethodFactory = PaymentMethodFactory.getInstance();
        PaymentResult paymentResult = sale.pay(amount, paymentMethodFactory.getDefaultPaymentMethodStrategy(paymentMethod));
        printer.print(paymentResult.receipt());
        saleLog.registerSaleLog(sale.getSaleDTO());
        updateExternalSystems();
        return paymentResult.customerChange();
    }



    public Sale getSale() {
        return sale;
    }

    /**
     * Adds a {@link SaleObserver saleObserver} to the list of {@link List<SaleObserver> saleObservers}.
     * This list is later given to the newly instantiated {@link Sale sale} object.
     * @param saleObserver The {@link SaleObserver saleObserver} to be added to the list.
     */
    public void addSaleObserver(SaleObserver saleObserver) {
        saleObservers.add(saleObserver);
    }
}
