package se.kth.iv1350.sem3.model;

import se.kth.iv1350.sem3.integration.dto.ItemDTO;
import se.kth.iv1350.sem3.integration.dto.SaleDTO;

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


    /**
     * Constructor to initialize and object of the Sale, i.e an ongoing sale.
     */
    public Sale() {
        this.purchasedItems = new ArrayList<>();
        this.currentTotalPrice = 0;
        this.totalVAT = 0;
    }

    private List<ItemDTO> printOutList() {
        return purchasedItems;
    }


    /**
     * Checks a given item of the type @Link(ItemDTO) exists in the current sale, or purchased items list.
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
     * @return The updated saleDTO after scanning the new item.
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
     * Increases the quantity of an item in the sale by scanning and adding it to the sale.
     * @param itemID The ID of the item to be scanned again.
     * @return A new saleDTO after increasing the quantity of the item.
     */
    public SaleDTO increaseQuantity(int itemID) {
            for (ItemDTO item : purchasedItems) {
                if (item.getItemID() == itemID) {
                    System.out.println("This is the itemQuantity: " + item.getQuantity());

                    int quantity = item.getQuantity() + 1;
                    System.out.println("This is the quantity after incrementing the quantity: " + quantity);
                    //item.getQuantity = item.getQuantity() + 1;
                    //System.out.println("This is the quantity of the item: " + quantity);
                    System.out.println(item.toString());

                    ItemDTO updatedItemWithQuantity = new ItemDTO(item.getItemName(), itemID, item.getItemPrice(), item.getItemVAT(), quantity);

                    //double itemTTTprice = calculatePriceAndVATForScannedItem(itemPrice, itemVAT);
                    double itemTotalPrice = item.getItemPrice() * (1 + item.getItemVAT());
                    totalVAT += (item.getItemPrice() * item.getItemVAT());

                    purchasedItems.remove(item);
                    purchasedItems.add(updatedItemWithQuantity);
                    /**
                     * Vad ifall jag hade tagit bort item:et, och skapat ett nytt objekt av samma itemDTO
                     * fast denna gång med en specifierad quantity av den itemDTO?
                     */
                    currentTotalPrice = currentTotalPrice + itemTotalPrice;
                    this.saleDTO = new SaleDTO(purchasedItems, currentTotalPrice, totalVAT);
                    return saleDTO;
                }
            }
        System.out.println("A String never meant to be reached");
        this.saleDTO = new SaleDTO(purchasedItems, currentTotalPrice, totalVAT);
        return saleDTO;
    }

    public double getCurrentTotalPrice() {
        return currentTotalPrice;
    }

    public List<ItemDTO> getSaleDTOItemList() {
        return purchasedItems;
    }

    public void setCurrentTotalPrice(double currentTotalPrice) {
        this.currentTotalPrice = currentTotalPrice;
    }

    public void setPurchasedItems(List<ItemDTO> purchasedItems) {
        this.purchasedItems = purchasedItems;
    }

    public void setSaleDTO(SaleDTO saleDTO) {
        this.saleDTO = saleDTO;
    }

    public SaleDTO getSaleDTO() {
        return saleDTO;
    }
}
