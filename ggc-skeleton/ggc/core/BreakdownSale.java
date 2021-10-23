package ggc.core;

public class BreakdownSale extends Sale{
    private Batch _batch;
    protected BreakdownSale(Product p, int quantity, Partner intPart){
        super(p, quantity, intPart);
    }
    public String toString(){
        return "TODO";
    }
}
