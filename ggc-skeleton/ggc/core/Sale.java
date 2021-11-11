package ggc.core;

import java.io.Serializable;
import java.nio.file.ProviderNotFoundException;

public abstract class Sale extends Transaction implements Serializable{

    protected double _amountPaid;

    protected Sale(Product product, int quantity, Partner partner, double value){
        super(quantity, product, partner, value);
        _amountPaid = 0;
    }

    public void receivePayment(){
        setPaymentDate(new Date(Date.showNow()));
        updateAmount();
    }

    public void updateAmount(){
        _amountPaid = _baseValue * (1 + SalePaymentCoeficient.getCoeficient(getProduct(), getPartner().getStatus(), Date.showNow() - getPaymentDate().getDay()));
    }

    @Override
    public String toString(){
        updateAmount();
        return String.format("%s|%d|%d", super.toString(), Math.round(_baseValue), Math.round(_amountPaid));
    }
}
