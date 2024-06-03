package se.kth.iv1350.sem3.model;

import se.kth.iv1350.sem3.integration.dto.ItemDTO;
import se.kth.iv1350.sem3.integration.dto.SaleDTO;


import java.time.LocalDateTime;
import java.util.HashMap;

public class HashMapInheritance<K, V> extends HashMap<K, V> {

    private final SaleDTO saleDTO;
    private final LocalDateTime timeOfLog;

    public HashMapInheritance(SaleDTO saleDTO, LocalDateTime timeOfLog) {
        this.saleDTO = saleDTO;
        this.timeOfLog = timeOfLog;
    }



    private void itemInfoGenerator(StringBuilder receiptBuilder) {
        for (ItemDTO item : saleDTO.getPurchasedItems()) {
            String itemName = item.getItemName();
            double multipliedPriceOfSameItem = item.getItemPrice() * item.getQuantity();

            receiptBuilder.append(String.format("%s %d x %.2f  %.2f SEK%n", itemName, item.getQuantity(), item.getItemPrice(), multipliedPriceOfSameItem));
        }
    }


}
