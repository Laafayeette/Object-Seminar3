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

    /**
     * Retrieves an {@link ItemDTO} from the purchased items list, i.e, the list of items scanned in the sale.
     * @param index An index starting from 1, representing the ID for the item to retrieve.
     * @return The {@link ItemDTO} at the given index.
     * @throws IndexOutOfBoundsException If the specified index is out of range, meaning if the item has not been scanned in the sale.
     */
    public ItemDTO getItemDTO(int index) throws IndexOutOfBoundsException {
        return purchasedItems.get(index-1);
    }



}
