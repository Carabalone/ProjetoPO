package ggc.core.exception;

public class NoSuchProductException extends Exception{
    private String _id;
    public NoSuchProductException(String id){
        _id = id;
    }
    public String getId(){
        return _id;
    }
}

