package se.kth.iv1350.sem3.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.sem3.controller.Controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ViewTest {
    //private ByteArrayOutputStream inMemoryPrintOut;
    private View view;
    private ByteArrayOutputStream inMemoryPrintOut;
    private PrintStream originalSysOut;

    @BeforeEach
    void setUp() {
        Controller ctrl = new Controller();
        view = new View(ctrl);
        inMemoryPrintOut = new ByteArrayOutputStream();
        originalSysOut = System.out;
        System.setOut(new PrintStream(inMemoryPrintOut));
    }

    @AfterEach
    void tearDown() {
        view = null;
        inMemoryPrintOut = null;
        System.setOut(originalSysOut);
    }

    @Test
    void sampleExecutionPrints() {
        view.sampleExecution();
        String printOutFromView = inMemoryPrintOut.toString();
        List<String> expectedPrintString = List.of(
                "Scanning item with ID", "Item ID:", "Item name:", "Item cost:", "VAT:",
                "Total Cost (incl VAT):", "Total VAT:", "End sale",
                "Begin Payment", "Customer pays:", "Quantity of this item sold:",
                "Date", "Cash:", "Change:" );
        for (String expectedResult : expectedPrintString) {
            assertTrue(printOutFromView.contains(expectedResult),
                    "Expected output not found: " + expectedResult);
        }
    }

    @Test
    void sampleExecutionPrintNoChangeForInvalidPaymentMethod() {
        view.sampleExecution1();
        String printOutFromView = inMemoryPrintOut.toString();
        String unexpectedOutput = "Change to give back to Customer:";
        boolean unexpectedOutputFound = printOutFromView.contains(unexpectedOutput);
        assertFalse(unexpectedOutputFound,
                "Unexpected output found: " + unexpectedOutput);
    }

    @Test
    void returnChangeToCustomer() {
        view.sampleExecution();
        String printOutFromView = inMemoryPrintOut.toString();
        String expectedOutput = "Change to give back to Customer: ";
        boolean unexpectedOutputFound = printOutFromView.contains(expectedOutput);
        assertTrue(unexpectedOutputFound,
                "Expected output not found: " + expectedOutput);
    }

    @Test
    void printItemDTOString() {
        view.sampleExecution();
        String printOutFromView = inMemoryPrintOut.toString();
        List<String> expectedPrintString = List.of("Item ID:", "Item name:", "Item cost:", "VAT:");
        for (String expectedResult : expectedPrintString) {
            assertTrue(printOutFromView.contains(expectedResult),
                    "Expected output not found: " + expectedResult);
        }
    }

    @Test
    void printSaleDTO() {
        view.sampleExecution();
        String printOutFromView = inMemoryPrintOut.toString();
        List<String> expectedPrintString = List.of("Total Cost (incl VAT):", "Total VAT:");
        for (String expectedResult : expectedPrintString) {
            assertTrue(printOutFromView.contains(expectedResult),
                    "Expected output not found: " + expectedResult);
        }
    }

    @Test
    void printEndSale() {
        view.sampleExecution();
        String printOutFromView = inMemoryPrintOut.toString();
        String expectedPrintString = "End sale";
        assertTrue(printOutFromView.contains(expectedPrintString),
                "Expected output not found: " + expectedPrintString);
    }
}