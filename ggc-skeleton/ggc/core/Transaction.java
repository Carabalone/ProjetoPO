package ggc.core;
public abstract class Transaction{
    private int _id;
    private Date _paymentDate;
            double _baseValue;
    private int _quantity;

    protected Transaction(int quantity, Date paymentDate){
        _quantity = quantity;
        _paymentDate = paymentDate;
    }
    //TODO
    public boolean isPaid(){
        return true;
    }
    public Date getPaymentDate(){
        return new Date(_paymentDate.getDay());
    }
}