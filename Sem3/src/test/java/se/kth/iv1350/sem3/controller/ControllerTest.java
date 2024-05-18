package se.kth.iv1350.sem3.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.sem3.integration.*;
import se.kth.iv1350.sem3.integration.dto.ItemDTO;
import se.kth.iv1350.sem3.integration.dto.SaleDTO;
import se.kth.iv1350.sem3.model.CashPayment;
import se.kth.iv1350.sem3.model.Payment;
import se.kth.iv1350.sem3.model.Sale;
import se.kth.iv1350.sem3.model.SaleLog;

import java.util.ArrayList;
import java.util.List;


public class ControllerTest {

    private AccountingSystem accSys;
    private InventorySystem invSys1;
    private Printer printer;
    private SaleLog saleLog;

    private Sale sale;

    private ItemDTO itemDTO;

    private Controller contr;

    @BeforeEach
    void setUp() {
        sale = new Sale();
        accSys = new AccountingSystem();
        invSys1 = new InventorySystem();
        printer = new Printer();
        saleLog = new SaleLog();
    }

    @AfterEach
    void tearDown() {
        sale = null;
        accSys = null;
        invSys1 = null;
        printer = null;
        saleLog = null;

    }

    @Test
    public void testStartSale() {
        contr = new Controller();
        sale = new Sale();
        contr.startSale();

        assertNotNull(contr.getSale());
    }

    @Test
    public void testStartSaleEmpty() {
        contr = new Controller();
        sale = new Sale();
        contr.startSale();

        assertTrue(contr.getSale().getSaleDTOItemList().isEmpty());
    }

    @Test
    void testEndSaleTotalPriceWithSaleDTO() {
        contr = new Controller();
        sale = new Sale();
        contr.startSale();

        ItemDTO item1 = new ItemDTO("ABC123", 2, 10.0, 0.25, 1);

        SaleDTO saleDTO = contr.getSale().updateSale(item1);

        double expectedTotalprice =  contr.endSale();

        assertEquals(expectedTotalprice, saleDTO.getCurrentTotalPrice(), 0.001);
    }

    @Test
    void testEndSaleTotalPriceEqual() {
        contr = new Controller();
        sale = new Sale();
        contr.startSale();

        ItemDTO item1 = new ItemDTO("ABC123", 2, 10.0, 0.25, 1);

        contr.getSale().updateSale(item1);

        double obviousTotalPrice = 10 * (1 + 0.25);

        double expectedTotalprice =  contr.endSale();

        assertEquals(expectedTotalprice, obviousTotalPrice, 0.001);
    }

    @Test
    void testEndSaleTotalPriceNotEqual() {
        contr = new Controller();
        sale = new Sale();
        contr.startSale();

        ItemDTO item1 = new ItemDTO("ABC123", 2, 5.0, 0.25, 1);

        contr.getSale().updateSale(item1);

        double obviousTotalPrice = 10 * (1 + 0.25);

        double expectedTotalprice = contr.endSale();

        assertFalse(expectedTotalprice == obviousTotalPrice);
    }

    @Test
    void testEndSaleTotalPriceMultipleItems() {
        contr = new Controller();
        sale = new Sale();
        contr.startSale();

        ItemDTO item1 = new ItemDTO("Gurka", 2, 5.0, 0.25, 1);
        ItemDTO item2 = new ItemDTO("Lök", 3, 10.0, 0.25, 1);

        contr.getSale().updateSale(item1);
        contr.getSale().updateSale(item2);

        double totalPriceForItem1 = 10 * (1 + 0.25);
        double expectedTotalprice = contr.endSale();


        assertFalse(expectedTotalprice == totalPriceForItem1);
    }

    @Test
    void testEndSaleTotalPriceForVAT() {
        contr = new Controller();
        sale = new Sale();
        contr.startSale();

        ItemDTO item1 = new ItemDTO("Gurka", 2, 5.0, 0.25, 1);
        ItemDTO item2 = new ItemDTO("Lök", 3, 10.0, 0.12, 1);

        contr.getSale().updateSale(item1);
        contr.getSale().updateSale(item2);

        double calculatedTotalPrice = 5 * (1 + 0.25) + 10 * (1 + 0.12);

        double expectedTotalprice = contr.endSale();

        assertEquals("The totals are not equal", expectedTotalprice, calculatedTotalPrice, 0.001);
    }

