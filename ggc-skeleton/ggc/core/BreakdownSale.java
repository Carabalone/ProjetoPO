package ggc.core;

import java.io.Serializable;
import java.util.*;
public class BreakdownSale extends Sale implements Serializable{
    private List<Batch> _batches;
    private double _paidAmount;

    protected BreakdownSale(Product product, int quantity, Partner partner, double value, List<Batch> batches){
        super(product, quantity, partner, value);
        _batches = batches;

        if (value > 0)
            _paidAmount = value;
        else 
            _paidAmount = 0;

        setPaymentDate(new Date(Date.showNow()));
    }

    /*private String toStringComponents(){

    }*/

    //TODO add components field in the end

    @Override
    public double getAmountPaid(){
        return _baseValue;
    }

    @Override
    public String toString(){
        String generatedBatches = "";
        for (Batch b : _batches){
            generatedBatches += String.format("%s:%d:%d#", b.getProduct().getId(), b.getAvailableQuantity(), (int) b.getPriceOfUnits(b.getAvailableQuantity()));
        }
        generatedBatches = generatedBatches.substring(0, generatedBatches.length() - 1);
        return String.format("%s|%s|%d|%d|%s", "DESAGREGAÇÃO", super.toString(), (int) _paidAmount, getPaymentDate().getDay(), generatedBatches);
    }

}
