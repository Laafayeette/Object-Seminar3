package se.kth.iv1350.sem3.startup;

import se.kth.iv1350.sem3.controller.Controller;
import se.kth.iv1350.sem3.view.ErrorMessageHandler;
import se.kth.iv1350.sem3.view.View;

/**
 * Represents the class in which the main method is run.
 */
public class Main {

    /**
     * Main method is responsible for starting the program.
     * Creates instances of the @Link(Controller) and passes it to the @Link(View) class' constructor to initiate the View object.
     * @param args The Command-line arguments to the program.
     */
    public static void main(String[] args) throws ErrorMessageHandler {

        Controller contr = new Controller();
        View view = new View(contr);
        view.sampleExecution();
        view.sampleExecution1();
    }
}