    @Test
    void testScanSizeOfListAfterScanSameItem() throws ItemInvalidException, DatabaseCallException {
        contr = new Controller();
        sale = new Sale();
        contr.startSale();
        int itemID = 2;
        int quantity = 1;

        int expectedSizeOfListAfterScan = 1;

        contr.getSale().getSaleDTOItemList().add(new ItemDTO("Banana", 2, 5.0, 0.25));

        SaleDTO result = contr.scanItem(2);
        System.out.println(result.getPurchasedItems().size());

        assertEquals("The size is not 2", result.getPurchasedItems().size(), expectedSizeOfListAfterScan);
    }



    @Test
    void testScanItemDifferentLists() throws ItemInvalidException, DatabaseCallException {
        contr = new Controller();
        sale = new Sale();
        contr.startSale();
        invSys1 = new InventorySystem();
        List<ItemDTO> itemsInInvSys = new ArrayList<>();

        itemsInInvSys.add(new ItemDTO("Banana", 1, 7.99, 0.25));
        itemsInInvSys.add(new ItemDTO("Apple", 2, 3.49, 0.12));
        itemsInInvSys.add(new ItemDTO("Orange", 3, 4.99, 0.18));
        itemsInInvSys.add(new ItemDTO("Grapes", 4, 8.99, 0.25));

        contr.scanItem(2);
        System.out.println("This is the item list: " + contr.getSale().getSaleDTOItemList());

        SaleDTO saleDTO = contr.scanItem(3);

        List<ItemDTO> dummyList = new ArrayList<>();
        dummyList.add(new ItemDTO("Apple", 2, 3.49, 0.12));
        dummyList.add(new ItemDTO("Apple", 2, 3.49, 0.12));
        dummyList.add(new ItemDTO("Orange", 3, 4.99, 0.18));
        System.out.println("This is the dummyList: " + dummyList);

        assertFalse(itemsInInvSys == dummyList);
    }

    @Test
    void testScanItemSameLists() throws ItemInvalidException, DatabaseCallException {
        contr = new Controller();
        sale = new Sale();
        contr.startSale();
        invSys1 = new InventorySystem();
        List<ItemDTO> itemsInInvSys = new ArrayList<>();

        itemsInInvSys.add(new ItemDTO("Apple", 2, 3.49, 0.12));
        itemsInInvSys.add(new ItemDTO("Orange", 3, 4.99, 0.18));

        contr.scanItem(2);

        SaleDTO saleDTO = contr.scanItem(3);

        List<ItemDTO> dummyList = new ArrayList<>();
        dummyList.add(new ItemDTO("Apple", 2, 3.49, 0.12));
        dummyList.add(new ItemDTO("Orange", 3, 4.99, 0.18));

        assertEquals("The sizes, and by extensions the lists are not identical", itemsInInvSys.size(), dummyList.size());
    }



    @Test
    void testPayChange() throws ItemInvalidException, DatabaseCallException {
        contr = new Controller();
        sale = new Sale();
        contr.startSale();
        List<ItemDTO> purchasedItems;
        Printer printer = new Printer();

        ItemDTO item1 = new ItemDTO("ABC123", 5, 10.0, 0.25, 1);
        contr.getSale().getSaleDTOItemList().add(item1);

        SaleDTO saleDTO = contr.scanItem(5);

        int amount = 200;
        String paymentMethod = "Cash";
        double expectedCurrentTotalPrice = 12.5; //Change should be 50.
        double expectedChange = amount - expectedCurrentTotalPrice;

        Payment payment = new Payment(amount, paymentMethod, expectedCurrentTotalPrice, new CashPayment());
        double expectedCustomerChange = payment.getCustomerChange();
        payment.getCustomerChange();

        contr.pay(200, "Cash");
        double changeFromPay = payment.getCustomerChange();

        assertEquals("The total price incorrectly handles change", expectedChange, changeFromPay, 0.01);
    }

    @Test
    void testPayPrice() throws ItemInvalidException, DatabaseCallException {
        contr = new Controller();
        sale = new Sale();
        contr.startSale();
        List<ItemDTO> purchasedItems;
        Printer printer = new Printer();

        ItemDTO item1 = new ItemDTO("ABC123", 5, 10.0, 0.25, 1);
        contr.getSale().getSaleDTOItemList().add(item1);

        SaleDTO saleDTO = contr.scanItem(5);

        int amount = 200;
        String paymentMethod = "Cash";
        double expectedCurrentTotalPrice = 12.5; //Change should be 50.
        double expectedChange = amount - expectedCurrentTotalPrice;

        Payment payment = new Payment(amount, paymentMethod, expectedCurrentTotalPrice, new CashPayment());
        double expectedCustomerChange = payment.getCustomerChange();
        payment.getCustomerChange();

        contr.pay(200, "Cash");
        double currentTotalPriceFromPay = contr.getSale().getSaleDTO().getCurrentTotalPrice();

        assertEquals("The total price incorrectly handles total price", expectedCurrentTotalPrice, currentTotalPriceFromPay, 0.01);
    }

