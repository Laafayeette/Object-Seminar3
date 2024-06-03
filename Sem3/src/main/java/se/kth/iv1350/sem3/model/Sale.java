package se.kth.iv1350.sem3.model;

import se.kth.iv1350.sem3.integration.dto.ItemDTO;
import se.kth.iv1350.sem3.integration.dto.SaleDTO;
import se.kth.iv1350.sem3.util.HashMapLogger;

import java.nio.DoubleBuffer;
import java.time.LocalDateTime;
import java.util.*;

/**
 * This class represents the state of the current sale in the ongoing sale.
 * It has operations to be performed on the sale, as well as reading and writing to the sale.
 */
public class Sale {

    private double currentTotalPrice;
    private List<ItemDTO> purchasedItems;

    private double totalVAT;

    private SaleDTO saleDTO;

    private List<SaleObserver> saleObservers = new ArrayList<>();

    private HashMapInheritance<String, Double> hashMapInheritance;

    private HashMapComposition<String, Double> hashMapComposition;

    /**
     * Constructor to initialize and object of the Sale, i.e an ongoing sale.
     */
    public Sale() {
        this.purchasedItems = new ArrayList<>();
        this.currentTotalPrice = 0;
        this.totalVAT = 0;
        this.hashMapInheritance = new HashMapInheritance<>(new HashMapLogger("HashMapLog1.txt"));
        this.hashMapComposition = new HashMapComposition<>(new HashMapLogger("HashMapLog2.txt"));
    }

    private List<ItemDTO> printOutList() {
        return purchasedItems;
    }


    /**
     * Checks a given item of the type {@link ItemDTO} exists in the current sale, or purchased items list.
     *
     * @param itemID The ID of the item to be found.
     * @return <code>true</code>> if item is found, <code>false</code> if not found.
     */
    public boolean findItemInfo(int itemID) {
        for(ItemDTO item : purchasedItems) {
            if(item.getItemID() == itemID) {
                return true;
            }
        }
        return false;
    }

    private double calculatePriceAndVATForScannedItem(double itemPrice, double itemVAT) {
        double itemTotalPrice = itemPrice * (1 + itemVAT);
        totalVAT += (itemPrice * itemVAT); //I kronor ska det visas
        return itemTotalPrice;
    }

    /**
     * Updates the current sale with new information from the recently scanned item.
     * Calculates the new total price and total VAT with the added item to the sale.
     * @param itemDTO The itemDTO with information about the new item in the sale.
     * @return The updated {@link SaleDTO saleDTO} after scanning the new item.
     */
    public SaleDTO updateSale(ItemDTO itemDTO) {
        double itemPrice = itemDTO.getItemPrice();
        double itemVAT = itemDTO.getItemVAT();

        double itemTotalPrice = calculatePriceAndVATForScannedItem(itemPrice, itemVAT);

        purchasedItems.add(itemDTO);
        currentTotalPrice = currentTotalPrice + itemTotalPrice;
        this.saleDTO = new SaleDTO(purchasedItems, currentTotalPrice, totalVAT);
        return saleDTO;
    }

    /**
     * Increases the quantity of an item in the sale by scanning and adding it to the {@link Sale} instance.
     * @param itemID The ID of the item to be scanned again.
     * @return A new {@link SaleDTO saleDTO} after increasing the quantity of the item.
     */
    public SaleDTO increaseQuantity(int itemID) {
            for (ItemDTO item : purchasedItems) {
                if (item.getItemID() == itemID) {

                    int quantity = item.getQuantity() + 1;

                    ItemDTO updatedItemWithQuantity = new ItemDTO(item.getItemName(), itemID, item.getItemPrice(), item.getItemVAT(), quantity);

                    double itemTotalPrice = calculatePriceAndVATForScannedItem(item.getItemPrice(), item.getItemVAT());


                    purchasedItems.remove(item);
                    purchasedItems.add(updatedItemWithQuantity);
                    currentTotalPrice = currentTotalPrice + itemTotalPrice;
                    this.saleDTO = new SaleDTO(purchasedItems, currentTotalPrice, totalVAT);
                    return saleDTO;
                }
            }
        System.out.println("A String never meant to be reached");
        this.saleDTO = new SaleDTO(purchasedItems, currentTotalPrice, totalVAT);
        return saleDTO;
    }


    /**
     * Handles the payment for the Sale class.
     * After the payment has been finalized, the method notifies the observers about the updated state.
     * Subsequently, it returns an instance of {@link PaymentResult} back to the caller.
     * @param amount The amount to be paid.
     * @param paymentMethodStrategy The implementation of the payment method.
     * @return An instance of {@link PaymentResult}.
     */
    public PaymentResult pay(double amount, PaymentMethodStrategy paymentMethodStrategy) {
        Payment payment = new Payment(amount, getCurrentTotalPrice(), paymentMethodStrategy);
        Receipt receipt = new Receipt(getSaleDTO(), payment);
        notifyObservers();
        logSoldItemsInheritance();
        logSoldItemsComposition();
        return new PaymentResult(receipt, payment.getCustomerChange());
    }

    private void logSoldItemsInheritance() {
        for (ItemDTO item : getSaleDTO().getPurchasedItems()) {
            hashMapInheritance.put(item.getItemName(), item.getItemPrice());
        }
    }

    private void logSoldItemsComposition() {
        for (ItemDTO item : getSaleDTO().getPurchasedItems()) {
            hashMapComposition.put(item.getItemName(), item.getItemPrice());
        }
    }

    /**
     * Anropar på alla observers i saleObservers (listan) och skickar totala priset till updateTotalRevenue-metoden.
     */
    private void notifyObservers() {
        for(SaleObserver obs : saleObservers) {
            obs.updateTotalRevenue(getCurrentTotalPrice());
        }
    }

    /**
     * The list of sale observers to be added to the current sale object during instantiation of the sale.
     * This list is provided from the {@link se.kth.iv1350.sem3.controller.Controller} class because the Controller permeates all instances of sales in the program,
     * and therefore when the sale is started, the current sale object is simply provided with the list of observers it needs.
     * It does not itself perform any logic to fetch any observers, that is the Controller's job.
     * @param saleObservers The list of observers provided from Controller.
     */
    public void addSaleObservers(List<SaleObserver> saleObservers) {
        this.saleObservers.addAll(saleObservers);
    }

    public double getCurrentTotalPrice() {
        return currentTotalPrice;
    }

    public List<ItemDTO> getSaleDTOItemList() {
        return purchasedItems;
    }

    public SaleDTO getSaleDTO() {
        return saleDTO;
    }

    public Map<String, Double> getItemInfo() {
        Map<String, Double> itemInfo = new HashMap<>();
        for (ItemDTO item : saleDTO.getPurchasedItems()) {
            String itemName = item.getItemName();
            double itemPrice = item.getItemPrice();
        }
        return itemInfo;
    }
}
