package ggc.core;

import java.io.Serializable;
import java.util.*;

public class SaleByCredit extends Sale implements Serializable{
    private Date _deadline;
    private double _amountPaid;

    protected SaleByCredit(Product product, int quantity, Date deadline, Partner partner, double value){
        super(product, quantity, partner, value);
        _deadline = deadline;
        _amountPaid = 0;
    }

    @Override
    public void receivePayment(){
        setPaymentDate(new Date(Date.showNow()));
        updateAmount();
    }

    public void updateAmount(){
        if (getPaymentDate() == null)
            _amountPaid = _baseValue * (1 + SalePaymentCoeficients.getCoeficient(getProduct(), getPartner().getStatus(), Date.showNow() - _deadline.getDay()));
    }

    @Override
    public String toString(){
        if (getPaymentDate() != null)
            return String.format("%s|%s|%d|%d|%d", "VENDA", super.toString(), Math.round(_amountPaid), _deadline.getDay(), getPaymentDate().getDay());
        else {
            updateAmount();
            return String.format("%s|%s|%d|%d", "VENDA", super.toString(), Math.round(_amountPaid), _deadline.getDay());
        }
    }
}