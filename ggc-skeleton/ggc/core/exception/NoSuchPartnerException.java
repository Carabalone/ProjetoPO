package ggc.core.exception;

public class NoSuchPartnerException extends Exception{
    private String _id;
    public NoSuchPartnerException(String id){
        _id = id;
    }
    public String getId(){
        return _id;
    }
}
