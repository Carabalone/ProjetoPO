package ggc.core;

import java.io.Serializable;
import java.util.*;
public class BreakdownSale extends Sale implements Serializable{
    private List<Batch> _batches;

    protected BreakdownSale(Product product, int quantity, Partner partner, value){
        super(product, quantity, partner, value);
        _batches = new ArrayList();
    }

    //TODO add components field in the end
    @Override
    public String toString(){
        if(getPaymentDate() != null)
            return String.format("%s|%s|%d", "DESAGREGAÇÃO", super.toString(), getPaymentDate().getDay());
        else
            return String.format("%s|%s", "DESAGREGAÇÃO", super.toString());
    }

}
