package ggc.core;

import java.io.Serializable;
import java.nio.file.ProviderNotFoundException;

public abstract class Sale extends Transaction implements Serializable{

    protected double _ammountPaid;

    protected Sale(Product product, int quantity, Partner partner){
        super(quantity, product, partner);
        _ammountPaid = 0;
    }

    @Override
    protected String toString(){
        return String.format("%s|%d|%d", super.toString(), _baseValue, _ammountPaid);
    }
}
