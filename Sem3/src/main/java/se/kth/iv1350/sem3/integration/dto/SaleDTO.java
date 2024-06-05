package se.kth.iv1350.sem3.integration.dto;

import java.util.List;

public class SaleDTO {

    private final double currentTotalPrice;
    private final List<ItemDTO> purchasedItems;
    private final double totalVAT;

    public SaleDTO(List<ItemDTO> purchasedItems, double currentTotalPrice, double totalVAT) {
        this.currentTotalPrice = currentTotalPrice;
        this.purchasedItems = purchasedItems;
        this.totalVAT = totalVAT;
    }

    public double getCurrentTotalPrice() {
        return currentTotalPrice;
    }

    public List<ItemDTO> getPurchasedItems() {
        return purchasedItems;
    }

    public double getTotalVAT() {
        return totalVAT;
    }

    public ItemDTO getItemDTO(int index) throws IndexOutOfBoundsException {
        return purchasedItems.get(index-1);
    }



}
