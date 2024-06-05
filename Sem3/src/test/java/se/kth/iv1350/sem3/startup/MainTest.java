package se.kth.iv1350.sem3.startup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.sem3.view.ErrorMessageHandler;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private Main mainTest; // Change variable name from mainTest to main
    private ByteArrayOutputStream inMemoryPrintOut;
    private PrintStream originalSysOut;

    @BeforeEach
    void setUp() {
        mainTest = new Main();
        inMemoryPrintOut = new ByteArrayOutputStream();
        originalSysOut = System.out;
        System.setOut(new PrintStream(inMemoryPrintOut));
    }

    @AfterEach
    void tearDown() {
        mainTest = null; // Change variable name from main to mainTest
        inMemoryPrintOut = null;
        System.setOut(originalSysOut);
    }

    @Test
    void mainTest() throws ErrorMessageHandler {
        String[] args = {}; // Empty array
        Main.main(args); // Change method call from main.main(args) to mainTest.main(args)
        String printOutFromView = inMemoryPrintOut.toString();
        List<String> expectedPrintString = List.of(
                "First row in main, about to instantiate contr object.",
                "Instantiated the controller object, about to send the contr object to View's constructor.",
                "Sent the contr object to View's constructor. Instantiated view object.",
                "Called on sampleExecution using view object reference.",
                "Called on sampleExecution1 using view object reference.");
        for (String expectedResult : expectedPrintString) {
            assertTrue(printOutFromView.contains(expectedResult),
                    "Expected output not found: " + expectedResult);
        }
    }
}