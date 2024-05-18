package se.kth.iv1350.sem3.view;

public class ErrorMessageHandler extends Exception {
//    public ErrorMessageHandler(int i) {
//    }

    //private final int itemID;


    void showMessage(String message) {
        StringBuilder errorstringBuilder = new StringBuilder();
        errorstringBuilder.append("Error: ");
        errorstringBuilder.append(message);
        errorstringBuilder.append("\n");
        System.out.println(errorstringBuilder);
    }

//    public ErrorMessageHandler(int itemID) {
//        //super(message);
//        System.out.println("The item you have entered is invalid, no item with ID " + itemID + " exists" +
//               " Please try another item.");
//        this.itemID = itemID;
//    }
}
