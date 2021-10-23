package ggc.core;
public class Acquisition extends Transaction{
    Partner _partner;
    protected Acquisition(Product p, int quantity, Partner intPart){
        super(quantity, Date.now(), p);
        _partner = intPart;

    }
    //TODO
    public String toString(){
        return "TODO";
    }
}