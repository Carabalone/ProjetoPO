package ggc.core;

import java.io.Serializable;
import java.util.*;

public class SaleByCredit extends Sale implements Serializable{
    private Date _deadline;

    protected SaleByCredit(Product product, int quantity, Date deadline, Partner partner, double value){
        super(product, quantity, partner, value);
        _deadline = deadline;
    }

    @Override
    public void receivePayment(){
        setPaymentDate(new Date(Date.showNow()));
        updateAmount();
    }

    public void updateAmount(){
        _amountPaid = _baseValue * (1 + SalePaymentCoeficients.getCoeficient(getProduct(), getPartner().getStatus(), Date.showNow() - _deadline.getDay()));
    }

    @Override
    public String toString(){
        updateAmount();
        if (getPaymentDate() != null)
            return String.format("%s|%s|%d|%d", "VENDA", super.toString(), _deadline.getDay(), getPaymentDate().getDay());
        else
            return String.format("%s|%s|%d", "VENDA", super.toString(), _deadline.getDay());
    }
}