package ggc.core.exception;

public class NotEnoughProductException extends Exception{

    private int _available;
    private int _requested;
    private String _productId;

    public NotEnoughProductException(String productId, int requestedUnits, int availableUnits){
        _available = availableUnits;
        _reuested = requestedUnits;
        _productId = productId;
    }

    public int getAvailableUnits(){
        return _available;
    }

    public String getId(){
        return _productId;
    }

    public int getRequestedUnits(){
        return _requested;
    }
}