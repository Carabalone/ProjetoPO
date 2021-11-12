package ggc.core.exception;

public class NotEnoughProductException extends Exception{

    private int _available;
    private int _rquested;
    private String _productiId;

    public NotEnoughProductException(String productId, int requestedUnits, int availableUnits){
        _available = availableUnits;
        _rquested = requestedUnits;
        _productiId = productId;
    }

    public int getAvailableUnits(){
        return _available;
    }

    public int getId(){
        return _productId;
    }

    public int getRequestedUnits(){
        return _requested;
    }
}