package ggc.core;

import java.io.Serializable;
import java.nio.file.ProviderNotFoundException;

public abstract class Sale extends Transaction implements Serializable{

    protected double _amountPaid;

    protected Sale(Product product, int quantity, Partner partner, double value){
        super(quantity, product, partner, value);
        _amountPaid = 0;
    }

    @Override
    public void receivePayment(){}

    @Override
    public String toString(){
        return String.format("%s|%d|%d", super.toString(), Math.round(_baseValue), Math.round(_amountPaid));
    }
}
