package ggc.core;

public class Batch {
    private Partner _partner;
    private Product _product;
    private double _price;
    private int _quantity;

    public Batch(Partner partner, Product product, double price, int quantity){
        _partner = partner;
        _product = product;
        _price = price;
        _quantity = quantity;
    }

    //TODO
    public String toString(){
        return "TODO";
    }
    
    protected Batch makeCopy(){
        return new Batch(_partner, _product, _price, _quantity);
    }
    
}
