package ggc.core;

import java.io.Serializable;

public abstract class Transaction implements Serializable{
    private int _id;
    private Date _paymentDate;
    protected double _baseValue;
    private int _quantity;
    private Product _product;
    private Partner _partner;

    protected Transaction(int quantity, Date paymentDate, Product product, Partner partner){
        _quantity = quantity;
        _paymentDate = paymentDate;
        _product = product;
        _partner = partner;
        _id = Warehouse.getNextTransactionId();
        Warehouse.advanceTransactionId();
    }
    protected Transaction(int quantity, Product product, Partner partner){
        _quantity = quantity;
        _product = product;
        _partner = partner;
        _paymentDate = null;
        _id = Warehouse.getNextTransactionId();
        Warehouse.advanceTransactionId();
    }
    //TODO
    public boolean isPaid(){
        return true;
    }
    public Date getPaymentDate(){
        return new Date(_paymentDate.getDay());
    }

    @Override
    public String toString(){
        return String.format("%d|%s|%s|%d", _id, _partner.getId(), _product.getId(), _quantity);
    }
}