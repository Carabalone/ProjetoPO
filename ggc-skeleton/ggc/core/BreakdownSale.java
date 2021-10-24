package ggc.core;

import java.util.*;
public class BreakdownSale extends Sale{
    private List<Batch> _batches;
    protected BreakdownSale(Product p, int quantity, Partner intPart){
        super(p, quantity, intPart);
        _batches = new ArrayList();
    }
    public String toString(){
        return "TODO";
    }

}
