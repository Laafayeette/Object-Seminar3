package se.kth.iv1350.sem3.view;

/**
 * A class representing the error message to be printed to the user interface.
 */
public class ErrorMessageHandler extends Exception {

    void showMessage(String message) {
        StringBuilder errorstringBuilder = new StringBuilder();
        errorstringBuilder.append("Error: ");
        errorstringBuilder.append(message);
        errorstringBuilder.append("\n");
        System.out.println(errorstringBuilder);
    }

}
