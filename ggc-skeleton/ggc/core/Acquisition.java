package ggc.core;

import java.io.Serializable;

public class Acquisition extends Transaction implements Serializable{
    
    public Acquisition(Product product, int quantity, Partner partner){
        super(quantity, Date.now(), product, partner);
    }

    @Override
    public String toString(){
        return String.format("%s|%s|%d|%d", "COMPRA", super.toString(), _baseValue, getPaymentDate().getDay());
    }
}