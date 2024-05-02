package se.kth.iv1350.sem3.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.sem3.integration.dto.ItemDTO;
import se.kth.iv1350.sem3.model.Sale;

import static org.junit.jupiter.api.Assertions.*;

class InventorySystemTest {

    @BeforeEach
    void setUp() {
        Sale sale = new Sale();
    }

    @AfterEach
    void tearDown() {
        Sale sale = new Sale();
        sale = null;
    }

    @Test
    void testFetchItemInfo_Equals() {
        Sale sale = new Sale();
        ItemDTO item1 = new ItemDTO("ABC123", 2, 10.0, 5.0, 1);
        ItemDTO item2 = new ItemDTO("DEF456", 3, 15.0, 6.0, 1);
        int result = item1.getItemID();
        int expResult = 2;
        assertEquals(expResult, result, "ItemID not equal");
    }

    @Test
    void testFetchItemInfo_NotEquals() {
        Sale sale = new Sale();
        ItemDTO item1 = new ItemDTO("ABC123", 2, 10.0, 5.0, 1);
        ItemDTO item2 = new ItemDTO("DEF456", 3, 15.0, 6.0, 1);
        int result = item1.getItemID();
        int expResult = 1;
        assertFalse(expResult == result, "ItemID equal");
    }

    @Test
    void testFetchSales_negative() {
        Sale sale = new Sale();
        ItemDTO item1 = new ItemDTO("ABC123", -2, 10.0, 5.0, 1);
        ItemDTO item2 = new ItemDTO("DEF456", 3, 15.0, 6.0, 1);
        int result = item1.getItemID();
        int expResult = 2;
        assertFalse(expResult == result, "ItemID positive");
    }

    @Test
    void testFetchSales_positive() {
        Sale sale = new Sale();
        ItemDTO item1 = new ItemDTO("ABC123", 2, 10.0, 5.0, 1);
        ItemDTO item2 = new ItemDTO("DEF456", 3, 15.0, 6.0, 1);
        int result = item1.getItemID();
        int expResult = -1;
        assertFalse(expResult == result, "ItemID negative");
    }




}