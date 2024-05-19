package se.kth.iv1350.sem3.integration;

/**
 * A custom exception class defined for the retail store.
 * Represents the exception to when an item that is not present in the store is scanned.
 */
public class ItemInvalidException extends Exception{

    private final int itemID;

    /**
     * A constructor for the {@link ItemInvalidException} class.
     * Is called upon when other methods throws this exception.
     * @param itemID The scanned <code>itemID</code> that does not exist in the inventory system.
     */
    public ItemInvalidException(int itemID){
        super("The item with ID " + itemID + " is not valid, as it does not exist in the inventory system.");
        this.itemID = itemID;
    }

    public int getItemID(){
        return itemID;
    }

}
