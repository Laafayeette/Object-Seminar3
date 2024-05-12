package se.kth.iv1350.sem3.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.sem3.integration.dto.ItemDTO;
import se.kth.iv1350.sem3.integration.dto.SaleDTO;
import se.kth.iv1350.sem3.model.Receipt;
import se.kth.iv1350.sem3.model.Payment;
import se.kth.iv1350.sem3.model.Sale;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PrinterTest {

    private ByteArrayOutputStream outputStream;
    private PrintStream originalSysOut;

    private double currentTotalPrice;
    private List<ItemDTO> purchasedItems;
    private double totalVAT;

    private SaleDTO saleDTO;
    private Payment payment;
    private Receipt receipt;
    private Sale sale;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        originalSysOut = System.out;
        System.setOut(new PrintStream(outputStream));
        Printer printer = new Printer();
        purchasedItems = new ArrayList<>();
    }

    @AfterEach
    void tearDown() {
        outputStream = null;
        System.setOut(originalSysOut);
        Printer printer = null;
        sale = null;
        saleDTO = null;
        payment = null;
        receipt = null;
        purchasedItems = null;
        totalVAT = 0;
        currentTotalPrice = 0;
    }

    @Test
    void testPrintNotNull() {
        Printer printer = new Printer();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ItemDTO item1 = new ItemDTO("Gurka", 2, 5.0, 0.25, 1);
        purchasedItems.add(item1);

        double item1Price = item1.getItemPrice();
        double item1VAT = item1.getItemVAT();

        double currentTotalPrice = item1Price;  //5 kr

        SaleDTO saleDTO = new SaleDTO(purchasedItems, currentTotalPrice, item1VAT);
        Payment payment = new Payment(150, "Cash", currentTotalPrice);
        Receipt receipt = new Receipt(saleDTO, payment);

        printer.print(receipt);

        assertNotNull(outputStream.toString());
    }

    @Test
    void testPrintEqualsPrintedReceipt() {
        Printer printer = new Printer();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ItemDTO item1 = new ItemDTO("Gurka", 2, 5.0, 0.25, 1);
        purchasedItems.add(item1);

        double item1Price = item1.getItemPrice();
        double item1VAT = item1.getItemVAT();

        double currentTotalPrice = item1Price;  //5 kr

        SaleDTO saleDTO = new SaleDTO(purchasedItems, currentTotalPrice, item1VAT);
        Payment payment = new Payment(150, "Cash", currentTotalPrice);
        Receipt receipt = new Receipt(saleDTO, payment);

        printer.print(receipt);

        String expectedReceipt = receipt.getReceipt();
        String printedReceipt = outputStream.toString();
        assertEquals(expectedReceipt.strip(), printedReceipt.strip());
    }
}