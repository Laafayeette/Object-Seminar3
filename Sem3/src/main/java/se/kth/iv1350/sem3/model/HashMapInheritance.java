package se.kth.iv1350.sem3.model;

import se.kth.iv1350.sem3.integration.dto.ItemDTO;
import se.kth.iv1350.sem3.integration.dto.SaleDTO;
import se.kth.iv1350.sem3.util.HashMapLogger;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class HashMapInheritance<K, V> extends HashMap<K, V> {

    private final SaleDTO saleDTO;
    private final LocalDateTime timeOfLog;

    private final HashMapLogger mapLogger;

    public HashMapInheritance(SaleDTO saleDTO, LocalDateTime timeOfLog, HashMapLogger mapLogger) {
        this.saleDTO = saleDTO;
        this.timeOfLog = timeOfLog;
        this.mapLogger = mapLogger;
    }

    @Override
    public V put(K key, V value) {
        mapLogger.log(getItemInfo());
        return super.put(key, value);
    }

    private void getKeyValue() {
    }

     private Map<String, Double> getItemInfo() {
         Map<String, Double> itemInfo = new HashMap<>();
         for (ItemDTO item : saleDTO.getPurchasedItems()) {
             String itemName = item.getItemName();
             double itemPrice = item.getItemPrice();
         }
         return itemInfo;
     }



         private void itemInfoGenerator(StringBuilder receiptBuilder) {
        for (ItemDTO item : saleDTO.getPurchasedItems()) {
            String itemName = item.getItemName();
            double multipliedPriceOfSameItem = item.getItemPrice() * item.getQuantity();

            receiptBuilder.append(String.format("%s %d x %.2f  %.2f SEK%n", itemName, item.getQuantity(), item.getItemPrice(), multipliedPriceOfSameItem));
        }
    }


}
