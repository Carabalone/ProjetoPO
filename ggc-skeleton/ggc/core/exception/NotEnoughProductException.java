package ggc.core.exception;

public class NotEnoughProductException extends Exception{

    private int _available;

    public NotEnoughProductException(int availableUnits){
        _available = availableUnits;
    }

    public int getAvailableUnits(){
        return _available;
    }
}