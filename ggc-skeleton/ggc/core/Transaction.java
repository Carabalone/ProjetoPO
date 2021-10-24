package ggc.core;
public abstract class Transaction{
    private int _id;
    private Date _paymentDate;
    protected double _baseValue;
    private int _quantity;
    private Product _product;

    protected Transaction(int quantity, Date paymentDate, Product product){
        _quantity = quantity;
        _paymentDate = paymentDate;
        _product = product;
    }
    protected Transaction(int quantity, Product product){
        _quantity = quantity;
        _product = product;
    }
    //TODO
    public boolean isPaid(){
        return true;
    }
    public Date getPaymentDate(){
        return new Date(_paymentDate.getDay());
    }
}