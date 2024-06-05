package se.kth.iv1350.sem3.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.sem3.controller.Controller;
import se.kth.iv1350.sem3.integration.DatabaseConnectionException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TotalRevenueViewTest {
    private TotalRevenueView totalRevenueView;
    private ByteArrayOutputStream inMemoryPrintOut;
    private PrintStream originalSysOut;

    @BeforeEach
    void setUp() {
        totalRevenueView = new TotalRevenueView();
        inMemoryPrintOut = new ByteArrayOutputStream();
        PrintStream outStream = new PrintStream(inMemoryPrintOut);
        originalSysOut = System.out;
        System.setOut(outStream);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalSysOut);
        totalRevenueView = null;
        originalSysOut = null;
    }

    @Test
    void doShowTotalRevenue() {
        double totalRevenue = 1450;
        try {
            totalRevenueView.doShowTotalRevenue(totalRevenue);
        } catch (Exception e) {
            fail("Exception is thrown, fail in totalRevenueView.doShowTotalRevenue");
        }
        String printOutFromView = inMemoryPrintOut.toString();
        String expectedOutput = "Total Revenue: " + totalRevenue + " SEK";
        boolean unexpectedOutputFound = printOutFromView.contains(expectedOutput);
        assertTrue(unexpectedOutputFound,
                "Expected output not found: " + expectedOutput);
    }
    @Test
    void doShowExceptionTotalRevenueWithNull() {
        Double totalRevenue = null;
        Exception exception = null;
        try {
            totalRevenueView.doShowTotalRevenue(totalRevenue);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception, "Expected exception not thrown");
    }

    @Test
    void doShowExceptionTotalRevenueWithNull1() {
        Double totalRevenue = null;
        assertThrows(Exception.class, () -> totalRevenueView.doShowTotalRevenue(totalRevenue));
    }

}