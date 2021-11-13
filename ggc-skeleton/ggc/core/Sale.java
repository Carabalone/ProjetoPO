package ggc.core;

import java.io.Serializable;

public abstract class Sale extends Transaction implements Serializable{

    protected Sale(Product product, int quantity, Partner partner, double value){
        super(quantity, product, partner, value);
    }

    @Override
    public int receivePayment(){
        return 0;
    }

    protected double getBaseValue(){
        return _baseValue;
    }

    abstract double getAmountPaid();

    @Override
    public String toString(){
        return String.format("%s|%d", super.toString(), Math.round(_baseValue));
    }
}
