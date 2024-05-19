package se.kth.iv1350.sem3.integration;

public class ItemInvalidException extends Exception{

    public ItemInvalidException(int itemID){
        super("The item with ID " + itemID + " is not valid, as it does not exist in the inventory system.");
    }


}
