package ggc.core;

import java.io.Serializable;

public class Acquisition extends Transaction implements Serializable{
    


    public Acquisition(Product product, int quantity, double value, Partner partner){
        super(quantity, new Date(Date.showNow()), product, partner, value);
    }

    @Override
    public String toString(){
        return String.format("%s|%s|%d|%d", "COMPRA", super.toString(), Math.round(_baseValue), getPaymentDate().getDay());
    }
    public double getValue(){
        return getBaseValue()*getQuantity();
    }
}