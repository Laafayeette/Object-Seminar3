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
        System.out.println("First row in main, about to instantiate contr object.");
        Controller contr = new Controller();
        System.out.println("Instantiated the controller object, about to send the contr object to View's constructor.");
        View view = new View(contr);
        System.out.println("Sent the contr object to View's constructor. Instantiated view object.");
        view.sampleExecution();
        System.out.println("Called on sampleExecution using view object reference.");
        view.sampleExecution1();
        System.out.println("Called on sampleExecution1 using view object reference.");
    }
}