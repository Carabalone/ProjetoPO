package ggc.core;
public class Acquisition extends Transaction{
    Partner _partner;
    Product _product;
    protected Acquisition(Product p, int quantity, Partner intPart){
        super(quantity, Date.now());
        _partner = intPart;
        _product = p;
    }
}