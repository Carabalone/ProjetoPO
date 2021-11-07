package ggc.core;

import java.io.Serializable;
import java.util.*;

public class SaleByCredit extends Sale implements Serializable{
    private Date _deadline;

    protected SaleByCredit(Product product, int quantity, Date deadline, Partner partner){
        super(product, quantity, partner);
        _deadline = deadline;
    }

    @Override
    public String toString(){
        if (getPaymentDate() != null)
            return String.format("%s|%s|%d|%d", "VENDA", super.toString(), _deadline.getDay(), getPaymentDate().getDay());
        else
            return String.format("%s|%s|%d|%d", "VENDA", super.toString(), _deadline.getDay());
    }
}