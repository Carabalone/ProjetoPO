package ggc.core;

import java.io.Serializable;

public abstract class Transaction implements Serializable{
    private int _id;
    private Date _paymentDate;
    protected double _baseValue;
    private int _quantity;
    private Product _product;
    private Partner _partner;

    protected Transaction(int quantity, Date paymentDate, Product product, Partner partner, double value){
        _quantity = quantity;
        _paymentDate = paymentDate;
        _product = product;
        _partner = partner;
        _baseValue = value;
        _id = Warehouse.getNextTransactionId();
        Warehouse.advanceTransactionId();
    }
    protected Transaction(int quantity, Product product, Partner partner, double value){
        _quantity = quantity;
        _product = product;
        _partner = partner;
        _paymentDate = null;
        _baseValue = value;
        _id = Warehouse.getNextTransactionId();
        Warehouse.advanceTransactionId();
    }
    protected boolean isPaid(){
        return true;
    }

    protected int getDeadline(){
        return 0;
    }
    
    protected Date getPaymentDate(){
        return _paymentDate;
    }

    protected int receivePayment(){
        return 0;
    }

    protected void setPaymentDate(Date date){
        _paymentDate = date;
    }

    @Override
    public String toString(){
        return String.format("%d|%s|%s|%d", _id, _partner.getId(), _product.getId(), _quantity);
    }

    protected int getId(){
        return _id;
    }

    protected Product getProduct(){
        return _product;
    }

    protected Partner getPartner(){
        return _partner;
    }

    protected int getQuantity(){
        return _quantity;
    }

    protected double getBaseValue(){
        return _baseValue;
    }
}