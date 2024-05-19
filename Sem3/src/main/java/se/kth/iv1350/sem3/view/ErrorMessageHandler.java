package se.kth.iv1350.sem3.view;

/**
 * A class representing the error message to be printed to the user interface.
 */
public class ErrorMessageHandler extends Exception {

    void showMessage(String message) {
        String errorString = "Error: " +
                message +
                "\n";
        System.out.println(errorString);
    }
}
