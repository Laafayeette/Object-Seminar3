package se.kth.iv1350.sem3.integration;

import se.kth.iv1350.sem3.integration.dto.ItemDTO;
import se.kth.iv1350.sem3.integration.dto.SaleDTO;

import java.util.*;

public class InventorySystem {

    private String itemName;
    private int itemID;
    private double itemPrice;
    private double itemVAT;
    private int quantity;

    private List<ItemDTO> itemsInStore = new ArrayList<>();

    /**
     * Constructor for the Inventory System class, initialized by the Controller's constructor.
     * Initializes the inventory system by adding itemsInStore to the store.
     */
    public InventorySystem() {
        addItemsToStore();
    }

    /**
     * Generating dummy data
     */
    private void addItemsToStore() {
        itemsInStore.add(new ItemDTO("Banana", 1, 7.99, 0.06));
        itemsInStore.add(new ItemDTO("Apple", 2, 3.49, 0.12));
        itemsInStore.add(new ItemDTO("Orange", 3, 4.99, 0.06));
        itemsInStore.add(new ItemDTO("Grapes", 4, 8.99, 0.25));
    }

    /**
     * Fetches the item information pertaining to the given item ID, from the external inventory system.
     * @param itemID The ID of the item to be scanned.
     * @return An item of type @Link(ItemDTO) representing the entirety of the item that was resulted from the fetching.
     */
    public ItemDTO fetchItemInfo(int itemID) throws ItemInvalidException, DatabaseCallException   {
        if(itemID == 7){
            System.out.println("You have reached the itemID == 7 block, in fetchItemInfo (InventorySystem");
            throw new DatabaseCallException("Database call failed, please try again or check your connection.");
        }
        for(ItemDTO item : itemsInStore) {
            if(item.getItemID() == itemID) {
                return item;
            }
        }
        //If we cant find with the item then we throw the exception..!!!!
        System.out.println("About to throw an ItemInvalidException in fetchItemInfo to Controller (InventorySystem)");
        throw new ItemInvalidException(itemID);
    }

    /**
     * Sends sale information to the external inventory system.
     * @param saleDTO The saleDTO containing the information about the sale in question.
     */
    public void sendSaleInfo(SaleDTO saleDTO) {
        determineInventoryToBeUpdated(saleDTO);
    }

    private void determineInventoryToBeUpdated(SaleDTO saleDTO) {
        Map<Integer, Integer> itemCounts = new HashMap<>();
        for (ItemDTO item : saleDTO.getPurchasedItems()) {
            int itemID = item.getItemID();
            itemCounts.put(itemID, itemCounts.getOrDefault(itemID, 0) + 1);
        }
        printDecreaseInventorySystemStatement(itemCounts);
    }

    private void printDecreaseInventorySystemStatement(Map<Integer, Integer> itemCounts) {
        System.out.println("-------------------Updating External Inventory System------------------------");
        for (Map.Entry<Integer, Integer> entry : itemCounts.entrySet()) {
            int itemID = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("Told External Inventory system to decrease inventory quantity of item with ID " + itemID + " by " + quantity + " units.");
        }
    }

    public List<ItemDTO> getItemList() {
        return itemsInStore;
    }
}
