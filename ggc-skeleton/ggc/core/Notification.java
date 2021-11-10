package ggc.core;
public class Notification {

    private Type _type;
    private Product _product;

    protected Notification(Type type, Product product){
        _type = type;
        _product = product;
    }

    @Override
    public String toString(){
        /*  since the bargain only notifies when the price is at it's lowest and when the stock of a product goes from 0 to 1+
        *   the lowest and maximum prices are the same we can just use _product.getLowestPrice to get the price of the product.
        */
        return String.format("%s|%d|%d", _type.name(), _product.getId(), (int) _product.getLowestPrice());
    }
}
