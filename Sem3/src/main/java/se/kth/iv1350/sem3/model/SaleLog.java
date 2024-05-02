package se.kth.iv1350.sem3.model;

import se.kth.iv1350.sem3.integration.dto.SaleDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an accumulated list of all saleDTOs during a given time period.
 * This can represent the total sales accumulated over a single day.
 * The accumulated sales have one sale ID in common.
 */
public class SaleLog {

    public static int saleIDSequence = 1;

    private SaleDTO saleDTO;

    private final int saleID;

    private List<SaleDTO> listOfOldSales;

    /**
     * Constructor for the @Link(SaleLog) class.
     * Initiates an object of the class.
     * Increments the static variable of @Link(saleIDSequence) setting it to the current @Link(saleID) of the object.
     */
    public SaleLog() {
        saleID = saleIDSequence++;
        this.listOfOldSales = new ArrayList<>();
    }

    public static int getSaleIDSequence() {
        return saleIDSequence;
    }

    public SaleDTO getSaleDTO() {
        return saleDTO;
    }

    public int getSaleID() {
        return saleID;
    }

    public List<SaleDTO> getListOfOldSales() {
        return listOfOldSales;
    }

    public void setListOfOldSales(List<SaleDTO> listOfOldSales) {
        this.listOfOldSales = listOfOldSales;
    }


    public void registerSaleLog(SaleDTO saleDTO) {
        listOfOldSales.add(saleDTO);
    }
}
