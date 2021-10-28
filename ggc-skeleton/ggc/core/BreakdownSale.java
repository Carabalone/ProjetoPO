package ggc.core;

import java.io.Serializable;
import java.util.*;
public class BreakdownSale extends Sale implements Serializable{
    private List<Batch> _batches;
    protected BreakdownSale(Product p, int quantity, Partner intPart){
        super(p, quantity, intPart);
        _batches = new ArrayList();
    }
    public String toString(){
        return "TODO";
    }

}
