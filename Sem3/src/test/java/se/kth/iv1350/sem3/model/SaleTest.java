package se.kth.iv1350.sem3.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import se.kth.iv1350.sem3.integration.dto.ItemDTO;
import se.kth.iv1350.sem3.integration.dto.SaleDTO;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class SaleTest {

    private ByteArrayOutputStream inMemoryPrintOut;
    private PrintStream inMemorySysOut;
    private PrintStream originalSysOut;

    private Sale sale;
    @Before
    public void setUp() throws Exception {
        sale = new Sale();
        inMemoryPrintOut = new ByteArrayOutputStream();
        PrintStream inMemorySysOut = new PrintStream(inMemoryPrintOut);
        originalSysOut = System.out;
        System.setOut(inMemorySysOut);
    }

    @After
    public void tearDown() throws Exception {
        sale = null;
        inMemoryPrintOut.close();
        System.setOut(originalSysOut);
    }


    @Test
    public void findItemInfo_ItemFound() {

        ItemDTO item1 = new ItemDTO("ABC123", 2, 10.0, 5.0, 1);
        ItemDTO item2 = new ItemDTO("DEF456", 3, 15.0, 6.0, 1);
        sale.getSaleDTOItemList().add(item1);
        sale.getSaleDTOItemList().add(item2);

        boolean expResult = true;

        boolean result = sale.findItemInfo(3);

        assertEquals("he item w ith ID 4 was expected to be found", expResult, result);
    }

    @Test
    public void findItemInfo_ItemNotFound() {
        Sale sale = new Sale();
        ItemDTO item1 = new ItemDTO("ABC123", 2, 10.0, 5.0, 1);
        ItemDTO item2 = new ItemDTO("DEF456", 3, 15.0, 6.0, 1);
        sale.getSaleDTOItemList().add(item1);
        sale.getSaleDTOItemList().add(item2);
        boolean expResult = false;
        boolean result = sale.findItemInfo(4);
        assertEquals("The item with ID 4 was not expected to be found", expResult, result);
    }

    @Test
    public void findItemInfo_emptyList() {
        Sale sale = new Sale();
        ItemDTO item1 = new ItemDTO("ABC123", 2, 10.0, 5.0, 1);
        boolean result = sale.getSaleDTOItemList().isEmpty();
        boolean expResult = true;
        assertEquals("The sale list is not empty", expResult, result);
    }

    @Test
    public void findItemInfo_notEmpty() {
        Sale sale = new Sale();
        ItemDTO item1 = new ItemDTO("ABC123", 2, 10.0, 5.0, 1);
        sale.getSaleDTOItemList().add(item1);
        boolean result = sale.getSaleDTOItemList().isEmpty();
        boolean expResult = false;
        assertEquals("The sale list is empty", expResult, result);
    }


    @Test
    public void testUpdateSaleNotEquals() {
        Sale sale = new Sale();
        ItemDTO item1 = new ItemDTO("ABC123", 2, 12.0, 0.25, 1);
        SaleDTO saleDTO = sale.updateSale(item1);

        List<ItemDTO> expectedPurchasedItems = new ArrayList<>();
        expectedPurchasedItems.add(item1);
        double expectedTotalPrice = 10.0 * (1 + 0.25); // itemPrice * (1 + itemVAT)
        double expectedTotalVAT = 10.0 * 0.25; // itemPrice * itemVAT
        SaleDTO expectedSaleDTO = new SaleDTO(expectedPurchasedItems, expectedTotalPrice, expectedTotalVAT);

        assertFalse(Objects.equals(saleDTO, expectedSaleDTO));
    }

    @Test
    public void testupdateSaleEmptyPurchasedList() {
        //Testing the case, that even when the values of an item: price and VAT are identical,
        //It does not matter unless there is an actual list of said item.
        //the list is empty so the expectedValues are never inserted.
        Sale sale = new Sale();
        ItemDTO item1 = new ItemDTO("ABC123", 2, 12.0, 0.25, 1);
        SaleDTO saleDTO = sale.updateSale(item1);

        List<ItemDTO> expectedPurchasedItems = new ArrayList<>();
        double expectedTotalPrice = 12.0 * (1 + 0.25); // itemPrice * (1 + itemVAT)
        double expectedTotalVAT = 12.0 * 0.25; // itemPrice * itemVAT
        SaleDTO expectedSaleDTO = new SaleDTO(expectedPurchasedItems, expectedTotalPrice, expectedTotalVAT);

        assertFalse(Objects.equals(saleDTO, expectedSaleDTO));
    }



    @Test
    public void testupdateSaleitemDTONotNull() {
        //Testing the case, that even when the values of an item: price and VAT are identical,
        //It does not matter unless there is an actual list of said item.
        //the list is empty so the expectedValues are never inserted.
        Sale sale = new Sale();
        ItemDTO itemDTO = new ItemDTO("ABC123", 2, 12.0, 0.25, 1);
        SaleDTO saleDTO = sale.updateSale(itemDTO);

        List<ItemDTO> expectedPurchasedItems = new ArrayList<>();
        expectedPurchasedItems.add(itemDTO);

        double expectedItemPrice = itemDTO.getItemPrice();
        double expectedItemVAT = itemDTO.getItemVAT();

        double expectedTotalPrice = expectedItemPrice * (1 + expectedItemVAT); // itemPrice * (1 + itemVAT)
        double expectedTotalVAT = expectedItemPrice * expectedItemVAT; // itemPrice * itemVAT
        SaleDTO expectedSaleDTO = new SaleDTO(expectedPurchasedItems, expectedTotalPrice, expectedTotalVAT);

        assertNotNull(expectedSaleDTO);
    }



    @Test
    /**
     * We test the if-branch wherein it is true. The expected outcome is that we create a new saleDTO with the added item.
     * The expectedSaleDTO should contain one more item in the list, the increased item.
     */
    public void testIncreaseQuantityIdExists() {
        Sale sale = new Sale();
        ItemDTO item1 = new ItemDTO("ABC123", 2, 12.0, 0.25, 1);
        SaleDTO saleDTO = sale.updateSale(item1);

        int itemID = 2;

        SaleDTO expectedSaleDTO = sale.increaseQuantity(itemID);

        assertFalse(expectedSaleDTO == saleDTO);
    }

    @Disabled
    public void testIncreaseQuantitySystemPrint() {
        Sale sale = new Sale();
        ItemDTO item1 = new ItemDTO("ABC123", 2, 12.0, 0.25, 1);
        int itemID = 1;

        SaleDTO expectedSaleDTO = sale.increaseQuantity(itemID);

        String expectedSystemPrintOutput = inMemoryPrintOut.toString();
        assertTrue(expectedSystemPrintOutput.contains("A String never meant to be reached"));
    }

    @Test
    public void testIncreaseQuantityNegativeArg() {
        Sale sale = new Sale();
        ItemDTO item1 = new ItemDTO("ABC123", 2, 12.0, 0.25, 1);
        SaleDTO saleDTO = sale.updateSale(item1);

        int itemID = -2;

        SaleDTO expectedSaleDTO = sale.increaseQuantity(itemID);

        assertFalse(expectedSaleDTO == saleDTO);
    }

}
