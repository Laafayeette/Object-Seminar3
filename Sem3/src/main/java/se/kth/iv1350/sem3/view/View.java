package se.kth.iv1350.sem3.view;

import se.kth.iv1350.sem3.controller.Controller;
import se.kth.iv1350.sem3.integration.DatabaseCallException;
import se.kth.iv1350.sem3.integration.ItemInvalidException;
import se.kth.iv1350.sem3.integration.dto.ItemDTO;
import se.kth.iv1350.sem3.integration.dto.SaleDTO;
import se.kth.iv1350.sem3.util.LogHandler;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * This class represents and stimulates the cashier's and customer's activity within the sale.
 * The someMethodInView represents inputs to the sale.
 * All activity within the sale is a result of the View class' initiation.
 */
public class View {

    private final Controller contr;
    private ErrorMessageHandler errorMsgHandler = new ErrorMessageHandler();
    private LogHandler logHandler = new LogHandler();


    /**
     * Constructor for the view class.
     * Calls upon @Link(someMethodInView) to instantiate a new sale in the store.
     * @param contr The Controller object.
     */
    public View(Controller contr) {
        this.contr = contr;
    }

    public void sampleExecution() {
        contr.startSale();
        int minQuantity = 1;
        int maxQuantity = 3;
        Random random = new Random();
        for(int i = 1, quantity = 2; i < 5; i++) {
            //quantity = random.nextInt(maxQuantity - minQuantity + 1) + minQuantity;
            for(int j= 1; j <= quantity; j++) {
                System.out.println("---------------------Scanning item with ID " + i + "---------------------------");
                try {
                    SaleDTO saleDTO = contr.scanItem(i);
                    printItemDTOString(saleDTO.getItemDTO(i));
                    printSaleDTO(saleDTO);
                } catch (ItemInvalidException e) {
                    e.printStackTrace();
                    System.out.println("Caught the ItemInvalidException in View, about to errorMessage it");
                    errorMsgHandler.showMessage("Could not scan item, no such item exists in store. Please try another item..");
                } catch (DatabaseCallException e) {
                    System.out.println("Caught the DatabaseCallException in in View, about to errorMessage it and log");
                    errorMsgHandler.showMessage("Failed to scan item, there might be an error in the system. Please try again or get supervisor");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("\nQuantity of this item sold: " + quantity);
        }
        contr.endSale();
        printEndSale();
        double change = contr.pay(200, "cash");
        printReturnChangeToCustomer(change);
    }

    private void printToLogAndView(String errorMsg, Exception e) {
        errorMsgHandler.showMessage(errorMsg);
        logHandler.log(e);
    }

    private void printReturnChangeToCustomer(double change) {
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("\nChange to give back to Customer: " + df.format(change));
    }

    private void printItemDTOString(ItemDTO itemDTO) {
        System.out.println(
                "Item ID: " + itemDTO.getItemID() + "\n" +
                        "Item name: " + itemDTO.getItemName() + "\n" +
                        "Item cost: " + String.format("%.2f", itemDTO.getItemPrice()) + " SEK\n" +
                        "VAT: " + (100* itemDTO.getItemVAT()) + "%");
    }

    private void printSaleDTO(SaleDTO saleDTO) {
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println( "\n" +
                "\nTotal Cost (incl VAT): " + df.format(contr.getSale().getSaleDTO().getCurrentTotalPrice()) + " SEK" +
                "\nTotal VAT: " + df.format(contr.getSale().getSaleDTO().getTotalVAT()) + " SEK" + "\n" );
    }

    private void printEndSale() {
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println( "---------------------End sale--------------------------- " +
                "\nTotal Cost is (incl VAT): " + df.format(contr.getSale().getSaleDTO().getCurrentTotalPrice()) + " SEK");
    }
}
