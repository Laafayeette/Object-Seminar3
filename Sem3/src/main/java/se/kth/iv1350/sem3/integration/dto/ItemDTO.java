package se.kth.iv1350.sem3.integration.dto;

import java.util.Objects;

public class ItemDTO {

    private final String itemName;
    private final int itemID;
    private final double itemPrice;
    private final double itemVAT;
    private final int quantity;

    /**
     * Instantiates a new ItemDTO from the given descriptions of the item.
     * If no quantity is set, the quantity is default to one.
     * @param itemName The name of the item.
     * @param itemID The itemID of the item.
     * @param itemPrice The price of the item (excluding VAT).
     * @param itemVAT The VAT of the item.
     */
    public ItemDTO(String itemName, int itemID, double itemPrice, double itemVAT) {
        this.itemName = itemName;
        this.itemID = itemID;
        this.itemPrice = itemPrice;
        this.itemVAT = itemVAT;
        this.quantity = 1;
    }
    /**
     * Instantiates a new ItemDTO from the given descriptions of the item.
     * Quantity is set as the argument passed to it.
     * @param itemName The name of the item.
     * @param itemID The itemID of the item.
     * @param itemPrice The price of the item (excluding VAT).
     * @param itemVAT The VAT of the item.
     * @param quantity The quantity of the item.
     */
    public ItemDTO(String itemName, int itemID, double itemPrice, double itemVAT, int quantity) {
        this.itemName = itemName;
        this.itemID = itemID;
        this.itemPrice = itemPrice;
        this.itemVAT = itemVAT;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemID() {
        return itemID;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public double getItemVAT() {
        return itemVAT;
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     * Presents a custom printout of the itemDTO.
     * @return A formatted String with the values of @Link(ItemDTO).
     */
    @Override
    public String toString() {
        return
                "Item ID: " + itemID + "\n" +
                "Item name: " + itemName + "\n" +
                "Item cost: " + String.format("%.2f", itemPrice) + " SEK\n" +
                "VAT: " + (100* itemVAT) + "%";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ItemDTO itemDTO = (ItemDTO) obj;
        return itemID == itemDTO.itemID &&
                Double.compare(itemDTO.itemPrice, itemPrice) == 0 &&
                Double.compare(itemDTO.itemVAT, itemVAT) == 0 &&
                Objects.equals(itemName, itemDTO.itemName);
    }



}
