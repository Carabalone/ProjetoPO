package ggc.core;

import java.io.Serializable;
import java.util.*;

public class SaleByCredit extends Sale implements Serializable{
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