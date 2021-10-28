package ggc.core.exception;

public class DuplicatePartnerIdException extends Exception{
    private String _id;
    public DuplicatePartnerIdException(String id){
        _id = id;
    }
    public String getId(){
        return _id;
    }
}
