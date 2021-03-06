package ggc.core;

import java.io.Serializable;

public class Acquisition extends Transaction implements Serializable{
    


    protected Acquisition(Product product, int quantity, double value, Partner partner){
        super(quantity, new Date(Date.showNow()), product, partner, value);
    }

    protected double getValue(){
        return getBaseValue()*getQuantity();
    }

    @Override
    public String toString(){
        return String.format("%s|%s|%d|%d", "COMPRA", super.toString(), Math.round(getValue()), getPaymentDate().getDay());
    }
}