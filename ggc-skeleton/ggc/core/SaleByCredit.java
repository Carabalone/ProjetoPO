package ggc.core;

import java.util.*;

public class SaleByCredit extends Sale{
    private Date _deadline;
    private double _ammountPaid;

    protected SaleByCredit(Product p, int quantity, Date deadline, Partner intPart){
        super(p, quantity, intPart);
        _deadline = deadline;
    }
    public String toString(){
        return "TODO";
    }
}