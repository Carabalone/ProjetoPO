package ggc.core.exception;

public class NoSuchTransactionException extends Exception{
    private int _id;
    public NoSuchTransactionException(int id){
        _id = id;
    }
    public int getId(){
        return _id;
    }
}