package se.kth.iv1350.sem3.view;

public class ErrorMessageHandler extends Exception {

    private final int itemID;

    public ErrorMessageHandler(int itemID) {
        //super(message);
        System.out.println("The item you have entered is invalid, no item with ID " + itemID + " exists" +
                " Please try another item.");
        this.itemID = itemID;
    }
}