    /**
     * Should in reality throw error, but we are simply testing that it works as intended.
     */
    @Test
    void testPayPriceNegative() throws ItemInvalidException, DatabaseCallException {
        contr = new Controller();
        sale = new Sale();
        contr.startSale();
        List<ItemDTO> purchasedItems;
        Printer printer = new Printer();

        ItemDTO item1 = new ItemDTO("ABC123", 5, 10.0, 0.25, 1);
        contr.getSale().getSaleDTOItemList().add(item1);

        SaleDTO saleDTO = contr.scanItem(5);

        int amount = 10;
        String paymentMethod = "Cash";
        double expectedCurrentTotalPrice = 12.5; //Change should be 50.
        double expectedChange = amount - expectedCurrentTotalPrice;

        Payment payment = new Payment(amount, paymentMethod, expectedCurrentTotalPrice, new CashPayment());
        double expectedCustomerChange = payment.getCustomerChange();
        payment.getCustomerChange();

        contr.pay(10, "Cash");
        double currentTotalPriceFromPay = contr.getSale().getSaleDTO().getCurrentTotalPrice();

        assertEquals("The total price cannot calculate total price", expectedCurrentTotalPrice, currentTotalPriceFromPay, 0.01);
    }

    @Test
    void testPayPriceZeroVAT() throws ItemInvalidException, DatabaseCallException {
        contr = new Controller();
        sale = new Sale();
        contr.startSale();
        List<ItemDTO> purchasedItems;
        Printer printer = new Printer();

        ItemDTO item1 = new ItemDTO("ABC123", 5, 12.5, 0, 1);
        contr.getSale().getSaleDTOItemList().add(item1);

        SaleDTO saleDTO = contr.scanItem(5);

        double amount = 12.5;
        String paymentMethod = "Cash";
        double expectedCurrentTotalPrice = 12.5; //Change should be 0
        double expectedChange = amount - expectedCurrentTotalPrice;

        Payment payment = new Payment(amount, paymentMethod, expectedCurrentTotalPrice, new CashPayment());
        double expectedCustomerChange = payment.getCustomerChange();
        double changeFromPay = payment.getCustomerChange();

        contr.pay(12.5, "Cash");
        double currentTotalPriceFromPay = contr.getSale().getSaleDTO().getCurrentTotalPrice();

        assertEquals("The change cannot be calculated to zero", changeFromPay, expectedChange, 0.01);
    }





//        public void testPayChange(int amount, String paymentMethod) {
//            System.out.println("\n-------------------Begin Payment------------------------" + "\nCustomer pays: " + amount + " SEK via " + paymentMethod);
//            double currentTotalPrice = sale.getCurrentTotalPrice();
//            Payment payment = new Payment(amount, paymentMethod, currentTotalPrice);
//            Receipt receipt = new Receipt(sale.getSaleDTO(), payment);
//            printer.print(receipt);
//            saleLog.registerSaleLog(sale.getSaleDTO());
//            sale = null;
//            System.out.println("Payment finalized: Accounting and InventorySystem updated DELETE THIS LATER");
//        }
//
//
//
//        Printer printer = new Printer();
//        ItemDTO item1 = new ItemDTO("Gurka", 2, 5.0, 0.25, 1);
//        purchasedItems.add(item1);
//        double item1Price = item1.getItemPrice();
//        double item1VAT = item1.getItemVAT();
//
//        double currentTotalPrice = item1Price;  //5 kr
//
//        SaleDTO saleDTO = new SaleDTO(purchasedItems, item1Price, item1VAT);
//
//        Payment payment = new Payment(150, "Cash", currentTotalPrice);
//
//        Receipt receipt = new Receipt(saleDTO, payment);
//
//        printer.print(receipt);
//
//        Receipt printedReceipt = printer.getReceipt();
//
//        Assertions.assertEquals(receipt, printedReceipt);

    }

//    public void testPayChange(int amount, String paymentMethod) {
//        System.out.println("\n-------------------Begin Payment------------------------" + "\nCustomer pays: " + amount + " SEK via " + paymentMethod);
//        double currentTotalPrice = sale.getCurrentTotalPrice();
//        Payment payment = new Payment(amount, paymentMethod, currentTotalPrice);
//        Receipt receipt = new Receipt(sale.getSaleDTO(), payment);
//        printer.print(receipt);
//        saleLog.registerSaleLog(sale.getSaleDTO());
//        updateExternalSystems();
//        sale = null;
//        System.out.println("Payment finalized: Accounting and InventorySystem updated DELETE THIS LATER");
//    }