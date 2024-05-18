package se.kth.iv1350.sem3.model;

import se.kth.iv1350.sem3.integration.dto.ItemDTO;
import se.kth.iv1350.sem3.integration.dto.SaleDTO;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * This class represents the Receipt to be printed at the end of the sale.
 */
public class Receipt {

    private final SaleDTO saleDTO;
    private final Payment payment;
    private final LocalDateTime timeOfSale;

    /**
     * Constructor for the Receipt class.
     * Constructs a new object of the Receipt class based on the saleDTO and payment objects.
     * Prints the receipt of the sale.
     * @param saleDTO The saleDTO of the finalized sale.
     * @param payment The payment object, encapsulating information about the @Link(amount),@Link(paymentMethod) and @Link(currentTotalPrice).
     */
    public Receipt(SaleDTO saleDTO, Payment payment) {
        this.saleDTO = saleDTO;
        this.payment = payment;
        timeOfSale = LocalDateTime.now();
    }

    /**
     * Generates a complete receipt of the sale.
     * @return The receipt to be printed.
     */
    public String getReceipt() {
        StringBuilder receiptBuilder = new StringBuilder();
        receiptBuilder.append("\n-------------------Begin Receipt------------------------\n");
        itemInfoGenerator(receiptBuilder);
        gatherReceiptInformation(receiptBuilder);
        printTimeOfSale(receiptBuilder);
        whoAmI(receiptBuilder);
        receiptBuilder.append("-------------------End Receipt------------------------\n\n");
        return receiptBuilder.toString();
    }

    private void whoAmI(StringBuilder receiptBuilder) {
        receiptBuilder.append("\nCustomer Name: Teoman Köylüoglu\n");
        receiptBuilder.append("City: San Francisco Bay Area, California\n");
        receiptBuilder.append("Occupation: Software Engineer, Google\n");
    }

    private void printTimeOfSale(StringBuilder receiptBuilder) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = timeOfSale.format(formatter);
        receiptBuilder.append("Date: ").append(formattedTime).append("\n");
    }

    private void gatherReceiptInformation(StringBuilder receiptBuilder) {
        DecimalFormat df = new DecimalFormat("0.00");
        receiptBuilder.append("\rAmount of items: ").append(saleDTO.getPurchasedItems().size()).append("\n");
        printSaleDTOForReceipt(receiptBuilder);
        receiptBuilder.append(payment.getPaymentMethod() + ": ").append(payment.getAmount()).append(" SEK").append("\n");
        receiptBuilder.append("Change: ").append(df.format(payment.getCustomerChange())).append(" SEK\n");
        receiptBuilder.append("\r\n");
    }

    private void printSaleDTOForReceipt(StringBuilder receiptBuilder) {
        DecimalFormat df = new DecimalFormat("#.##");
        receiptBuilder.append("\nTotal Cost (incl VAT): ").append(df.format(saleDTO.getCurrentTotalPrice())).append(" SEK\n");
        receiptBuilder.append("Total VAT: ").append(df.format(saleDTO.getTotalVAT())).append(" SEK\n\n");
    }

    private void itemInfoGenerator(StringBuilder receiptBuilder) {
        for (ItemDTO item : saleDTO.getPurchasedItems()) {
            String itemName = item.getItemName();
            double multipliedPriceOfSameItem = item.getItemPrice() * item.getQuantity();

            receiptBuilder.append(String.format("%s %d x %.2f  %.2f SEK%n", itemName, item.getQuantity(), item.getItemPrice(), multipliedPriceOfSameItem));
        }
    }
}
