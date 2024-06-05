package se.kth.iv1350.sem3.view;

/**
 * This class represents and handles the <code>totalRevenue</code> to be printed on the user interface.
 */
public class TotalRevenueView extends TotalRevenueTemplate {

    private final ErrorMessageHandler errorMsgHandler = new ErrorMessageHandler();


    @Override
    protected void doShowTotalRevenue(double totalRevenue) throws Exception {
        System.out.println("Total Revenue: " + totalRevenue + " SEK");  //Specifik
    }


    @Override
    protected void handleErrors(Exception e) {
        errorMsgHandler.showMessage("Operation resulted in error." + e.getMessage());
    }
}
